package fi.ixonos.projects.logic.service.impl;

import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_ADMIN;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_GM;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_USER;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.dao.CodeTableDAO;
import fi.ixonos.projects.logic.dao.GenericDAO;
import fi.ixonos.projects.logic.service.AbstractGenericService;
import fi.ixonos.projects.logic.service.CodeTableService;

/**
 *
 * @author magurja
 */
@Service("codeTableService")
public final class CodeTableServiceImpl extends AbstractGenericService<CodeTable> implements CodeTableService {

    @Resource
    protected CodeTableDAO codeTableDAO;

    @Autowired
    @Override
    public void setServiceDAO(@Qualifier("codeTableDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    @Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
    @Override
    @Transactional(readOnly = true)
    public CodeTable getCode(String groupCode, String code) throws Exception {
        CodeTable codeTable = new CodeTable();
        codeTable.setGroupCode(groupCode);
        codeTable.setCode(code);
        List<CodeTable> codeTables = codeTableDAO.readByCriteria(codeTable);
        if (codeTables.size() == 1) {
            codeTable = codeTables.get(0);
        } else if (codeTables.size() == 0) {
            throw new Exception("Code not found or is not unique!");
        }
        return codeTable;
    }

    @Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
    @Override
    @Transactional(readOnly = true)
    public List<CodeTable> getCodes(String groupCode) throws Exception {
        CodeTable codeTable = new CodeTable();
        codeTable.setGroupCode(groupCode);
        List<CodeTable> codeTables = codeTableDAO.readByCriteria(codeTable);
        CodeTable[] array = codeTables.toArray(new CodeTable[codeTables.size()]);
        Arrays.sort(array);
        return Arrays.asList(array);
    }
}
