package com.ixonos.skillnet.web.usermanagement.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Groups;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.context.SkillnetApplicationContext;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.logic.service.MailService;
import com.ixonos.skillnet.logic.service.UsersService;

public class UserManagementWindow extends Window {

	private String lastEditedAuthoritiesUserName;
	private List<String> lastEditedAuthorities;

	/**
	 * Log4j logger for this class
	 */
	private static Logger logger = Logger.getLogger(UserManagementWindow.class);
	private static final long serialVersionUID = 1L;
	private final MailService mailService = (final MailService) SpringUtil
			.getApplicationContext().getBean("mailService");

	public Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(final Users user) {
		this.user = user;
	}

	public void onAuthoritySelect(final SelectEvent event) throws Exception {
		final Set selectedItems = event.getSelectedItems();
		final Iterator selectedIterator = selectedItems.iterator();
		final List<String> selectedAuthorities = new ArrayList<String>();
		while (selectedIterator.hasNext()) {
			final Listitem li = (Listitem) selectedIterator.next();
			final CodeTable selectedAuthority = (CodeTable) li.getValue();
			selectedAuthorities.add(selectedAuthority.getCode());
		}
		final Listbox lb = (Listbox) event.getTarget();
		final Users user = (Users) ((Listitem) getParentComponent(lb,
				Listitem.class)).getValue();
		final String userName = user.getUsername();
		if (!isLastEditedAuthorities(userName, selectedAuthorities)) {
			final StringBuilder sb = new StringBuilder();
			for (final String selectedAuthority : selectedAuthorities) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(selectedAuthority);
			}
			final Bandbox bandbox = (Bandbox) getParentComponent(lb,
					Bandbox.class);
			bandbox.setText(sb.toString());
			user.setAuthorities(sb.toString());
			lastEditedAuthorities = selectedAuthorities;
			lastEditedAuthoritiesUserName = userName;
			final UsersService usersService = (UsersService) SkillnetApplicationContext
					.getApplicationContext().getBean("usersService");
			try {
				usersService.updateUser(userName, selectedAuthorities);
			} catch (final Exception e) {
				Messagebox.show(Labels.getLabel(e.getMessage()),
						Labels.getLabel("userEdit.error"), Messagebox.OK,
						Messagebox.ERROR);
				refreshUsersListBox();
			}
		}
	}

	private Component getParentComponent(final Component component,
 final Class clazz)
			throws Exception {
		int index = 0;
		while (component != null
				&& !clazz.isInstance(component = component.getParent())) {
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

	private boolean isLastEditedAuthorities(final String userName,
			final List<String> authorities) {
		boolean isLastEdited = false;
		if (userName.equals(lastEditedAuthoritiesUserName)
				&& lastEditedAuthorities != null
				&& authorities.size() == lastEditedAuthorities.size()) {
			for (final String authority : authorities) {
				if (!lastEditedAuthorities.contains(authority)) {
					break;
				}
			}
			isLastEdited = true;
		}
		return isLastEdited;
	}

	public void onAddUser() throws Exception {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("usersListbox", this.getFellow("usersListbox"));
		final Window win = (Window) Executions.createComponents(
				"/WEB-INF/jsp/tiles/usermanagement/addNewUser.zul", null, map);
		win.doModal();
	}

	public void onDeleteUser(final MouseEvent event) throws Exception {
		final Listitem li = (Listitem) getParentComponent(event.getTarget(),
				Listitem.class);
		final Users user = (Users) li.getValue();
		final String userName = user.getUsername();
		final int answer = Messagebox.show(
				Labels.getLabel("userEdit.delete.question") + " '" + userName
						+ "' ?", Labels.getLabel("userEdit.confirmation"),
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (answer == Messagebox.YES) {
			final UsersService usersService = (UsersService) SpringUtil
					.getApplicationContext().getBean("usersService");
			try {
				final boolean deleted = usersService.removeUser(userName);
				li.getListbox().removeItemAt(li.getIndex());
				// sending email if user was deleted from list
				if (deleted) {
					try {
						final String account = userName;
						final String email = user.getEmail();
						final String name = user.getName();
						final String surname = user.getSurname();

						// sending info mail to user with changed password
						final List<String> textParameters = new ArrayList<String>();
						textParameters.add(account);
						textParameters.add(email);
						textParameters.add(name + " " + surname);
						mailService
								.sendUserAccountDeletedEmail(
										new InternetAddress(email, name + " "
												+ surname), textParameters);

					} catch (final Exception e) {
						logger.error(
								"#onDeleteUser: Error on sending email about deleted account",
								e);
					}
				}
			} catch (final Exception e) {
				Messagebox.show(Labels.getLabel(e.getMessage()),
						Labels.getLabel("userEdit.error"), Messagebox.OK,
						Messagebox.ERROR);
			}
		}
	}

	public void onChangePassword(final MouseEvent event) throws Exception {
		final Users user = (Users) ((Listitem) getParentComponent(
				event.getTarget(), Listitem.class)).getValue();
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", user.getUsername());
		map.put("showOldPassword", Boolean.FALSE);
		final Window win = (Window) Executions.createComponents(
				"/WEB-INF/jsp/tiles/usermanagement/changePassword.zul", null,
				map);
		win.doModal();
	}

	public void onManagerOrEnabledChange(final Event event) throws Exception {
		final Users user = (Users) ((Listitem) getParentComponent(
				event.getTarget(), Listitem.class)).getValue();
		final String userName = user.getUsername();
		final Users manager = user.getManager();
		final Boolean enabled = user.getEnabled();
		final UsersService usersService = (UsersService) SpringUtil
				.getApplicationContext().getBean("usersService");
		try {
			usersService.updateUser(userName, manager, enabled);
		} catch (final Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()),
					Labels.getLabel("userEdit.error"), Messagebox.OK,
					Messagebox.ERROR);
			refreshUsersListBox();
		}
	}

	private void refreshUsersListBox() {
		final UsersService usersService = (UsersService) SkillnetApplicationContext
				.getApplicationContext().getBean("usersService");
		final List<Users> users = usersService.getAllUsers();
		((Listbox) this.getFellow("usersListbox"))
				.setModel(new BindingListModelList(users, true));
	}

	public void onGroupSelect(final Event event) throws Exception {
		selectGroupMembers((Combobox) event.getTarget());
	}

	private void selectGroupMembers(final Combobox groupCombobox)
			throws Exception {
		if (groupCombobox.getSelectedItem() == null
				|| groupCombobox.getSelectedItem().getValue() == null) {
			return;
		}
		final Groups group = (Groups) groupCombobox.getSelectedItem()
				.getValue();
		final String groupName = group.getGroupName();
		final GroupsService groupsService = (GroupsService) SpringUtil
				.getApplicationContext().getBean("groupsService");
		List<String> members;
		try {
			members = groupsService.getGroupMembers(groupName);
		} catch (final Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()),
					Labels.getLabel("userEdit.error"), Messagebox.OK,
					Messagebox.ERROR);
			return;
		}
		final Iterator childrenIterator = ((Listbox) this
				.getFellow("usersListbox")).getVisibleChildrenIterator();
		while (childrenIterator.hasNext()) {
			final Object listItem = childrenIterator.next();
			if (listItem instanceof Listitem) {
				final Users user = (Users) ((Listitem) listItem).getValue();
				final String userName = user.getUsername();
				((Listitem) listItem).setSelected(members.contains(userName));
			}
		}
	}

	public void onUserSelect(final SelectEvent event) throws Exception {
		final Comboitem selectedItem = ((Combobox) this
				.getFellow("groupCombobox")).getSelectedItem();
		if (selectedItem == null || selectedItem.getValue() == null) {
			return;
		}
		final String groupName = ((Groups) selectedItem.getValue())
				.getGroupName();
		final Map<String, Boolean> selectedUsersMap = new HashMap<String, Boolean>();
		final Iterator childrenIterator = ((Listbox) this
				.getFellow("usersListbox")).getVisibleChildrenIterator();
		while (childrenIterator.hasNext()) {
			final Object listItem = childrenIterator.next();
			if (listItem instanceof Listitem) {
				final Users user = (Users) ((Listitem) listItem).getValue();
				final String userName = user.getUsername();
				final Boolean selected = ((Listitem) listItem).isSelected();
				selectedUsersMap.put(userName, selected);
			}
		}
		final GroupsService groupsService = (GroupsService) SkillnetApplicationContext
				.getApplicationContext().getBean("groupsService");
		try {
			groupsService.changeGroupMembers(groupName, selectedUsersMap);
		} catch (final Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()),
					Labels.getLabel("userEdit.error"), Messagebox.OK,
					Messagebox.ERROR);
			refreshUsersListBox();
		}
	}

	public void onPaging() throws Exception {
		selectGroupMembers((Combobox) this.getFellow("groupCombobox"));
	}
}
