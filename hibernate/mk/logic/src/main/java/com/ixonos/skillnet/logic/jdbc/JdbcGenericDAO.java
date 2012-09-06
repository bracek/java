package com.ixonos.skillnet.logic.jdbc;

import java.util.List;

/**
 *
 * @author katrami
 */
public interface JdbcGenericDAO<T> {

    List<T> jdbcGetListOfIds();

    void jdbcDelete(final Integer id);

}
