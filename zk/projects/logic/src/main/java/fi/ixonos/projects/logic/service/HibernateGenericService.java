package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.dao.GenericDAO;
import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

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
     * @param genericDAO the serviceDAO to set.
     */
    void setServiceDAO(GenericDAO<T> genericDAO);

    /**
     * Prepares criteria from attributes which are set and perform select.
     * @param t object which is used as criteria.
     * @return loaded object or null if not found.
     */
    List<T> readByCriteria(T t);

    /**
     * Use detached criteria for preparing WHERE condition and perform select.
     * @param criteria object which is used as criteria.
     * @return loaded object or null if not found.
     */
    List<T> readByCriteria(DetachedCriteria criteria);
}
