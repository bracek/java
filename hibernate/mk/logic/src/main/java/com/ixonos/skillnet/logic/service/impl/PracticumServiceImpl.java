package com.ixonos.skillnet.logic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ixonos.skillnet.logic.bean.Practicum;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.PracticumDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.PracticumService;

/**
 *
 * @author magurja
 */
@Service("practicumService")
public final class PracticumServiceImpl extends AbstractGenericService<Practicum> implements PracticumService {	
	
	@Resource
	protected PracticumDAO practicumDAO;

    @Autowired
    @Override
    public void setServiceDAO(@Qualifier("practicumDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
    
	public List<Practicum> getPracticum(Users userId, Skill skillId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Practicum.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("skillId", skillId));
		return readByCriteria(criteria);
	}
}