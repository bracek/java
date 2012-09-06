package com.ixonos.skillnet.logic.service;

import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.jdbc.JdbcGenericDAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author magurja
 */
public abstract class AbstractGenericService<T> implements HibernateGenericService<T>, JdbcGenericService<T> {

    /**
     * JDBC dao for service
     */
    protected JdbcGenericDAO<T> jdbcGenericDAO;
    /**
     * DAO for a service.
     */
    protected GenericDAO<T> genericDAO;

    public void create(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.create(t);
    }
    
    public void createOrUpdate(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.createOrUpdate(t);
    }

    public void update(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.update(t);
    }

    public void delete(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.delete(t);
    }

    public List<T> readAll() {
        return this.genericDAO.readAll();
    }

    public T read(Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        return this.genericDAO.read(id);
    }
    
    public T find(Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        return this.genericDAO.find(id);
    }

    public void setServiceDAO(final GenericDAO<T> genericDAO) {
        if (genericDAO == null) {
            throw new IllegalArgumentException("Given argument (genericDAO) is null.");
        }
        this.genericDAO = genericDAO;
    }

    public void setJdbcGenericDAO(JdbcGenericDAO<T> jdbcGenericDAO) {
        if (jdbcGenericDAO == null) {
            throw new IllegalArgumentException("Given argument (jdbcGenericDAO) is null.");
        }
        this.jdbcGenericDAO = jdbcGenericDAO;
    }
   
    public List<T> readByCriteria(T t) {
        if (genericDAO == null) {
            throw new IllegalArgumentException("Given argument (genericDAO) is null.");
        }
        return this.genericDAO.readByCriteria(t);
    }

    public List<T> readByCriteria(DetachedCriteria criteria) {
        if (genericDAO == null) {
            throw new IllegalArgumentException("Given argument (genericDAO) is null.");
        }
        return this.genericDAO.readByCriteria(criteria);
    }   

    public List<T> jdbcGetListOfIds() {
        if (jdbcGenericDAO == null) {
            throw new IllegalArgumentException("Given argument (jdbcGenericDAO) is null.");
        }
        return jdbcGenericDAO.jdbcGetListOfIds();
    }

    public void jdbcDelete(final Integer id) {
        if (jdbcGenericDAO == null) {
            throw new IllegalArgumentException("Given argument (jdbcGenericDAO) is null.");
        }
        jdbcGenericDAO.jdbcDelete(id);
    }

}
