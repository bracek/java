package com.att.uchannels.service;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */

import com.att.uchannels.model.dao.GenericDAO;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface HibernateGenericService<T> {

    void create(T t);

    void createOrUpdate(T t);

    T read(Serializable id);

    T find(Serializable id);

    List<T> readAll();

    void update(T t);

    boolean delete(T t);

    /**
     * Sets service's DAO which will be used during the access to the database.
     *
     * @param genericDAO the serviceDAO to set.
     */
    void setServiceDAO(GenericDAO<T> genericDAO);

    /**
     * Prepares criteria from attributes which are set and perform select.
     *
     * @param t object which is used as criteria.
     * @return loaded object or null if not found.
     */
    List<T> readByCriteria(T t);

    /**
     * Use detached criteria for preparing WHERE condition and perform select.
     *
     * @param criteria object which is used as criteria.
     * @return loaded object or null if not found.
     */
    List<T> readByCriteria(DetachedCriteria criteria);
}
