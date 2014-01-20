package com.ixonos.skillnet.logic.dao.impl;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ixonos.skillnet.logic.bean.Address;
import com.ixonos.skillnet.logic.bean.Person;
import com.ixonos.skillnet.logic.dao.PersonDao;

/**
 * Person DAO implementation.
 *
 * @author David Winterfeldt
 */
@Repository
@Transactional(readOnly = true)
public class PersonDaoImpl implements PersonDao {

    protected HibernateTemplate template = null;

    /**
     * Sets Hibernate session factory.
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    @Autowired
    public PersonDaoImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * Find persons.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Person findPersonById(final Integer id) throws DataAccessException {
        return (Person) template.get(Person.class, id);
    }

    /**
     * Find persons.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Person> findPersons() throws DataAccessException {
        return template.find("from Person p order by p.lastName, p.firstName");
    }

    /**
     * Find persons by last name.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Person> findPersonsByLastName(final String lastName) throws DataAccessException {
        return template.find("from Person p where p.lastName = ? order by p.lastName, p.firstName", lastName);
    }

    /**
     * Saves person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Person save(final Person person) {
        Person result = (Person) template.merge(person);

        return result;
    }

    /**
     * Deletes person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(final Person person) {
        template.delete(person);
    }

    /**
     * Saves address to person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Person saveAddress(final Integer id,
final  Address address) {
        Person person = findPersonById(id);

        if (person.getAddresses().contains(address)) {
            person.getAddresses().remove(address);
        }

        person.getAddresses().add(address);

        return save(person);
    }

    /**
     * Deletes address to person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Person deleteAddress(final Integer id,
final  Integer addressId) {
        Person person = findPersonById(id);

        Address address = new Address();
        address.setId(addressId);

        if (person.getAddresses().contains(address)) {
            person.getAddresses().remove(address);
        }

        return save(person);
    }
}
