package com.ixonos.skillnet.logic.service;

import com.ixonos.skillnet.logic.bean.Authority;

/**
 *
 * @author magurja
 */
public interface AuthorityService extends HibernateGenericService<Authority> {

    Authority getAuthority(String authority);
}
