package fi.ixonos.projects.logic.service.impl;


import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_ADMIN;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_GM;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_USER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.ixonos.projects.logic.bean.GroupMember;
import fi.ixonos.projects.logic.bean.Groups;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.GenericDAO;
import fi.ixonos.projects.logic.dao.GroupsDAO;
import fi.ixonos.projects.logic.service.AbstractGenericService;
import fi.ixonos.projects.logic.service.GroupMemberService;
import fi.ixonos.projects.logic.service.GroupsService;
import fi.ixonos.projects.logic.service.UsersService;

/**
 *
 * @author magurja
 */
@Service("groupsService")
public final class GroupsServiceImpl extends AbstractGenericService<Groups> implements GroupsService {	
	
	@Resource
	protected GroupsDAO groupsDAO;
	
	@Resource
	protected GroupMemberService groupMemberService;
	
	@Resource
	protected UsersService usersService;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("groupsDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
    
    @Secured({ROLE_ADMIN, ROLE_GM})
    @Transactional(readOnly = true)
    @Override
    public List<String> getGroupMembers(final String groupName) throws Exception {
    	Groups group = getGroup(groupName);
    	List<String> members = new ArrayList<String>();
    	List<GroupMember> groupMembers = group.getGroupMemberCollection();
    	for (GroupMember groupMember : groupMembers) {
    		members.add(groupMember.getUser().getUsername());
		}
    	return members;
    }

    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean changeGroupMembers(final String groupName,
final  List<String> members) throws Exception {
    	Groups group = getGroup(groupName);    	    	
    	// prepared list delete, add authorities
    	List<GroupMember> membersCollection = group.getGroupMemberCollection();
    	List<GroupMember> membersForDelete = new ArrayList<GroupMember>();
    	List<GroupMember> membersForAdd = new ArrayList<GroupMember>();
    	List<String> actualMembers = new ArrayList<String>();
    	for(GroupMember groupMember : membersCollection) {
    		actualMembers.add(groupMember.getUser().getUsername());
    		if (!members.contains(groupMember.getUser().getUsername())) {
    			membersForDelete.add(groupMember);
    		}
    	}
    	for(String member : members) {
    		if (!actualMembers.contains(member)) {    			
    			membersForAdd.add(getGroupMember(member));
    		}
    	}
    	// delete
    	for(GroupMember memberForDelete : membersForDelete) {
    		membersCollection.remove(memberForDelete);
    		groupMemberService.delete(memberForDelete);
    	}
    	// insert
    	for(GroupMember memberForAdd : membersForAdd) {
    		memberForAdd.setGroup(group);
    		membersCollection.add(memberForAdd);
    	}    	    	    	
    	update(group);     
    	return true;
    }
    
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean changeGroupMember(final String groupName,
final  String member,
final  boolean isAdded) throws Exception {
    	Groups group = getGroup(groupName);    	    	    	
    	List<GroupMember> membersCollection = group.getGroupMemberCollection();
    	if (isAdded) {	// insert
    		boolean isAlreadyAdded = false;
    		for(GroupMember groupMember : membersCollection) {    			
        		if (member.equals(groupMember.getUser().getUsername())) {
        			isAlreadyAdded = true;
        			break;
        		}
        	}
    		if (!isAlreadyAdded) {
	    		GroupMember memberForAdd = getGroupMember(member);
	    		memberForAdd.setGroup(group);
	    		membersCollection.add(memberForAdd);
    		}
    	} else {		// remove
    		GroupMember memberForDelete = null;
    		for(GroupMember groupMember : membersCollection) {
        		if (member.equals(groupMember.getUser().getUsername())) {
        			memberForDelete = groupMember;
        			break;
        		}
        	}    		
    		if (memberForDelete != null) {
    			membersCollection.remove(memberForDelete);
    			groupMemberService.delete(memberForDelete);
    		}
    	}
    	update(group);
    	return true;
    }
    
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean changeGroupMembers(final String groupName,
final  Map<String,
final Boolean> selectedUsersMap) throws Exception {
    	Groups group = getGroup(groupName);    	    	    	
    	List<GroupMember> membersCollection = group.getGroupMemberCollection();
    	for (Entry<String, Boolean> selectedUser : selectedUsersMap.entrySet()) {
    		String login = selectedUser.getKey();
    		Boolean isSelected = selectedUser.getValue();    	
    		boolean isAlreadyAdded = false;
    		for(GroupMember groupMember : membersCollection) {    			
        		if (login.equals(groupMember.getUser().getUsername())) {
        			isAlreadyAdded = true;
        			break;
        		}
        	}
    		if (isSelected && isAlreadyAdded) {
    			// nothing to do
    		} else if (isSelected && !isAlreadyAdded){
    			// add
    			GroupMember memberForAdd = getGroupMember(login);
	    		memberForAdd.setGroup(group);
	    		membersCollection.add(memberForAdd);
    		} else if (!isSelected && isAlreadyAdded){
    			// remove
    			GroupMember memberForDelete = null;
        		for(GroupMember groupMember : membersCollection) {
            		if (login.equals(groupMember.getUser().getUsername())) {
            			memberForDelete = groupMember;
            			break;
            		}
            	}    		
        		if (memberForDelete != null) {
        			membersCollection.remove(memberForDelete);
        			groupMemberService.delete(memberForDelete);
        		}
    		} else if (!isSelected && !isAlreadyAdded){
    			// nothing to do
    		}
		}
    	update(group);
    	return true;
    }
    
    private GroupMember getGroupMember(final String userName) throws Exception {
    	GroupMember gm = new GroupMember();
    	gm.setUser(usersService.getUser(userName));    	
    	return gm;
    }
    
    @Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
    @Override
    @Transactional(readOnly = true)
    public Groups getGroup(final String groupName) throws Exception {
    	Groups group = new Groups();
    	group.setGroupName(groupName);
    	List<Groups> groups = readByCriteria(group);
    	if (groups.size()==0) {
    		throw new Exception("group.error.groupNotFound");
    	} else if (groups.size()>1) {
    		throw new Exception("group.error.groupMore");
    	} else {
    		group = groups.get(0);    		
    	}
    	return group;
    }

    @Transactional(readOnly = true)
    @Override
    @Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	public String getTreeRootName(final String userName,
final  Properties props) throws Exception {
    	// read all groups of this user
		Users user = usersService.getUser(userName);
		List<GroupMember> groupMembers = user.getGroupMemberCollection();
		List<String> usersGroups = new ArrayList<String>();
		for (GroupMember groupMember : groupMembers) {
			String groupName = groupMember.getGroup().getGroupName();
			usersGroups.add(groupName);
		}
		// get list of trees which should see user
		List<String> trees = new ArrayList<String>();
		for(Entry entry : props.entrySet()) {
			String key = entry.getKey().toString();
			if (key.startsWith("TREE_") && !key.equals("TREE_DEFAULT") && !key.equals("TREE_PRIORITIES")) {
				String value = entry.getValue().toString();
				String[] groups = value.split(",");
				for (String group : groups) {
					if (usersGroups.contains(group)) {
						String tree = key.substring("TREE_".length());
						if (!trees.contains(tree)) trees.add(tree);
					}
				}
			}
		}	
		// get tree with higher priority
		if (trees.size() > 0) {						
			String[] priorityTrees = props.getProperty("TREE_PRIORITIES").toString().split(",");
			for (String priorityTree : priorityTrees) {
				if (trees.contains(priorityTree)) {
					return priorityTree;
				}
			}
		}
		return props.get("TREE_DEFAULT").toString();
	}
}
