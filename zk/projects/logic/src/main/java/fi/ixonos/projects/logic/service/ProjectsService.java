package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;


/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
public interface  ProjectsService extends HibernateGenericService<Projects> {

     Projects getProject(final Integer projectId) throws Exception;

     void addUser(final Integer projectId,final  Users user) throws Exception;

     void removeUser(final Integer projectId,final  Users user) throws Exception;

}
