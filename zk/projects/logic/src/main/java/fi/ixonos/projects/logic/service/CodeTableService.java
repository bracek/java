
package fi.ixonos.projects.logic.service;

import java.util.List;

import fi.ixonos.projects.logic.bean.CodeTable;

/**
 *
 * @author magurja
 */
public interface CodeTableService extends HibernateGenericService<CodeTable> {
	 CodeTable getCode(final String groupCode, final String code) throws Exception;
    
     List<CodeTable> getCodes(final String groupCode) throws Exception;
}
