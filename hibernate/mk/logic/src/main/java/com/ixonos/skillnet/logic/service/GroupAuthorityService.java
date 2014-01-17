package com.ixonos.skillnet.logic.service;

import com.ixonos.skillnet.logic.bean.GroupAuthority;
import java.util.List;

/**
 *
 * @author magurja
 */
public interface GroupAuthorityService extends HibernateGenericService<GroupAuthority> {

    boolean changeGroupAuthorities(String groupName, List<String> authorities) throws Exception;
}
