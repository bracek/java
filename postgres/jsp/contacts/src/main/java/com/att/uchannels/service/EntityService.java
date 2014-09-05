package com.att.uchannels.service;

import com.att.uchannels.domain.Entity;

/**
 * @author katrami
 * @date Oct 13, 2010
 */
public interface EntityService extends HibernateGenericService<Entity> {

    Entity getEntity(final String username) throws Exception;


}
