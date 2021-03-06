//package fi.ixonos.projects.web.usermanagement.add;
//
//import fi.ixonos.projects.logic.bean.Projects;
//import fi.ixonos.projects.logic.bean.Users;
//import fi.ixonos.projects.logic.service.ProjectsService;
//import fi.ixonos.projects.logic.service.UsersService;
//import fi.ixonos.projects.web.utils.ZkUtils;
//import fi.ixonos.projects.web.zk.IZkWindowGeneralAction;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zkplus.spring.SpringUtil;
//import org.zkoss.zul.Listitem;
//import org.zkoss.zul.Window;
//
///**
// *
// * @author katrami
// */
//public final class EditUsersWindow extends Window implements IZkWindowGeneralAction<Users> {
//
//    private Users user;
//    private Listbox usersListbox;
//    private Listbox updateProjectListbox;
//    private ProjectsService projectsService = (ProjectsService) SpringUtil.getApplicationContext().getBean("projectsService");
//    private List<Users> addUsersList;
//
//    public EditUsersWindow() {
//        this.user = new Users();
//    }
//
//    @Override
//    public void onAdd(Event event) throws Exception {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("usersListbox", this.getFellow("usersListbox"));
//        Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/users/addNewUser.zul", null, map);
//        win.doModal();
//        refreshView();
//    }
//
//    @Override
//    public void onUpdate(Event event) throws Exception {
//
//        UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");
//        usersService.update(this.user);
//        refreshView();
//    }
//
//    @Override
//    public boolean checkValues(Users t) throws Exception {
//        return false;
//    }
//
//    @Override
//    public void onDelete(Event event) throws Exception {
//
//        Listitem li = (Listitem) ZkUtils.getParentComponent(event.getTarget(), Listitem.class);
//        final Users selectedUser = (Users) (((Listbox) (li.getParent())).getModel().getElementAt(li.getIndex()));
//        //first we need remove user from all projects in which user exist
//
//        UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");
//        boolean deleted = usersService.removeUser(selectedUser.getUsername());
//        if (!deleted) {
//            throw new Exception("unable to delete user with id: " + selectedUser.getUserId());
//        }
//
//
//        li.getListbox().removeItemAt(li.getIndex());
//        refreshView();
//    }
//
//    /**
//     * refresh list of projects
//     */
//    @Override
//    public void refreshView() {
//
//        UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");
//
//        final Listbox projectsLisbox = (Listbox) this.getFellow("usersListbox");
//        final List projectsList = (List) projectsLisbox.getModel();
//        projectsList.clear();
//        projectsList.addAll(usersService.readAll());
//    }
//
//    public Listbox getUpdateProjectListbox() {
//        return updateProjectListbox;
//    }
//
//    public void setUpdateProjectListbox(Listbox updateProjectListbox) {
//        this.updateProjectListbox = updateProjectListbox;
//    }
//
//    public Listbox getUsersListbox() {
//        return usersListbox;
//    }
//
//    public void setUsersListbox(Listbox usersListbox) {
//        this.usersListbox = usersListbox;
//    }
//
//    public Users getUser() {
//        return user;
//    }
//
//    public void setUser(Users user) {
//        this.user = user;
//    }
//
//    public List<Users> getAddUsersList() {
//        return addUsersList;
//    }
//
//    public void setAddUsersList(List<Users> addUsersList) {
//        this.addUsersList = addUsersList;
//    }
//}
