package com.att.uchannels.service.impl;

import com.att.uchannels.domain.Entity;
import com.att.uchannels.model.dao.EntityDAO;
import com.att.uchannels.model.dao.GenericDAO;
import com.att.uchannels.service.AbstractGenericService;
import com.att.uchannels.service.EntityService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("entityService")
public final class EntityServiceImpl extends AbstractGenericService<Entity> implements EntityService {

    @Resource
    protected EntityDAO entityDAO;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("entityDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    @Override
    public Entity getEntity(String username) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Entity.class);
        criteria.add(Restrictions.eq("attribute", username));
        List<Entity> entities = readByCriteria(criteria);
        if (entities.isEmpty()) {
            throw new Exception("userEdit.error.userNotFound");
        } else if (entities.size() > 1) {
            throw new Exception("userEdit.error.moreUsers");
        } else {
            return entities.get(0);
        }
    }


}
