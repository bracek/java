package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.AclClass;
import com.ixonos.skillnet.logic.dao.AclClassDAO;

/**
*
* @author magurja
*/
@Repository("aclClassDAO")
public class AclClassDAOImpl extends GenericDAOImpl<AclClass> implements AclClassDAO {

   @Autowired
   public AclClassDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}