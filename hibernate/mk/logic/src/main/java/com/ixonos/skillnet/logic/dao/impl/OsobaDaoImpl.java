package com.ixonos.skillnet.logic.dao.impl;

import com.ixonos.skillnet.logic.bean.Osoba;
import com.ixonos.skillnet.logic.dao.OsobaDao;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("osobaDao")
public class OsobaDaoImpl implements OsobaDao {

    protected HibernateTemplate template = null;

    // ----------------------------- CONSTRUCTORS ------------------------------
    /**
     *
     * @param SessionFactory sessionFactory
     */
    @Autowired
    public OsobaDaoImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * Sets Hibernate session factory.
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    /**
     * Find persons.
     */
    @SuppressWarnings("unchecked")
    public Osoba findPersonById(final Integer id) throws DataAccessException {
        return (Osoba) template.get(Osoba.class, id);
    }

    /**
     * Find persons.
     */
//    @Override
//    public Collection<Osoba> findPersons() throws DataAccessException {
//        return template.find("from Person p order by p.lastName, p.firstName");
//    }
//
//    /**
//     * Find persons by last name.
//     */
//    public Collection<Osoba> findPersonsByLastName(String lastName) throws DataAccessException {
//        return template.find("from Person p where p.lastName = ? order by p.lastName, p.firstName", lastName);
//    }
    /**
     * Saves person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(final Osoba person) {
        template.saveOrUpdate(person);
    }

    /**
     * Deletes person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(final Osoba person) {
        template.delete(person);
    }

    @Override
    public Collection<com.ixonos.skillnet.logic.bean.Osoba> findPersons() throws DataAccessException {
        return template.find("from Person p order by p.lastName, p.firstName");
    }

    @Override
    public Collection<com.ixonos.skillnet.logic.bean.Osoba> findPersonsByLastName(final String lastName) throws DataAccessException {
        return template.find("from Person p where p.lastName = ? order by p.lastName, p.firstName", lastName);
    }
}
