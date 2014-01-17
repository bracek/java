package com.ixonos.skillnet.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.AclClass;
import com.ixonos.skillnet.logic.dao.AclClassDAO;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AclClassService;
/**
 *
 * @author magurja
 */
@Service("aclClassService")
public final class AclClassServiceImpl extends AbstractGenericService<AclClass> implements AclClassService {	
	
	@Resource
	protected AclClassDAO aclClassDAO;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("aclClassDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}
