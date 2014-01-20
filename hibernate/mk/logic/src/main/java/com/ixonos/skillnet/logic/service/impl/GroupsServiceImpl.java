package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.GroupMember;
import com.ixonos.skillnet.logic.bean.Groups;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.GroupsDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.GroupMemberService;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.logic.service.UsersService;

/**
 * 
 * @author magurja
 */
@Service("groupsService")
public final class GroupsServiceImpl extends AbstractGenericService<Groups>
		implements GroupsService {

	@Resource
	protected GroupsDAO groupsDAO;

	@Resource
	protected GroupMemberService groupMemberService;

	@Resource
	protected UsersService usersService;

	@Autowired
	@Override
	public void setServiceDAO(			final @Qualifier("groupsDAO") GenericDAO genericDAO) {
		super.setServiceDAO(genericDAO);
	}

	@Secured({ ROLE_ADMIN, ROLE_GM })
	@Transactional(readOnly = true)
	@Override
	public List<String> getGroupMembers(final String groupName)
			throws Exception {
		final Groups group = getGroup(groupName);
		final List<String> members = new ArrayList<String>();
		final List<GroupMember> groupMembers = group.getGroupMemberCollection();
		for (final GroupMember groupMember : groupMembers) {
			members.add(groupMember.getUser().getUsername());
		}
		return members;
	}

	@Secured(ROLE_ADMIN)
	@Transactional
	@Override
	public boolean changeGroupMembers(final String groupName,
			final List<String> members) throws Exception {
		final Groups group = getGroup(groupName);
		// prepared list delete, add authorities
		final List<GroupMember> membersCollection = group
				.getGroupMemberCollection();
		final List<GroupMember> membersForDelete = new ArrayList<GroupMember>();
		final List<GroupMember> membersForAdd = new ArrayList<GroupMember>();
		final List<String> actualMembers = new ArrayList<String>();
		for (final GroupMember groupMember : membersCollection) {
			actualMembers.add(groupMember.getUser().getUsername());
			if (!members.contains(groupMember.getUser().getUsername())) {
				membersForDelete.add(groupMember);
			}
		}
		for (final String member : members) {
			if (!actualMembers.contains(member)) {
				membersForAdd.add(getGroupMember(member));
			}
		}
		// delete
		for (final GroupMember memberForDelete : membersForDelete) {
			membersCollection.remove(memberForDelete);
			groupMemberService.delete(memberForDelete);
		}
		// insert
		for (final GroupMember memberForAdd : membersForAdd) {
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
			final String member,
 final boolean isAdded) throws Exception {
		final Groups group = getGroup(groupName);
		final List<GroupMember> membersCollection = group
				.getGroupMemberCollection();
		if (isAdded) { // insert
			boolean isAlreadyAdded = false;
			for (final GroupMember groupMember : membersCollection) {
				if (member.equals(groupMember.getUser().getUsername())) {
					isAlreadyAdded = true;
					break;
				}
			}
			if (!isAlreadyAdded) {
				final GroupMember memberForAdd = getGroupMember(member);
				memberForAdd.setGroup(group);
				membersCollection.add(memberForAdd);
			}
		} else { // remove
			GroupMember memberForDelete = null;
			for (final GroupMember groupMember : membersCollection) {
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
			final Map<String,
 Boolean> selectedUsersMap) throws Exception {
		final Groups group = getGroup(groupName);
		final List<GroupMember> membersCollection = group
				.getGroupMemberCollection();
		for (final Entry<String, Boolean> selectedUser : selectedUsersMap
				.entrySet()) {
			final String login = selectedUser.getKey();
			final Boolean isSelected = selectedUser.getValue();
			boolean isAlreadyAdded = false;
			for (final GroupMember groupMember : membersCollection) {
				if (login.equals(groupMember.getUser().getUsername())) {
					isAlreadyAdded = true;
					break;
				}
			}
			if (isSelected && isAlreadyAdded) {
				// nothing to do
			} else if (isSelected && !isAlreadyAdded) {
				// add
				final GroupMember memberForAdd = getGroupMember(login);
				memberForAdd.setGroup(group);
				membersCollection.add(memberForAdd);
			} else if (!isSelected && isAlreadyAdded) {
				// remove
				GroupMember memberForDelete = null;
				for (final GroupMember groupMember : membersCollection) {
					if (login.equals(groupMember.getUser().getUsername())) {
						memberForDelete = groupMember;
						break;
					}
				}
				if (memberForDelete != null) {
					membersCollection.remove(memberForDelete);
					groupMemberService.delete(memberForDelete);
				}
			} else if (!isSelected && !isAlreadyAdded) {
				// nothing to do
			}
		}
		update(group);
		return true;
	}

	private GroupMember getGroupMember(final String userName) throws Exception {
		final GroupMember gm = new GroupMember();
		gm.setUser(usersService.getUser(userName));
		return gm;
	}

	@Secured({ ROLE_ADMIN, ROLE_USER, ROLE_GM })
	@Override
	@Transactional(readOnly = true)
	public Groups getGroup(final String groupName) throws Exception {
		Groups group = new Groups();
		group.setGroupName(groupName);
		final List<Groups> groups = readByCriteria(group);
		if (groups.size() == 0) {
			throw new Exception("group.error.groupNotFound");
		} else if (groups.size() > 1) {
			throw new Exception("group.error.groupMore");
		} else {
			group = groups.get(0);
		}
		return group;
	}

	@Transactional(readOnly = true)
	@Override
	@Secured({ ROLE_ADMIN, ROLE_USER, ROLE_GM })
	public String getTreeRootName(final String userName,
 final Properties props)
			throws Exception {
		// read all groups of this user
		final Users user = usersService.getUser(userName);
		final List<GroupMember> groupMembers = user.getGroupMemberCollection();
		final List<String> usersGroups = new ArrayList<String>();
		for (final GroupMember groupMember : groupMembers) {
			final String groupName = groupMember.getGroup().getGroupName();
			usersGroups.add(groupName);
		}
		// get list of trees which should see user
		final List<String> trees = new ArrayList<String>();
		for (final Entry entry : props.entrySet()) {
			final String key = entry.getKey().toString();
			if (key.startsWith("TREE_") && !key.equals("TREE_DEFAULT")
					&& !key.equals("TREE_PRIORITIES")) {
				final String value = entry.getValue().toString();
				final String[] groups = value.split(",");
				for (final String group : groups) {
					if (usersGroups.contains(group)) {
						final String tree = key.substring("TREE_".length());
						if (!trees.contains(tree))
							trees.add(tree);
					}
				}
			}
		}
		// get tree with higher priority
		if (trees.size() > 0) {
			final String[] priorityTrees = props.getProperty("TREE_PRIORITIES")
					.toString().split(",");
			for (final String priorityTree : priorityTrees) {
				if (trees.contains(priorityTree)) {
					return priorityTree;
				}
			}
		}
		return props.get("TREE_DEFAULT").toString();
	}
}
