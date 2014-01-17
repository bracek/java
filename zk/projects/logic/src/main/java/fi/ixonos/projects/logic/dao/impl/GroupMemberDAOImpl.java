package fi.ixonos.projects.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import fi.ixonos.projects.logic.bean.GroupMember;
import fi.ixonos.projects.logic.dao.GroupMemberDAO;

/**
*
* @author magurja
*/
@Repository("groupMemberDAO")
public class GroupMemberDAOImpl extends GenericDAOImpl<GroupMember> implements GroupMemberDAO {

   @Autowired
   public GroupMemberDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
