package com.ixonos.skillnet.logic.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ixonos.skillnet.logic.bean.Groups;

/**
 * 
 * @author magurja
 */
public interface GroupsService extends HibernateGenericService<Groups> {
	 List<String> getGroupMembers(final String groupName)
			throws Exception;

	 boolean changeGroupMembers(final String groupName,
			final List<String> members) throws Exception;

	 Groups getGroup(final String groupName) throws Exception;

	 String getTreeRootName(final String userName, final Properties props)
			throws Exception;

	 boolean changeGroupMember(final String groupName,
			final String member, final boolean isAdded) throws Exception;

	 boolean changeGroupMembers(final String groupName,
			final Map<String, Boolean> selectedUsersMap) throws Exception;
}
