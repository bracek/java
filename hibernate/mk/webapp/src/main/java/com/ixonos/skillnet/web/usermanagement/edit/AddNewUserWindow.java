package com.ixonos.skillnet.web.usermanagement.edit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;

public class AddNewUserWindow extends Window {	
	private Users user;
	private Listbox usersListbox;
	
	public AddNewUserWindow() {
		this.user = new Users();
		this.user.setEnabled(Boolean.TRUE);
	}
		
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void onAddNewUser() throws Exception {
		String login = user.getUsername();
		if (login == null || login.trim().length()==0) {
			Messagebox.show(Labels.getLabel("userAddNew.error.loginNotSpec"), Labels.getLabel("userAddNew.error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		Textbox passwordBox = (Textbox)this.getFellow("password");
		Textbox confirmationBox = (Textbox)this.getFellow("confirmation");
		if (passwordBox.getValue().trim().length()==0 ||  confirmationBox.getValue().trim().length()==0) {			
			Messagebox.show(Labels.getLabel("userAddNew.error.passwNotSpec"), Labels.getLabel("userAddNew.error"), Messagebox.OK, Messagebox.ERROR);
			return;
		} 
		if (!passwordBox.getValue().equals(confirmationBox.getValue())) {			
			Messagebox.show(Labels.getLabel("userAddNew.error.passNotSame"), Labels.getLabel("userAddNew.error"), Messagebox.OK, Messagebox.ERROR);
			return;
		} 		
		String password = user.getPassword();
		Boolean enabled = user.getEnabled();
		Users manager = user.getManager();		
		Listbox lb = (Listbox)this.getFellow("usersAuthorities");
		Set selectedItems = lb.getSelectedItems();
		Iterator selectedIterator = selectedItems.iterator();
		List<String> authorities = new ArrayList<String>();
		while(selectedIterator.hasNext()) {
			Listitem li = (Listitem)selectedIterator.next();
			CodeTable selectedAuthority = (CodeTable)li.getValue();
			authorities.add(selectedAuthority.getCode());
		}		
		UsersService usersService = (UsersService)SpringUtil.getApplicationContext().getBean("usersService");
		try {
			if (!usersService.isUserAlreadyRegistered(login)) {
				//TODO set right user properties
				usersService.addNewUser(login, password, "<name>", "<surname>", "<email>", "<phone>", null, null, enabled, manager, authorities);
			} else {
				Messagebox.show(Labels.getLabel("userAddNew.error.userExist"), Labels.getLabel("userAddNew.error"), Messagebox.OK, Messagebox.ERROR);
				return;
			}
		} catch(Exception e) {
			Messagebox.show(Labels.getLabel(e.getMessage()), Labels.getLabel("userAddNew.error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		this.detach();
		// refresh listbox with users
		List<Users> users = usersService.getAllUsers();	
		this.getUsersListbox().setModel(new BindingListModelList(users, true));		
	}

	public void onAuthoritySelect(SelectEvent event) throws Exception {
		Set selectedItems = event.getSelectedItems();
		Iterator selectedIterator = selectedItems.iterator();
		List<String> selectedAuthorities = new ArrayList<String>();
		while (selectedIterator.hasNext()) {
			Listitem li = (Listitem) selectedIterator.next();
			CodeTable selectedAuthority = (CodeTable) li.getValue();
			selectedAuthorities.add(selectedAuthority.getCode());
		}
		Listbox lb = (Listbox) event.getTarget();

		StringBuilder sb = new StringBuilder();
		for (String selectedAuthority : selectedAuthorities) {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(selectedAuthority);
		}
		Bandbox bandbox = (Bandbox) getParentComponent(lb, Bandbox.class);
		bandbox.setText(sb.toString());

	}

	private Component getParentComponent(Component component, Class clazz)
			throws Exception {
		int index = 0;
		while ((component != null)
				&& !clazz.isInstance((component = component.getParent()))) {
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

	public void setUsersListbox(Listbox usersListbox) {
		this.usersListbox = usersListbox;
	}
	
	public Listbox getUsersListbox() {
		return this.usersListbox;
	}
}
