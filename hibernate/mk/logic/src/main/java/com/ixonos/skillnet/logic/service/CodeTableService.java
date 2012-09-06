
package com.ixonos.skillnet.logic.service;

import java.util.List;

import com.ixonos.skillnet.logic.bean.CodeTable;

/**
 *
 * @author magurja
 */
public interface CodeTableService extends HibernateGenericService<CodeTable> {
	public CodeTable getCode(final String groupCode, final String code) throws Exception;
    
    public List<CodeTable> getCodes(final String groupCode) throws Exception;
}
