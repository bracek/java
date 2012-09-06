package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.AclObjectIdentity;
import com.ixonos.skillnet.logic.dao.AclObjectIdentityDAO;

/**
*
* @author magurja
*/
@Repository("aclObjectIdentityDAO")
public class AclObjectIdentityDAOImpl extends GenericDAOImpl<AclObjectIdentity> implements AclObjectIdentityDAO {

   @Autowired
   public AclObjectIdentityDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}