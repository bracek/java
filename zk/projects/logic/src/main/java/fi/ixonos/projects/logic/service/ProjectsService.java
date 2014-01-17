package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;


/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
public interface  ProjectsService extends HibernateGenericService<Projects> {

    public Projects getProject(final Integer projectId) throws Exception;

    public void addUser(final Integer projectId,final  Users user) throws Exception;

    public void removeUser(final Integer projectId,final  Users user) throws Exception;

}
