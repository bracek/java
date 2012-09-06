package com.ixonos.skillnet.web.usermanagement.edit;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.UsersService;

public class AddNewUserInitiator extends AnnotateDataBinderInit {
	@Resource
	private CodeTableService codeTableService = (CodeTableService)SpringUtil.getApplicationContext().getBean("codeTableService");

	@Resource
	protected UsersService usersService = (UsersService)SpringUtil.getApplicationContext().getBean("usersService");
	
	@Override
	public void doAfterCompose(Page page, Component[] comps) throws Exception {		
		List<Users> users = usersService.getAllUsers();
		List<CodeTable> authorities = new ArrayList<CodeTable>();
		try {
			authorities = codeTableService.getCodes("AUTHORITIES");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		List<Users> managerList = new ArrayList<Users>();
		managerList.add(null);
		for (Users user : users) {
			managerList.add(user);
		}
		page.setVariable("managerList", managerList);
		page.setVariable("authorities", authorities);		
		Listbox usersListbox = (Listbox)Executions.getCurrent().getArg().get("usersListbox");
		AddNewUserWindow addNewUserWindow = (AddNewUserWindow)comps[0];
		addNewUserWindow.setUsersListbox(usersListbox);		
		super.doAfterCompose(page, comps);	
	}
}
