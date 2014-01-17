package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.Groups;
import com.ixonos.skillnet.logic.dao.GroupsDAO;

/**
*
* @author magurja
*/
@Repository("groupsDAO")
public class GroupsDAOImpl extends GenericDAOImpl<Groups> implements GroupsDAO {

   @Autowired
   public GroupsDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
