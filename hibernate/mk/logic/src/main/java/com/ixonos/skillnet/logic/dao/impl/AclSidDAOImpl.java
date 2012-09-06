package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.AclSid;
import com.ixonos.skillnet.logic.dao.AclSidDAO;

/**
*
* @author magurja
*/
@Repository("aclSidDAO")
public class AclSidDAOImpl extends GenericDAOImpl<AclSid> implements AclSidDAO {

   @Autowired
   public AclSidDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
