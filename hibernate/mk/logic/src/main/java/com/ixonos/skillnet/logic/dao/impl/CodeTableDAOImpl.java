package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.dao.CodeTableDAO;

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

