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
	public List<String> getGroupMembers(final String groupName) throws Exception;
	public boolean changeGroupMembers(final String groupName,final  List<String> members) throws Exception;
    public Groups getGroup(final String groupName) throws Exception;
    public String getTreeRootName(final String userName,final  Properties props) throws Exception;
    public boolean changeGroupMember(final String groupName,final  String member,final  boolean isAdded) throws Exception;
    public boolean changeGroupMembers(final String groupName,final  Map<String,final Boolean> selectedUsersMap) throws Exception;
}
