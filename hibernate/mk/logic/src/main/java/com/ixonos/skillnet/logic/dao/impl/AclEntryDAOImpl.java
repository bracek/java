package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.AclEntry;
import com.ixonos.skillnet.logic.dao.AclEntryDAO;

/**
*
* @author magurja
*/
@Repository("aclEntryDAO")
public class AclEntryDAOImpl extends GenericDAOImpl<AclEntry> implements AclEntryDAO {

   @Autowired
   public AclEntryDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}