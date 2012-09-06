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
    private final MailService mailService = (MailService) SpringUtil.getApplicationContext().getBean("mailService");
    
	
	public Users user;	

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public void onAuthoritySelect(SelectEvent event) throws Exception {		
		Set selectedItems = event.getSelectedItems();
		Iterator selectedIterator = selectedItems.iterator();
		List<String> selectedAuthorities = new ArrayList<String>();
		while(selectedIterator.hasNext()) {
			Listitem li = (Listitem)selectedIterator.next();
			CodeTable selectedAuthority = (CodeTable)li.getValue();
			selectedAuthorities.add(selectedAuthority.getCode());
		}
		Listbox lb = (Listbox)event.getTarget();
		Users user = (Users)((Listitem)getParentComponent(lb, Listitem.class)).getValue();
		String userName = user.getUsername();
		if (!isLastEditedAuthorities(userName, selectedAuthorities)) {					
			StringBuilder sb = new StringBuilder();
			for (String selectedAuthority : selectedAuthorities) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(selectedAuthority);
			}
			Bandbox bandbox = (Bandbox)getParentComponent(lb, Bandbox.class);
			bandbox.setText(sb.toString());
			user.setAuthorities(sb.toString());
			this.lastEditedAuthorities = selectedAuthorities;
			this.lastEditedAuthoritiesUserName = userName;
			UsersService usersService = (UsersService)SkillnetApplicationContext.getApplicationContext().getBean("usersService");
			try {
				usersService.updateUser(userName, selectedAuthorities);
			} catch(Exception e) {
				Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("userEdit.error"), Messagebox.OK, Messagebox.ERROR);
				refreshUsersListBox();
			}
		}		
	}
			
	private Component getParentComponent(Component component, Class clazz) throws Exception {
		int index = 0;				
		while((component != null) && !clazz.isInstance((component = component.getParent()))) {
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
	
	private boolean isLastEditedAuthorities(String userName, List<String> authorities) {
		boolean isLastEdited = false;
		if (userName.equals(this.lastEditedAuthoritiesUserName) && this.lastEditedAuthorities != null && authorities.size() == this.lastEditedAuthorities.size()) {
			for (String authority : authorities) {
				if (!this.lastEditedAuthorities.contains(authority)) {					
					break;
				}
			}
			isLastEdited = true;
		}
		return isLastEdited;
	}

	public void onAddUser() throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usersListbox", this.getFellow("usersListbox"));
		Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/usermanagement/addNewUser.zul", null, map);
		win.doModal();	
	}
		
	public void onDeleteUser(MouseEvent event) throws Exception
	{
		Listitem li = (Listitem)getParentComponent(event.getTarget(), Listitem.class);
		Users user = (Users)li.getValue();
		String userName = user.getUsername();
		int answer = Messagebox.show(Labels.getLabel("userEdit.delete.question") + " '" + userName + "' ?", Labels.getLabel("userEdit.confirmation"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (answer == Messagebox.YES) {
			UsersService usersService = (UsersService)SpringUtil.getApplicationContext().getBean("usersService");
			try {
				boolean deleted=usersService.removeUser(userName);
				li.getListbox().removeItemAt(li.getIndex());
				//sending email if user was deleted from list
				if (deleted){
					try{
						String account = userName;
				        String email =  user.getEmail();
				        String name =  user.getName();
				        String surname =  user.getSurname();

				        //sending info mail to user with changed password
				        List<String> textParameters = new ArrayList<String>();
				        textParameters.add(account);
				        textParameters.add(email);
				        textParameters.add(name + " " + surname);
				        mailService.sendUserAccountDeletedEmail(new InternetAddress(email, name + " " + surname), textParameters);
					
					} catch (Exception e) {
	                    logger.error("#onDeleteUser: Error on sending email about deleted account", e);
	                }
				}
			} catch(Exception e) {
				Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("userEdit.error"), Messagebox.OK, Messagebox.ERROR);
			}										
		}			
	}
	
	public void onChangePassword(MouseEvent event) throws Exception {
		Users user = (Users)((Listitem)getParentComponent(event.getTarget(), Listitem.class)).getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", user.getUsername());
		map.put("showOldPassword", Boolean.FALSE);
		Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/usermanagement/changePassword.zul", null, map);
		win.doModal();		
	}	
	
	public void onManagerOrEnabledChange(Event event) throws Exception {		
		Users user = (Users)((Listitem)getParentComponent(event.getTarget(), Listitem.class)).getValue();		
		String userName = user.getUsername();
		Users manager = user.getManager();
		Boolean enabled = user.getEnabled();
		UsersService usersService = (UsersService)SpringUtil.getApplicationContext().getBean("usersService");
		try {
			usersService.updateUser(userName, manager, enabled);
		} catch(Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("userEdit.error"), Messagebox.OK, Messagebox.ERROR);
			refreshUsersListBox();
		}	
	}
	
	private void refreshUsersListBox() {
		UsersService usersService = (UsersService)SkillnetApplicationContext.getApplicationContext().getBean("usersService");
		List<Users> users = usersService.getAllUsers();	
		((Listbox)this.getFellow("usersListbox")).setModel(new BindingListModelList(users, true));
	}
	
	public void onGroupSelect(Event event) throws Exception {
		selectGroupMembers((Combobox)event.getTarget());
	}
	
	private void selectGroupMembers(Combobox groupCombobox) throws Exception {
		if (groupCombobox.getSelectedItem() == null || groupCombobox.getSelectedItem().getValue() == null) {
			return;
		}
		Groups group = (Groups)groupCombobox.getSelectedItem().getValue();
		String groupName = group.getGroupName();			
		GroupsService groupsService = (GroupsService)SpringUtil.getApplicationContext().getBean("groupsService");
		List<String> members;
		try {
			members = groupsService.getGroupMembers(groupName);
		} catch (Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("userEdit.error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		Iterator childrenIterator = ((Listbox)this.getFellow("usersListbox")).getVisibleChildrenIterator();
		while(childrenIterator.hasNext()) {
			Object listItem = childrenIterator.next();
			if (listItem instanceof Listitem) {
				Users user = (Users)((Listitem)listItem).getValue();
				String userName = user.getUsername();
				((Listitem)listItem).setSelected(members.contains(userName));
			}
		}
	}
	
	public void onUserSelect(SelectEvent event) throws Exception {		
		Comboitem selectedItem = ((Combobox)this.getFellow("groupCombobox")).getSelectedItem();
		if (selectedItem == null || selectedItem.getValue() == null) {
			return;
		}
		String groupName = ((Groups)selectedItem.getValue()).getGroupName();
		Map<String, Boolean> selectedUsersMap = new HashMap<String, Boolean>();
		Iterator childrenIterator = ((Listbox)this.getFellow("usersListbox")).getVisibleChildrenIterator();
		while(childrenIterator.hasNext()) {
			Object listItem = childrenIterator.next();
			if (listItem instanceof Listitem) {
				Users user = (Users)((Listitem)listItem).getValue();
				String userName = user.getUsername();
				Boolean selected = ((Listitem)listItem).isSelected();
				selectedUsersMap.put(userName, selected);
			}
		}
		GroupsService groupsService = (GroupsService)SkillnetApplicationContext.getApplicationContext().getBean("groupsService");
		try {
			groupsService.changeGroupMembers(groupName, selectedUsersMap);
		} catch (Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("userEdit.error"), Messagebox.OK, Messagebox.ERROR);
			refreshUsersListBox();
		}
	}
	
	public void onPaging() throws Exception {
		selectGroupMembers((Combobox)this.getFellow("groupCombobox"));
	}
}
