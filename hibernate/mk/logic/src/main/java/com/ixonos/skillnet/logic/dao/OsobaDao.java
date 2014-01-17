package com.ixonos.skillnet.logic.dao;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import com.ixonos.skillnet.logic.bean.Osoba;

public interface OsobaDao {
    
    /**
     * Find persons.
     */
     Collection<Osoba> findPersons() throws DataAccessException;

    /**
     * Find persons by last name.
     */
     Collection<Osoba> findPersonsByLastName(final String lastName) throws DataAccessException;

    
}
