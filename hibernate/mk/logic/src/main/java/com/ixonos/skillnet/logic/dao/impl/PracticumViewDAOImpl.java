package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.PracticumView;
import com.ixonos.skillnet.logic.dao.PracticumViewDAO;

/**
*
* @author magurja
*/
@Repository("practicumViewDAO")
public class PracticumViewDAOImpl extends GenericDAOImpl<PracticumView> implements PracticumViewDAO {

   @Autowired
   public PracticumViewDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
