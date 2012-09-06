package fi.ixonos.projects.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import fi.ixonos.projects.logic.bean.Authority;
import fi.ixonos.projects.logic.dao.AuthorityDAO;
import fi.ixonos.projects.logic.dao.GenericDAO;
import fi.ixonos.projects.logic.service.AbstractGenericService;
import fi.ixonos.projects.logic.service.AuthorityService;

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
    public void setServiceDAO(@Qualifier("authorityDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

}
