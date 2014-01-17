package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.dao.GenericDAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class AbstractGenericService<T> implements HibernateGenericService<T> {

    /**
     * DAO for a service.
     */
    protected GenericDAO<T> genericDAO;

    @Override
    public void create(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.create(t);
    }

    @Override
    public void createOrUpdate(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.createOrUpdate(t);
    }

    @Override
    public void update(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        this.genericDAO.update(t);
    }

    @Override
    public boolean delete(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        return this.genericDAO.delete(t);


    }

    @Override
    public List<T> readAll() {
        return this.genericDAO.readAll();
    }

    @Override
    public T read(final Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        return this.genericDAO.read(id);
    }

    @Override
    public T find(final Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("Given argument is null");
        }
        return this.genericDAO.find(id);
    }

    @Override
    public void setServiceDAO(final GenericDAO<T> genericDAO) {
        if (genericDAO == null) {
            throw new IllegalArgumentException("Given argument (genericDAO) is null.");
        }
        this.genericDAO = genericDAO;
    }

    @Override
    public List<T> readByCriteria(final T t) {
        if (genericDAO == null) {
            throw new IllegalArgumentException("Given argument (genericDAO) is null.");
        }
        return this.genericDAO.readByCriteria(t);
    }

    @Override
    public List<T> readByCriteria(final DetachedCriteria criteria) {
        if (genericDAO == null) {
            throw new IllegalArgumentException("Given argument (genericDAO) is null.");
        }
        return this.genericDAO.readByCriteria(criteria);
    }

//    private void flush() {
//        //Initialize Hibernate Template if Necessary
//        if (hibernateTemplate == null) {
//            try {
//                hibernateTemplate = new HibernateTemplate((SessionFactory) ProjectsApplicationContext.getApplicationContext().getBean("sessionFactory"));
//
//
//            } catch (Exception e) {
//            }
//        }
//        hibernateTemplate.flush();
//    }
}
