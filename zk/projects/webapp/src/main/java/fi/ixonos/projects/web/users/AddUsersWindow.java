package fi.ixonos.projects.web.users;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.UsersService;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Window;

/**
 *
 * @author katrami
 */
public final class AddUsersWindow extends Window {

    private Users user;
    private Listbox usersListbox;
    private Listbox updateProjectListbox;
    private UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");
    private List<Users> addUsersList;

    public AddUsersWindow() {
        this.user = new Users();
    }

    //TODO add password from zk instead of hardcocde
    public void onAdd(Event event) throws Exception {
        if (checkValues(this.user)) {
            return;
        }
        try {
            this.user.setEnabled(Boolean.TRUE);
            this.user.setPassword("password");
            this.usersService.create(this.user);
        } catch (Exception e) {
            Messagebox.show(e.getMessage(), Labels.getLabel("userAddNew.error"), Messagebox.OK, Messagebox.ERROR);
            return;
        }
        this.detach();
    }

    public void onCancel(final Event event) {
        this.detach();
    }

    public boolean checkValues(Users t) throws Exception {
        StringBuilder error = new StringBuilder();
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            error.append(Labels.getLabel("users.error.insertName"));
        }

        final List<Users> usersList = usersService.readAll();
        boolean alreadyExistProjectName = false;
        for (Users projects : usersList) {
            if (projects.getUsername().equals(this.user.getUsername())) {
                alreadyExistProjectName = true;
                break;
            }
        }

        if (alreadyExistProjectName) {
            error.append("\n").append(Labels.getLabel("users.error.login.already.exists"));
        }


        if (error.length() > 0) {
            Messagebox.show(error.toString(), "Error", Messagebox.OK, Messagebox.ERROR);
            return true;
        } else {
            return false;
        }
    }

    public void onDelete(Event event)
            throws Exception {

        refreshView();
    }

    /**
     * refresh list of projects
     */
    public void refreshView() {

        final Listbox projectsLisbox = (Listbox) this.getFellow("usersListbox");
        final List projectsList = (List) projectsLisbox.getModel();
        projectsList.clear();
        projectsList.addAll(usersService.readAll());
    }

    public Listbox getUpdateProjectListbox() {
        return updateProjectListbox;
    }

    public void setUpdateProjectListbox(Listbox updateProjectListbox) {
        this.updateProjectListbox = updateProjectListbox;
    }

    public Listbox getUsersListbox() {
        return usersListbox;
    }

    public void setUsersListbox(Listbox usersListbox) {
        this.usersListbox = usersListbox;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getAddUsersList() {
        return addUsersList;
    }

    public void setAddUsersList(List<Users> addUsersList) {
        this.addUsersList = addUsersList;
    }
}
