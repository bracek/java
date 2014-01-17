package com.ixonos.skillnet.logic.service.impl;

import com.ixonos.skillnet.logic.bean.Authority;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.Authority;
import com.ixonos.skillnet.logic.dao.AuthorityDAO;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AuthorityService;

/**
 *
 * @author magurja
 */
@Service("authorityService")
public final class AuthorityServiceImpl extends AbstractGenericService<Authority> implements AuthorityService {

    @Resource
    protected AuthorityDAO authorityDAO;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("authorityDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    @Override
    public Authority getAuthority(final String authority) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
