package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.dao.CodeTableDAO;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.CodeTableService;

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
    public void setServiceDAO(final @Qualifier("codeTableDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
    
    @Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
    @Override
    @Transactional(readOnly = true)
    public CodeTable getCode(final String groupCode,
final  String code) throws Exception {
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
    public List<CodeTable> getCodes(final String groupCode) throws Exception {
        CodeTable codeTable = new CodeTable();
        codeTable.setGroupCode(groupCode);
        List<CodeTable> codeTables = codeTableDAO.readByCriteria(codeTable);
        CodeTable[] array = codeTables.toArray(new CodeTable[codeTables.size()]);
        Arrays.sort(array);
        return Arrays.asList(array);
    }
}
