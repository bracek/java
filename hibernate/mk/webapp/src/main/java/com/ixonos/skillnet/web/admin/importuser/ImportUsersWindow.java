package com.ixonos.skillnet.web.admin.importuser;

import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_BASE_DN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_BASE_DN_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_HOST;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_HOST_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_PORT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_PORT_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.ixonos.skillnet.logic.security.ldap.LdapClient;
import com.ixonos.skillnet.logic.service.ConfigService;
import com.ixonos.skillnet.logic.service.MailService;
import com.ixonos.skillnet.logic.service.UsersService;

/**
 * @author stibron
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
	private final ConfigService configService = (ConfigService) SpringUtil
			.getApplicationContext().getBean("configService");
	private final UsersService usersService = (UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");
	private final MailService mailService = (MailService) SpringUtil
			.getApplicationContext().getBean("mailService");

	@SuppressWarnings("unchecked")
	public void loadLdapUsers(final Event event) throws Exception {

		final AuthenticationWindow win = (AuthenticationWindow) Executions
				.createComponents("/WEB-INF/jsp/tiles/admin/authPage.zul",
						null, new HashMap<String, Object>());
		win.doModal();

		if (!win.isCanceled()) {
			ldapName = win.getName();
			ldapPassword = win.getPassword();

			if (ldapName != null && ldapPassword != null) {
				logger.debug("#loadLdapUsers: Name (" + ldapName
						+ ") and password to connect to Ldap entered.");
				final LdapClient ldapClient = new LdapClient();

				// TODO is "simple" config param?
				logger.debug("#loadLdapUsers: Connecting to Ldap...");
				List<String> ldapList = null;
				try {
					ldapList = ldapClient
							.getUsersFullNameList(
									"OU=Kosice,OU=Foreign,OU=People",
									getLdapURL(),
									"simple",
									"CN="
											+ ldapName
											+ ",OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local",
									ldapPassword);
				} catch (final Exception e) {
					Messagebox.show("Could not connect to LDAP", "Error",
							Messagebox.OK, Messagebox.ERROR);
					return;
				}

				Collections.sort(ldapList);

				logger.debug("#loadLdapUsers: Data from LDAP loaded, size="
						+ ldapList.size());
				final List<String> model = (List<String>) ((Listbox) this
						.getFellow("ldapUsersListBox")).getModel();
				model.clear();
				model.addAll(ldapList);

			} else {
				Messagebox.show("Wrong or empty name or password", "Error",
						Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void importLdapUsers(final Event event) throws Exception {
		final Set/* <Listitem> */selectedUsers = /* (Set<Listitem>) */((Listbox) this
				.getFellow("ldapUsersListBox")).getSelectedItems();
		logger.debug("#importLdapUsers: " + selectedUsers.size()
				+ " users selected.");

		if (selectedUsers.size() <= 0) {
			Messagebox.show("No record selected", "Error", Messagebox.OK,
					Messagebox.ERROR);
			return;
		}

		final Set listItems = ((Listbox) this.getFellow("ldapUsersListBox"))
				.getSelectedItems();

		final LdapClient ldapClient = new LdapClient();
		int i = 0;
		for (final Object listItem : listItems) {
			final Listitem li = (Listitem) listItem;
			final String userCNLabel = (String) li.getValue();

			List<Map<String, Object>> userList = null;
			try {
				userList = ldapClient
						.findUsersByCN(
								userCNLabel,
								getLdapURL(),
								"simple",
								"CN="
										+ ldapName
										+ ",OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local",
								ldapPassword);
			} catch (final Exception e) {
				Messagebox.show("Could not connect to LDAP", "Error",
						Messagebox.OK, Messagebox.ERROR);
				break;
			}

			if (userList.size() <= 0) {
				logger.debug("#importLdapUsers: User not found. Search criteria='"
						+ userCNLabel + "'");
				Messagebox.show("User not found for search criteria '"
						+ userCNLabel + "'", "Error", Messagebox.OK,
						Messagebox.ERROR);

			} else {
				if (userList.size() > 1) {
					logger.info("#importLdapUsers: More then 1 user found in LDAP. Search criteria='"
							+ userCNLabel + "'. Using first from list.");
				}

				final Map<String, Object> userData = userList.get(0);
				final String account = (String) userData.get("account");
				final String password = (String) userData.get("plainPassword");
				final String email = (String) userData.get("email");
				final String name = (String) userData.get("firstName");
				final String surname = (String) userData.get("lastName");
				final String phone = (String) userData.get("phone");

				try {
					usersService.addNewUser(account, password, name, surname,
							email, phone, null, null, true, null,
							Arrays.asList(new String[] { ROLE_USER }));
					logger.debug("#importLdapUsers: User '" + account
							+ "' successfully imported. Password is '"
							+ password + "'");

					// sending info mail to imported user
					final List<String> textParameters = new ArrayList<String>();
					textParameters.add(account);
					textParameters.add(email);
					textParameters.add(name + " " + surname);
					textParameters.add(password);

					mailService.sendNewAccountCreatedEmail(new InternetAddress(
							email, name + " " + surname), textParameters);

					i++;

				} catch (final Exception e) {
					logger.error("#importLdapUsers: Error on adding new user",
							e);
					Messagebox.show("User " + name + " " + surname
							+ " already exists in database.", "Info",
							Messagebox.OK, Messagebox.INFORMATION);
				}
			}
		}

		Messagebox.show("Number of successfully added users into database: "
				+ i, "Info", Messagebox.OK, Messagebox.INFORMATION);
		logger.info("#importLdapUsers: " + i + " users added into database.");

	}

	/**
	 * @return the ldapUsers
	 */
	public BindingListModel getLdapUsers() {
		return ldapUsers;
	}

	/**
	 * @param ldapUsers
	 *            the ldapUsers to set
	 */
	public void setLdapUsers(final BindingListModel ldapUsers) {
		this.ldapUsers = ldapUsers;
	}

	private String getLdapURL() {
		return "ldap://"
				+ configService.getStringProperty(LDAP_HOST, LDAP_HOST_DEFAULT)
				+ ":"
				+ configService.getIntProperty(LDAP_PORT, LDAP_PORT_DEFAULT)
				+ "/"
				+ configService.getStringProperty(LDAP_BASE_DN,
						LDAP_BASE_DN_DEFAULT);

	}
}
