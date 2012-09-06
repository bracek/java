package com.ixonos.skillnet.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.AclObjectIdentity;
import com.ixonos.skillnet.logic.dao.AclObjectIdentityDAO;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AclObjectIdentityService;

/**
 *
 * @author magurja
 */
@Service("aclObjectIdentityService")
public final class AclObjectIdentityServiceImpl extends AbstractGenericService<AclObjectIdentity> implements AclObjectIdentityService {	
	
	@Resource
	protected AclObjectIdentityDAO aclObjectIdentityDAO;

    @Autowired
    @Override
    public void setServiceDAO(@Qualifier("aclObjectIdentityDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}