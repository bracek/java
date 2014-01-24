package com.ixonos.skillnet.web.usermanagement.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Groups;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.logic.service.UsersService;

public class UserManagementInitiator extends AnnotateDataBinderInit {

	@Resource
	private final CodeTableService codeTableService = (CodeTableService) SpringUtil
			.getApplicationContext().getBean("codeTableService");

	@Resource
	protected UsersService usersService = (UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");

	@Resource
	protected GroupsService groupsService = (GroupsService) SpringUtil
			.getApplicationContext().getBean("groupsService");

	@Override
	public void doAfterCompose(final Page page,
 final Component[] comps)
			throws Exception {
		final List<Users> userList = getOrderedListOfUsers();
		final List<CodeTable> authorityList = codeTableService
				.getCodes("AUTHORITIES");
		final List<Users> managerList = getOrderedListOfManagers();
		final List<Groups> groupList = getOrderedListOfGroups();

		page.setVariable("managerList", managerList);
		page.setVariable("userList", userList);
		page.setVariable("authorityList", authorityList);
		page.setVariable("groupList", groupList);

		super.doAfterCompose(page, comps);
	}

	private List<Users> getOrderedListOfUsers() {
		List<Users> users = usersService.getAllUsers();
		final Users[] usersArray = users.toArray(new Users[users.size()]);
		Arrays.sort(usersArray, new UserManagementComparator(true, "Username"));
		users = Arrays.asList(usersArray);
		return users;
	}

	private List<Users> getOrderedListOfManagers() {
		final List<Users> managerList = new ArrayList<Users>();
		managerList.add(null);
		managerList.addAll(usersService.getAllGroupManagers());
		return managerList;
	}

	private List<Groups> getOrderedListOfGroups() {
		List<Groups> groups = groupsService.readAll();
		final Groups[] groupsArray = groups.toArray(new Groups[groups.size()]);
		Arrays.sort(groupsArray, new Comparator<Groups>() {
			@Override
			public int compare(final Groups o1,
 final Groups o2) {
				return o1.getGroupName().compareTo(o2.getGroupName());
			}

		});
		groups = Arrays.asList(groupsArray);
		return groups;
	}
}
