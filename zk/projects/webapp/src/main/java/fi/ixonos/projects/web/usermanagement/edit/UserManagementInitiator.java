package fi.ixonos.projects.web.usermanagement.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;

import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.bean.Groups;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.CodeTableService;
import fi.ixonos.projects.logic.service.GroupsService;
import fi.ixonos.projects.logic.service.UsersService;

public class UserManagementInitiator extends AnnotateDataBinderInit {

	@Resource
	private CodeTableService codeTableService = (final CodeTableService)SpringUtil.getApplicationContext().getBean("codeTableService");

	@Resource
	protected UsersService usersService = (final UsersService)SpringUtil.getApplicationContext().getBean("usersService");
	
	@Resource
	protected GroupsService groupsService = (final GroupsService)SpringUtil.getApplicationContext().getBean("groupsService");

	@Override
	public void doAfterCompose(final Page page,final  Component[] comps) throws Exception {		
		List<Users> userList = getOrderedListOfUsers();
		List<CodeTable> authorityList = codeTableService.getCodes("AUTHORITIES");			
		List<Users> managerList = getOrderedListOfManagers();
		List<Groups> groupList = getOrderedListOfGroups();
		
		page.setVariable("managerList", managerList);
		page.setVariable("userList", userList);
		page.setVariable("authorityList", authorityList);
		page.setVariable("groupList", groupList);
		
		super.doAfterCompose(page, comps);		
	}
	
	private List<Users> getOrderedListOfUsers() {
		List<Users> users = usersService.getAllUsers();
		Users[] usersArray = users.toArray(new Users[users.size()]);
		Arrays.sort(usersArray, new UserManagementComparator(true, "Username"));
		users = Arrays.asList(usersArray);
		return users;
	}

    @Deprecated
	private List<Users> getOrderedListOfManagers(final List<Users> users) {
		return this.getOrderedListOfManagers();
	}
	
	private List<Users> getOrderedListOfManagers() {
		List<Users> managerList = new ArrayList<Users>();
        managerList.add(null);
        managerList.addAll(usersService.getAllGroupManagers());
		return managerList;
	}

	private List<Groups> getOrderedListOfGroups() {
		List<Groups> groups = groupsService.readAll();
		Groups[] groupsArray = groups.toArray(new Groups[groups.size()]);
		Arrays.sort(groupsArray, new Comparator<Groups>() {
			public int compare(final Groups o1,final  Groups o2) {
				return o1.getGroupName().compareTo(o2.getGroupName());
			}
			
		});
		groups = Arrays.asList(groupsArray);
		return groups;
	}
}
