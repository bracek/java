package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.bean.GroupAuthority;
import java.util.List;

/**
 *
 * @author magurja
 */
public interface GroupAuthorityService extends HibernateGenericService<GroupAuthority> {

    boolean changeGroupAuthorities(String groupName, List<String> authorities) throws Exception;
}
