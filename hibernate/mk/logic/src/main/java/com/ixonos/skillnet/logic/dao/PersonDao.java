
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
     Person findPersonById(final Integer id) throws DataAccessException;

    /**
     * Find persons.
     */
     Collection<Person> findPersons() throws DataAccessException;
    
    /**
     * Find persons by last name.
     */
     Collection<Person> findPersonsByLastName(final String lastName) throws DataAccessException;

    /**
     * Saves person.
     */
     Person save(final Person person);

    /**
     * Deletes person.
     */
    @Secured ({"ROLE_ADMIN"})
     void delete(final Person person);

    /**
     * Saves address to person by adding or updating record.
     */
     Person saveAddress(final Integer id,final  Address address);

    /**
     * Deletes address.
     */
    @Secured ({"ROLE_ADMIN"})
     Person deleteAddress(final Integer id,final  Integer addressId);

}
