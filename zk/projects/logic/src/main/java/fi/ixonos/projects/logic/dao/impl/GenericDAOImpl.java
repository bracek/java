package fi.ixonos.projects.logic.dao.impl;

/**
 *
 * @author magurja
 */

import fi.ixonos.projects.logic.dao.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**
 * Hibernate implementation of the Data Access Object for objects stored in database.
 *
 * @author magurja
 */
public abstract class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    public boolean create(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null.");
        }
        try {
            getHibernateTemplate().save(t);
            return true;
        } catch (DataAccessException dataAccessException) {
        	if (dataAccessException.getCause() instanceof ConstraintViolationException){
        		ConstraintViolationException constraintViolationException = (ConstraintViolationException)dataAccessException.getCause();
        		 throw new ConstraintViolationException(constraintViolationException.getMessage(),constraintViolationException.getSQLException(),constraintViolationException.getConstraintName());    

        	}
        	else{	
        		throw new RuntimeException(dataAccessException.getMessage());
        	} 
        }	
    }
    
    /**
     * {@inheritDoc GenericDAO<T>}
     */
    public boolean createOrUpdate(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null.");
        }
        try {
            getHibernateTemplate().saveOrUpdate(t);
            return true;
        }catch (DataAccessException dataAccessException) {
            throw new RuntimeException(dataAccessException.getMessage());
        }
    }

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    public boolean update(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null.");
        }
        try {
            getHibernateTemplate().update(t);
            return true;
        }catch (DataAccessException dataAccessException) {
            throw new RuntimeException(dataAccessException.getMessage());
        }
    }

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    public boolean delete(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null.");
        }
        try {
            getHibernateTemplate().delete(t);
            return true;
        } catch (DataAccessException dataAccessException) {
            throw new RuntimeException(dataAccessException.getMessage());
        }
    }

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    @SuppressWarnings("unchecked")
	public T read(final Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("Given argument is null.");
        }
        try {
            return (T) getHibernateTemplate().load(this.getEntityClass(), id);
        } catch (DataAccessException dataAccessException) {
            throw new RuntimeException(dataAccessException.getMessage());
        }
    }
    
    /**
     * {@inheritDoc GenericDAO<T>}
     */
    @SuppressWarnings("unchecked")
	public T find(final Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("Given argument is null.");
        }
        try {
            return (T) getHibernateTemplate().get(this.getEntityClass(), id);
        } catch (DataAccessException dataAccessException) {
            throw new RuntimeException(dataAccessException.getMessage());
        }
    }

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    @SuppressWarnings("unchecked")
	public List<T> readAll() {
        try {
            return getHibernateTemplate().loadAll(this.getEntityClass());
        } catch (DataAccessException dataAccessException) {
            throw new RuntimeException(dataAccessException.getMessage());
        }
    }

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    @SuppressWarnings("unchecked")
	public List<T> readByCriteria(T t) {
        try {
            return getSession().createCriteria(getEntityClass()).add(Example.create(t)).list();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * {@inheritDoc GenericDAO<T>}
     */
    @SuppressWarnings("unchecked")
	public List<T> readByCriteria(DetachedCriteria criteria) {
        try {
            return criteria.getExecutableCriteria(getSession()).list();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc GenericDAO<T>}
     */
    @SuppressWarnings("unchecked")
	public T readUniqueByCriteria(DetachedCriteria criteria) {
        try {
            return (T) criteria.getExecutableCriteria(getSession()).uniqueResult();
        } catch (ClassCastException e) {
            logger.error(e);
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Returns class of the entity which DAO is specified for.
     *
     * @return class of the entity which DAO is specified for.
     */
    @SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
}

