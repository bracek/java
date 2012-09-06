package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.dao.SkillDAO;

/**
*
* @author magurja
*/
@Repository("skillDAO")
public class SkillDAOImpl extends GenericDAOImpl<Skill> implements SkillDAO {

   @Autowired
   public SkillDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
   
   @Override
   public Integer getValuableSkillsCount() {
	   return (Integer) getSession().createCriteria(Skill.class)
	   .setProjection(Projections.count("skillId"))
	   .add(Restrictions.eq("valuable", Boolean.TRUE))
	   .uniqueResult();
   }
}