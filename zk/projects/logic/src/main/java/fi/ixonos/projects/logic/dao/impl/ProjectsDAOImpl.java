package fi.ixonos.projects.logic.dao.impl;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.dao.ProjectsDAO;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author katrami
 * @date Oct 11, 2010
 */
@Repository("projectsDAO")
public class ProjectsDAOImpl extends GenericDAOImpl<Projects> implements ProjectsDAO {

   @Autowired
   public ProjectsDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
}
