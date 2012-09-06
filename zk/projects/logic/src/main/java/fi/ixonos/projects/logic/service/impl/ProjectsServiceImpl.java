package fi.ixonos.projects.logic.service.impl;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.GenericDAO;
import fi.ixonos.projects.logic.dao.ProjectsDAO;
import fi.ixonos.projects.logic.service.AbstractGenericService;
import fi.ixonos.projects.logic.service.ProjectsService;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
@Service("projectsService")
public final class ProjectsServiceImpl extends AbstractGenericService<Projects> implements ProjectsService {

    @Resource
    protected ProjectsDAO projectsDAO;

    @Autowired
    @Override
    public void setServiceDAO(@Qualifier("projectsDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    @Override
    @Transactional(readOnly=true)
    public Projects getProject(Integer projectId) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Projects.class);
        criteria.add(Restrictions.eq("projectsId", projectId));
        criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        List<Projects> projects = readByCriteria(criteria);
        if (projects.isEmpty()) {
            throw new Exception("projects.error.notFound");
        } else if (projects.size() > 1) {
            throw new Exception("projects.error.moreProjects");
        } else {
            return projects.get(0);
        }
    }

    @Override
    @Transactional
    public void addUser(Integer projectId, Users user) throws Exception {
        Projects project = getProject(projectId);
        project.getUsersCollection().add(user);
        update(project);
    }

    @Override
    @Transactional
    public void removeUser(Integer projectId, Users user) throws Exception {
        Projects project = getProject(projectId);
        project.getUsersCollection().remove(user);
        update(project);
    }

}
