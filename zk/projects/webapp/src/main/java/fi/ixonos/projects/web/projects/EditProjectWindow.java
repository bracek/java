package fi.ixonos.projects.web.projects;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.ProjectsService;
import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.allocation.UsersComparator;
import fi.ixonos.projects.web.zk.IZkWindowGeneralAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Window;

/**
 *
 * @author katrami
 */
public class EditProjectWindow extends Window implements IZkWindowGeneralAction<Projects>, IEditProjectWindowAction {

    private Projects project;
    private Users user;
    private Listbox projectsListbox;
    private Listbox usersListbox;
    private Listbox updateProjectListbox;
    private ProjectsService projectsService = (final ProjectsService) SpringUtil.getApplicationContext().getBean("projectsService");
    private UsersService usersService = (final UsersService) SpringUtil.getApplicationContext().getBean("usersService");
    private List<Users> addUsersList;

    public EditProjectWindow() {
        this.project = new Projects();
        this.user = new Users();
    }

    @Override
    public void onAdd(final Event event) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectsListbox", this.getFellow("projectsListbox"));
        Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/projects/addProject.zul", null, map);
        win.doModal();
        refreshView();
    }

    /*
     *
     */
    @Override
    public void onUpdate(final Event event) throws Exception {
        final Listitem li = (Listitem) getParentComponent(event.getTarget(),
                Listitem.class);

        final Projects selectedProject = (Projects) (((Listbox) (li.getParent())).getModel().getElementAt(li.getIndex()));
        if (checkValues(selectedProject)) {
            return;
        } else {
            projectsService.update(selectedProject);
            refreshView();
        }
    }

    @Override
    public void onDelete(final Event event) throws Exception {
        Listitem li = (Listitem) getParentComponent(event.getTarget(), Listitem.class);
        final Projects selectedProject = (Projects) (((Listbox) (li.getParent())).getModel().getElementAt(li.getIndex()));
        String projectName = selectedProject.getName();
        int answer = Messagebox.show(Labels.getLabel("project.question.delete") + " '" + projectName + "' ?", Labels.getLabel("common.dialog.confirmation"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
        if (answer == Messagebox.YES) {
            try {
                boolean deleted = projectsService.delete(selectedProject);
                if (!deleted) {
                    throw new Exception("unable to delete project with id: " + selectedProject.getProjectsId());
                }
                li.getListbox().removeItemAt(li.getIndex());
            } catch (Exception e) {
                Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
            }
        }
        refreshView();
    }

    public void onProjectSelected(final Event event) throws Exception {
        Listbox psListbox = (Listbox) this.getFellow("projectsListbox");
        if (psListbox.getSelectedIndex() > -1) {
            List projects = (List) psListbox.getModel();
            setProject((Projects) projects.get(psListbox.getSelectedIndex()));
            ((Listbox)getFellow("usersListbox")).setVisible(getProject() != null);
            refreshUsersOnProject();
            refreshListOfUsersWhichAreNotInProject();
        }
    }

    public void onFocus(final Event event) throws Exception {
        Listitem li = (Listitem) getParentComponent(event.getTarget(), Listitem.class);
        li.getListbox().setSelectedItem(li);
        Events.sendEvent(li.getListbox(), new SelectEvent(Events.ON_SELECT, li.getListbox(), Collections.singleton(li)));
    }

    @Override
    public boolean checkValues(final Projects project) throws Exception {
        StringBuilder error = new StringBuilder();
        if (project.getName() == null || "".equals(project.getName())) {
            error.append(Labels.getLabel("project.error.insertName"));
        }
        if (project.getDateFrom() == null) {
            error.append("\n").append(Labels.getLabel("project.error.insertStartDate"));
        }
        if (project.getDateTo() == null) {
            error.append("\n").append(Labels.getLabel("project.error.insertStartDate"));

        } else if (project.getDateTo() != null && project.getDateTo().before(project.getDateFrom())) {
            error.append("\n").append(Labels.getLabel("project.error.badDateOrder"));
        }

        if (error.length() > 0) {
            Messagebox.show(error.toString(), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onAddUserToProject(final Event event) throws Exception {
        if (project.getUsersCollection() == null) {
            project.setUsersCollection(new HashSet<Users>());
        }
        if(!project.getUsersCollection().contains(this.user)){
            project.getUsersCollection().add(this.user);
        }
        projectsService.update(this.project);
        refreshUsersOnProject();
        refreshListOfUsersWhichAreNotInProject();

    }

    @Override
    public void onDeleteUserFromProject(final Event event) throws Exception {
        Listitem li = (Listitem) getParentComponent(event.getTarget(), Listitem.class);
        final Users selectedUser = (Users) (((Listbox) (li.getParent())).getModel().getElementAt(li.getIndex()));
        project.getUsersCollection().remove(selectedUser);
        projectsService.update(this.project);
        refreshUsersOnProject();
        refreshListOfUsersWhichAreNotInProject();
    }

    public void onDropUserOnList(final Event event) throws Exception {
        Component dragged = ((DropEvent) event).getDragged();
        if (!"allUsersListbox".equals(dragged.getParent().getId()) && dragged instanceof Listitem) {
            Listitem li = (Listitem) dragged;
            final Users selectedUser = (Users) (((Listbox) (li.getParent())).getModel().getElementAt(li.getIndex()));
            project.getUsersCollection().remove(selectedUser);
            projectsService.update(this.project);
            refreshUsersOnProject();
            refreshListOfUsersWhichAreNotInProject();
        }
    }

    public void onDropUserOnProject(final Event event) throws Exception {
        Component dragged = ((DropEvent) event).getDragged();
        if (!"usersListbox".equals(dragged.getParent().getId()) && dragged instanceof Listitem) {
            Listitem li = (Listitem) dragged;
            final Users selectedUser = (Users) (((Listbox) (li.getParent())).getModel().getElementAt(li.getIndex()));
            if (project.getUsersCollection() == null) {
                project.setUsersCollection(new HashSet<Users>());
            }
            if(!project.getUsersCollection().contains(selectedUser)){
                project.getUsersCollection().add(selectedUser);
            }
            projectsService.update(this.project);
            refreshUsersOnProject();
            refreshListOfUsersWhichAreNotInProject();
        }
    }

    private void refreshListOfUsersWhichAreNotInProject() {
        Combobox groupCombobox = (Combobox) this.getFellow("addUsersCombobox");
        List addUsersToProjectList = (List) groupCombobox.getModel();
        addUsersToProjectList.clear();
        //TODO add only thoose users, whose havent' been on project until now
        Collection<Users> listOfUsersAlreadyOnProject = this.project.getUsersCollection();
        List<Users> listOfAllUsers = usersService.readAll();
        // fill up only with users which are not part of project
        listOfAllUsers.removeAll(listOfUsersAlreadyOnProject);
        addUsersToProjectList.addAll(listOfAllUsers);
        groupCombobox.setSelectedIndex(-1);
        groupCombobox.setValue("");
    }

    /**
     * refresh list of users in selected project
     */
    private void refreshUsersOnProject() {
        final Listbox usersLisbox = (Listbox) this.getFellow("usersListbox");
        final List usersOnProjectList = (List) usersLisbox.getModel();
        usersOnProjectList.clear();
        Users[] array = this.project.getUsersCollection().toArray(new Users[0]);
        Listhead listhead = (Listhead) usersLisbox.getHeads().toArray()[0];
        Listheader usernameHeader = (Listheader) listhead.getChildren().get(1);// username
        boolean isAscending = "ascending".equals(usernameHeader.getSortDirection());
        Arrays.sort(array, new UsersComparator(isAscending, UsersComparator.SURNAME_COLUMN));
        usersOnProjectList.addAll(Arrays.asList(array));
    }

    /**
     * refresh list of projects
     */
    @Override
    public void refreshView() {
        final Listbox projectsLisbox = (Listbox) this.getFellow("projectsListbox");
        final List projectsList = (List) projectsLisbox.getModel();
        projectsList.clear();
        projectsList.addAll(projectsService.readAll());
    }

    private Component getParentComponent(final Component component,
final  Class clazz) throws Exception {
        int index = 0;
        while ((component != null) && !clazz.isInstance((component = component.getParent()))) {
            index++;
            if (index == 50) {
                throw new Exception("No parent " + clazz + " found!");
            }
        }
        if (component == null) {
            throw new Exception("No parent " + clazz + " found!");
        }
        return component;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(final Projects project) {
        this.project = project;
    }

    //getters & setters
    void setProjectsListbox(Listbox projectsListbox) {
        this.projectsListbox = projectsListbox;
    }

    public Listbox getProjectsListbox() {
        return projectsListbox;
    }

    public Listbox getUpdateProjectListbox() {
        return updateProjectListbox;
    }

    public void setUpdateProjectListbox(final Listbox updateProjectListbox) {
        this.updateProjectListbox = updateProjectListbox;

    }

    public Listbox getUsersListbox() {
        return usersListbox;
    }

    public void setUsersListbox(final Listbox usersListbox) {
        this.usersListbox = usersListbox;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(final Users user) {
        this.user = user;
    }

    public List<Users> getAddUsersList() {
        return addUsersList;
    }

    public void setAddUsersList(final List<Users> addUsersList) {
        this.addUsersList = addUsersList;

    }
}
