package com.ixonos.skillnet.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.AclSid;
import com.ixonos.skillnet.logic.dao.AclSidDAO;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AclSidService;

/**
 *
 * @author magurja
 */
@Service("aclSidService")
public final class AclSidServiceImpl extends AbstractGenericService<AclSid> implements AclSidService {	
	
	@Resource
	protected AclSidDAO aclSidDAO;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("aclSidDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}
