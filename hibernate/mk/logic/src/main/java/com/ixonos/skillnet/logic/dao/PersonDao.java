
package com.ixonos.skillnet.logic.dao;


import java.util.Collection;

import com.ixonos.skillnet.logic.bean.Address;
import com.ixonos.skillnet.logic.bean.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.security.annotation.Secured;


/**
 * Person DAO interface.
 * 
 * @author David Winterfeldt
 */
public interface PersonDao {

    /**
     * Find person by id.
     */
    public Person findPersonById(Integer id) throws DataAccessException;

    /**
     * Find persons.
     */
    public Collection<Person> findPersons() throws DataAccessException;
    
    /**
     * Find persons by last name.
     */
    public Collection<Person> findPersonsByLastName(String lastName) throws DataAccessException;

    /**
     * Saves person.
     */
    public Person save(Person person);

    /**
     * Deletes person.
     */
    @Secured ({"ROLE_ADMIN"})
    public void delete(Person person);

    /**
     * Saves address to person by adding or updating record.
     */
    public Person saveAddress(Integer id, Address address);

    /**
     * Deletes address.
     */
    @Secured ({"ROLE_ADMIN"})
    public Person deleteAddress(Integer id, Integer addressId);

}
