package fi.ixonos.projects.web.usermanagement.ldap;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.enumeration.ProjectsRole;
import fi.ixonos.projects.logic.security.ldap.ImportLdapUsersService;
import fi.ixonos.projects.logic.service.UsersService;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

/**
 * @author polakja
 *
 */
public class ImportUsersWindow extends Window {

    /**
     * Log4j logger for this class
     */
    private static Logger logger = Logger.getLogger(ImportUsersWindow.class);
    private static final long serialVersionUID = 1L;
    private BindingListModel ldapUsers;
    private String ldapName;
    private String ldapPassword;
    private final UsersService usersService = (final UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");
    private final ImportLdapUsersService ldapUsersService = (final ImportLdapUsersService) ProjectsApplicationContext.getApplicationContext().getBean("ldapUsersService");

    @SuppressWarnings("unchecked")
    public void loadLdapUsers(final Event event) throws Exception {

        AuthenticationWindow win = (AuthenticationWindow) Executions.createComponents("/WEB-INF/jsp/tiles/usermanagement/authPage.zul", null, new HashMap<String, Object>());
        win.setName(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        win.doModal();

        if (!win.isCanceled()) {
            ldapName = win.getName();
            ldapPassword = win.getPassword();

            if (ldapName != null && ldapPassword != null) {
                logger.debug("#loadLdapUsers: Name (" + ldapName + ") and password to connect to Ldap entered.");

                //TODO is "simple" config param?
                logger.debug("#loadLdapUsers: Connecting to Ldap...");
                List<String> ldapList = null;
                try {
                    ldapList = ldapUsersService.getUsersFullNameList(ldapName, ldapPassword);
                } catch (Exception e) {
                    logger.debug(e.getMessage(), e);
                    Messagebox.show(Labels.getLabel("user.error.ldapNotConnected"), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
                    return;
                }

                Collections.sort(ldapList);

                logger.debug("#loadLdapUsers: Data from LDAP loaded, size=" + ldapList.size());
                List<String> model = (List<String>) ((Listbox) this.getFellow("ldapUsersListBox")).getModel();
                model.clear();
                model.addAll(ldapList);

            } else {
                Messagebox.show(Labels.getLabel("user.error.emptyVal"), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void importLdapUsers(final Event event) throws Exception {
        Set/*<Listitem>*/ selectedUsers = /*(Set<Listitem>)*/ ((Listbox) this.getFellow("ldapUsersListBox")).getSelectedItems();
        logger.debug("#importLdapUsers: " + selectedUsers.size() + " users selected.");

        if (selectedUsers.size() <= 0) {
            Messagebox.show(Labels.getLabel("user.error.nothingSelected"), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
            return;
        }

        Set listItems = ((Listbox) this.getFellow("ldapUsersListBox")).getSelectedItems();

        int i = 0;
        for (Object listItem : listItems) {
            Listitem li = (Listitem) listItem;
            String userCNLabel = (String) li.getValue();

            List<Map<String, Object>> userList = null;
            try {
                userList = ldapUsersService.findUsersByCN(userCNLabel, ldapName, ldapPassword);
            } catch (Exception e) {
                logger.debug(e.getMessage(), e);
                Messagebox.show(Labels.getLabel("user.error.ldapNotConnected"), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
                break;
            }

            if (userList.size() <= 0) {
                logger.debug("#importLdapUsers: User not found. Search criteria='" + userCNLabel + "'");
                Messagebox.show(MessageFormat.format(Labels.getLabel("user.error.ldapUserNotFound"), userCNLabel), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);

            } else {
                if (userList.size() > 1) {
                    logger.info("#importLdapUsers: More then 1 user found in LDAP. Search criteria='" + userCNLabel + "'. Using first from list.");
                }

                Map<String, Object> userData = userList.get(0);
                String account = (String) userData.get("account");
                String password = (String) userData.get("plainPassword");
                String email = (String) userData.get("email");
                String name = (String) userData.get("firstName");
                String surname = (String) userData.get("lastName");
                String phone = (String) userData.get("phone");
                String location = (String) userData.get("location");
                String position = (String) userData.get("position");
                Boolean isGroupManager = (Boolean) userData.get("isGroupManager");

                try {
                    if(!usersService.isUserAlreadyRegistered(account)) {
                        if(isGroupManager)
                            usersService.addNewUser(account, password, name, surname, email, phone, location, position, true, null, Arrays.asList(new String[]{ProjectsRole.ROLE_GM, ProjectsRole.ROLE_ADMIN}));
                        else
                            usersService.addNewUser(account, password, name, surname, email, phone, location, position, true, null, Arrays.asList(new String[]{ProjectsRole.ROLE_USER}));
                        logger.debug("#importLdapUsers: User '" + account + "' successfully imported. Password is '" + password + "'");
                    } else {
                        Users user = usersService.getUser(account);
                        user.setName(name);
                        user.setSurname(surname);
                        user.setEmail(email);
                        user.setTelephoneNumber(phone);
                        user.setLocation(location);
                        user.setPosition(position);
                        usersService.update(user);
                    }
                    i++;

                } catch (Exception e) {
                    logger.error("#importLdapUsers: Error on synchronizing with LDAP", e);
                    Messagebox.show(MessageFormat.format(Labels.getLabel("user.error.importFailed"), account), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
                }
            }
        }

        Messagebox.show(MessageFormat.format(Labels.getLabel("user.information.importedUsers"), i), Labels.getLabel("common.dialog.information"), Messagebox.OK, Messagebox.INFORMATION);
        logger.info("#importLdapUsers: " + i + " users added into database.");

    }

    /**
     * @return the ldapUsers
     */
    public BindingListModel getLdapUsers() {
        return ldapUsers;
    }

    /**
     * @param ldapUsers the ldapUsers to set
     */
    public void setLdapUsers(final BindingListModel ldapUsers) {
        this.ldapUsers = ldapUsers;
    }

}
