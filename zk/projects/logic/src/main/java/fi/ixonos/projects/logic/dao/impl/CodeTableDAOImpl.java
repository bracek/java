package fi.ixonos.projects.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.dao.CodeTableDAO;

/**
*
* @author magurja
*/
@Repository("codeTableDAO")
public class CodeTableDAOImpl extends GenericDAOImpl<CodeTable> implements CodeTableDAO {

   @Autowired
   public CodeTableDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}

