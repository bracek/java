package com.ixonos.skillnet.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.AclEntry;
import com.ixonos.skillnet.logic.dao.AclEntryDAO;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AclEntryService;

/**
 *
 * @author magurja
 */
@Service("aclEntryService")
public final class AclEntryServiceImpl extends AbstractGenericService<AclEntry> implements AclEntryService {	
	
	@Resource
	protected AclEntryDAO aclEntryDAO;

    @Autowired
    @Override
    public void setServiceDAO(@Qualifier("aclEntryDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}