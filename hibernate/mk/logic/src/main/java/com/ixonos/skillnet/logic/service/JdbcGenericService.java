package com.ixonos.skillnet.logic.service;

import java.util.List;

/**
 *
 * @author katrami
 */
public interface JdbcGenericService<T> {

    public List<T> jdbcGetListOfIds();

    void jdbcDelete(final Integer id);

}
