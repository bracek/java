package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.GroupMember;
import com.ixonos.skillnet.logic.dao.GroupMemberDAO;

/**
*
* @author magurja
*/
@Repository("groupMemberDAO")
public class GroupMemberDAOImpl extends GenericDAOImpl<GroupMember> implements GroupMemberDAO {

   @Autowired
   public GroupMemberDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
