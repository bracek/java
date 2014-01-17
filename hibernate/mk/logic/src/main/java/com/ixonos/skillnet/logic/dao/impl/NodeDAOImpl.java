package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.dao.NodeDAO;

/**
*
* @author magurja
*/
@Repository("nodeDAO")
public class NodeDAOImpl extends GenericDAOImpl<Node> implements NodeDAO {

   @Autowired
   public NodeDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
