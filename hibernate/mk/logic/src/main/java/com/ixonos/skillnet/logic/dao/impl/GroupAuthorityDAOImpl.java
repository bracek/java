package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.GroupAuthority;
import com.ixonos.skillnet.logic.dao.GroupAuthorityDAO;

/**
*
* @author magurja
*/
@Repository("groupAuthorityDAO")
public class GroupAuthorityDAOImpl extends GenericDAOImpl<GroupAuthority> implements GroupAuthorityDAO {

   @Autowired
   public GroupAuthorityDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
