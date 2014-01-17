package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.PracticumView;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.PracticumViewDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.PracticumViewService;

/**
 *
 * @author magurja
 */
@Service("practicumViewService")
public final class PracticumViewServiceImpl extends AbstractGenericService<PracticumView> implements PracticumViewService {

    /**
     * DAO for practicum view
     */
    @Resource
	protected PracticumViewDAO practicumViewDAO;

    /**
     *
     * @param genericDAO
     */
    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("practicumViewDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    /**
     *
     * @param skills
     * @param levels
     * @return
     */
    @Secured(ROLE_GM)
    @Override
    @Transactional(readOnly = true)
    public List<PracticumView> getPracticumsWithSkills(final List<String> skills,final  List<Integer> levels) {
		DetachedCriteria practicumCriteria = DetachedCriteria.forClass(PracticumView.class);
		practicumCriteria.add(Subqueries.propertyIn("username", getUsersWithSkillsCriteria(skills, levels)));
		if (skills.size() > 0) practicumCriteria.add(Restrictions.in("skill", skills));
		return readByCriteria(practicumCriteria);
    }

    /**
     *
     * @param skills
     * @param levels
     * @return
     */
    @Secured(ROLE_GM)
    @Override
    @Transactional(readOnly = true)
    public List<String> getUsersWithSkills(final List<String> skills,final  List<Integer> levels) {
		List<?> usersWithSkills = readByCriteria(getUsersWithSkillsCriteria(skills, levels));
		List<String> users = new ArrayList<String>();
		for (int i=0; i<usersWithSkills.size(); i++) {
			String user = usersWithSkills.get(i).toString();
			if (!users.contains(user)) {
				users.add(user);
			}
		}
		return users;
    }

    /**
     *
     * @param skills
     * @param levels
     * @return
     */
    private DetachedCriteria getUsersWithSkillsCriteria(final List<String> skills,final  List<Integer> levels) {		
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticumView.class);
		if (skills.size() == 0) {
			criteria.setProjection(Projections.property("username"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		}
		for(int i=0; i<skills.size(); i++) {
			DetachedCriteria criteriaOut = DetachedCriteria.forClass(PracticumView.class);
			criteriaOut.add(Restrictions.eq("skill", skills.get(i)));
			criteriaOut.add(Restrictions.ge("level_index", levels.get(i)));
			if (i > 0) {
				criteriaOut.add(Subqueries.propertyIn("username", criteria));
			}
			criteriaOut.setProjection(Projections.property("username"));
			criteriaOut.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria = criteriaOut;
		}		
		return criteria;
	}

}
