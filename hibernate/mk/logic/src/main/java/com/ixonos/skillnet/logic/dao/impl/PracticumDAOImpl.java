package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.Practicum;
import com.ixonos.skillnet.logic.dao.PracticumDAO;

/**
*
* @author magurja
*/
@Repository("practicumDAO")
public class PracticumDAOImpl extends GenericDAOImpl<Practicum> implements PracticumDAO {

   @Autowired
   public PracticumDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
