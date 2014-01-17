package com.ixonos.skillnet.logic.dao;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import com.ixonos.skillnet.logic.bean.Osoba;

public interface OsobaDao {
    
    /**
     * Find persons.
     */
    public Collection<Osoba> findPersons() throws DataAccessException;

    /**
     * Find persons by last name.
     */
    public Collection<Osoba> findPersonsByLastName(final String lastName) throws DataAccessException;

    
}
