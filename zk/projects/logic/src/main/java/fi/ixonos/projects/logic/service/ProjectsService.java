package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;


/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
public interface  ProjectsService extends HibernateGenericService<Projects> {

    public Projects getProject(Integer projectId) throws Exception;

    public void addUser(Integer projectId, Users user) throws Exception;

    public void removeUser(Integer projectId, Users user) throws Exception;

}
