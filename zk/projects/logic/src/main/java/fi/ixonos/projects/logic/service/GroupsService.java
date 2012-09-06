package fi.ixonos.projects.logic.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import fi.ixonos.projects.logic.bean.Groups;

/**
 *
 * @author magurja
 */
public interface GroupsService extends HibernateGenericService<Groups> {
	public List<String> getGroupMembers(String groupName) throws Exception;
	public boolean changeGroupMembers(String groupName, List<String> members) throws Exception;
    public Groups getGroup(String groupName) throws Exception;
    public String getTreeRootName(String userName, Properties props) throws Exception;
    public boolean changeGroupMember(String groupName, String member, boolean isAdded) throws Exception;
    public boolean changeGroupMembers(String groupName, Map<String,Boolean> selectedUsersMap) throws Exception;
}