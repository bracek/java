package com.ixonos.skillnet.web.companytree;

import javax.annotation.Resource;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;

import org.springframework.context.annotation.Scope;
import org.springframework.security.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.security.SkillnetUser;
import com.ixonos.skillnet.logic.service.UsersService;
import com.ixonos.skillnet.web.util.window.CurriculumWindow;
import org.springframework.security.context.SecurityContextHolder;

@Service("CompanyTreeModelController")
@Scope("prototype")
public class CompanyTreeModelController extends GenericForwardComposer implements
        TreeitemRenderer, RowRenderer, ListitemRenderer, CompanyTreeModelControlerInterface {

    public CompanyTreeModelController() {
    }
    @Resource(name = "companyTreeModel")
    protected CompanyTreeModel treeUserModel;
    protected Tree tree;
    protected Grid userdetailsgrid;
    protected Button downloadCV;
    protected Grid userDetailGrid;
    protected Listbox userDetailListbox;
    protected ListModelList userDetailsListModel;
    protected ListModelList userDetailsListModelCurriculum;
    private boolean possibleDownloadCv = false;
    @Resource
    protected UsersService usersService;
    private Users selectedUser;
    public static final String CURRICULUM_DOC_SUFFIX = "_cv.doc";

    @Override
    public void checkPermissionForDownloadCV() {

        final GrantedAuthority[] grandedAuthority = ((SkillnetUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities();
        //find if user is role of GROUP_MANAGER
        for (int i = 0; i < grandedAuthority.length; i++) {
            final GrantedAuthority grantedAuthority = grandedAuthority[i];
            if (grantedAuthority.getAuthority().equals(ROLE_GM)) {
                setPossibleDownloadCv(Boolean.TRUE);
                break;
            }
        }
    }

    @Override
    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
        checkPermissionForDownloadCV(); //find out if download buton can be active for user, only for GM role

        treeUserModel.clearMapUser();
        tree.setModel(treeUserModel);
        tree.setTreeitemRenderer(this);
        userDetailsListModel = new ListModelList();
        userdetailsgrid.setModel(userDetailsListModel);

        userDetailsListModelCurriculum = new ListModelList();
        userDetailListbox.setModel(userDetailsListModelCurriculum);
        userDetailListbox.setItemRenderer(this);

        userdetailsgrid.setRowRenderer(this);

        tree.addEventListener("onSelect", new EventListener() {

            @Override
            public void onEvent(final Event e)
                    throws Exception {

                Users user = (Users) tree.getSelectedItem().getValue();
                selectedUser = (Users) tree.getSelectedItem().getValue();
                userDetailsListModel.clear();
                if (user.getCurriculum() == null) {
                    user.setIsCurriculumAlreadyFillUp(Boolean.FALSE);
                } else {
                    user.setIsCurriculumAlreadyFillUp(Boolean.TRUE);
                }

                userDetailsListModel.add(new String[]{Labels.getLabel("compTree.firstName"), user.getName()});
                userDetailsListModel.add(new String[]{Labels.getLabel("compTree.lastName"), user.getSurname()});
                userDetailsListModel.add(new String[]{Labels.getLabel("compTree.email"), user.getEmail()});
                userDetailsListModel.add(new String[]{Labels.getLabel("compTree.telephone"), user.getTelephoneNumber()});
                userDetailsListModel.add(new String[]{Labels.getLabel("compTree.location"), user.getLocation()});
                userDetailsListModel.add(new String[]{Labels.getLabel("compTree.position"), user.getPosition()});

                userDetailsListModelCurriculum.clear();
                userDetailsListModelCurriculum.add(user);
                userDetailListbox.setModel(userDetailsListModelCurriculum);

                if (user.isIsCurriculumAlreadyFillUp()) {
                    //add exception for logged user (can download Cv)
                    //check only if curricullum isn't empy for rendered user
                    if (!isPossibleDownloadCv()) { //jump if current user is with ROLE_GROUP_MANAGER

                        final String usernameLoggedUser = ((SkillnetUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
                        if (usernameLoggedUser.equals(user.getUsername())) {
                            setPossibleDownloadCv(Boolean.TRUE);
                        } else {
                            setPossibleDownloadCv(Boolean.FALSE);
                        }
                    }

                    if (isPossibleDownloadCv()) {
                        userDetailGrid.setVisible(Boolean.TRUE);
                        downloadCV.setVisible(Boolean.TRUE);
                    }
                } else {
                    userDetailGrid.setVisible(Boolean.FALSE);
                    downloadCV.setVisible(Boolean.FALSE);
                }
            }
        });
    }

    @Override
    public void render(final Treeitem item,final  Object data) throws Exception {
        Users user = (Users) data;
        Treecell tcUserName = new Treecell(user.getHumanReadableUsername());
        Treerow tr = null;
        /*
         * Since only one treerow is allowed, if treerow is not null, append
         * treecells to it. If treerow is null, construct a new treerow and
         * attach it to item.
         */
        if (item.getTreerow() == null) {
            tr = new Treerow();
            tr.setParent(item);
        } else {
            tr = item.getTreerow();
            tr.getChildren().clear();
        }
        tcUserName.setParent(tr);

        item.setValue(user);
        item.setContext("expandPopup");
    }

    public void onClick$expandMenuItem(final Event e) {
        Treeitem selectedItem = tree.getSelectedItem();
        if (selectedItem != null) {
            expandTreeItemsRecursively(selectedItem);
        }
    }

    private void expandTreeItemsRecursively(final Treeitem treeItem) {
        if (!treeItem.isOpen()) {
            treeItem.setOpen(true);
        }
        if (treeItem.getTreechildren() != null) {
            Object[] items = treeItem.getTreechildren().getItems().toArray();
            for (int i = 0; i < items.length; i++) {
                Object item = items[i];
                if (item instanceof Treeitem) {
                    expandTreeItemsRecursively((Treeitem) item);
                }
            }
        }
    }

    @Override
    public void render(final Row row,final  Object data) throws Exception {
        String[] userData = (String[]) data;
        Label label = new Label(userData[0]);
        Textbox textbox = new Textbox(userData[1]);

        textbox.setWidth("100%");
        textbox.setReadonly(true);
        label.setParent(row);
        textbox.setParent(row);
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void onClick$downloadCV(final Event event) throws Exception {
        CurriculumWindow.downloadCV(event, selectedUser);
    }

    @Override
    public void render(final Listitem listItem,final  Object data) throws Exception {
        if (data != null) {
            if (data instanceof Users) {
                listItem.setValue(data);
                final Users users = (Users) data;

                boolean userHasCV = users.isIsCurriculumAlreadyFillUp();
                Listcell name = new Listcell();
                name.setLabel(Labels.getLabel("compTree.hasUserCurriculum"));
                name.setParent(listItem);
                Listcell lcIcon = new Listcell();

                if (userHasCV) {
                    ((Listcell) lcIcon).setImage("/img/icons/true.png");

                } else {
                    ((Listcell) lcIcon).setImage("/img/icons/false.png");
                }
                lcIcon.setParent(listItem);
            }
        }
    }

    public boolean isPossibleDownloadCv() {
        return possibleDownloadCv;
    }

    public void setPossibleDownloadCv(final boolean possibleDownloadCv) {
        this.possibleDownloadCv = possibleDownloadCv;
    }
}
