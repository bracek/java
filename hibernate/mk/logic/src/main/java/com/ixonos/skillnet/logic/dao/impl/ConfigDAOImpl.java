package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.Config;
import com.ixonos.skillnet.logic.dao.ConfigDAO;

/**
 * @author stibron
 *
 */
@Repository("configDAO")
public class ConfigDAOImpl extends GenericDAOImpl<Config> implements ConfigDAO {

	@Autowired
	   public ConfigDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
	       setSessionFactory(sessionFactory);
	   }

}
