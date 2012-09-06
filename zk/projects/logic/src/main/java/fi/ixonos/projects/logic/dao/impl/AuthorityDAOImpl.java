package fi.ixonos.projects.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import fi.ixonos.projects.logic.bean.Authority;
import fi.ixonos.projects.logic.dao.AuthorityDAO;

/**
*
* @author magurja
*/
@Repository("authorityDAO")
public class AuthorityDAOImpl extends GenericDAOImpl<Authority> implements AuthorityDAO {

   @Autowired
   public AuthorityDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}