
package com.ixonos.skillnet.logic.service;

import com.ixonos.skillnet.logic.bean.Osoba;
import java.util.Collection;

/**
 *
 * @author katrami
 */
public interface OsobaService {
  
    /**
     * Find persons by last name.
     */
    public Collection<Osoba> findPersonsByLastName(final String lastName);

}
