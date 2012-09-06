package fi.ixonos.projects.logic.dao.impl;

import fi.ixonos.projects.logic.AbstractWebTest;
import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.dao.ProjectsDAO;
import javax.annotation.Resource;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author katrami
 */
public class ProjectsDAOImplTest extends AbstractWebTest implements TestInterface {
    @Resource
    private ProjectsDAO projectsDAO;

    public static Test suite() {
        TestSuite suite = new TestSuite(ProjectsDAOImplTest.class);
        return suite;
    }

    @Override
    public void testInsert() {
        Projects project = new Projects();
        project.setName(testProjectName);
        projectsDAO.create(project);

        flush();
        IdHolder.projectId = project.getProjectsId();
    }

    @Override
    public void testLoad() {
        Projects project = projectsDAO.read(IdHolder.projectId);
        assertEquals(testProjectName, project.getName());
    }

    @Override
    public void testUpdate() {
        Projects project = projectsDAO.read(IdHolder.projectId);
        final String updatedAndroidName = "Android_updateted";
        project.setName(updatedAndroidName);
        projectsDAO.update(project);
        flush();
        project = projectsDAO.read(IdHolder.projectId);
        assertEquals(updatedAndroidName, project.getName());
    }

    @Override
    public void testDelete() {
        Projects project = projectsDAO.read(IdHolder.projectId);
        boolean isDeleted = projectsDAO.delete(project);
        flush();
        assertTrue("Project was not successfully deleted", isDeleted);
    }
//    public void testInserData() {
//
//        // visual android
//        Projects project = new Projects();
//        project.setId(new Integer(1));
//        project.setName(ANDROID);
//        project.setDateFrom(new Date(2010, 1, 30));
//        project.setDateTo(new Date(2010, 4, 5));
//        projectsDAO.create(project);
//
//        flush();
//        IdHolder.projectId = project.getId();
//
//        // visual studio
//        project = new Projects();
//        project.setId(new Integer(2));
//        project.setDateFrom(new Date(2010, 6, 30));
//        project.setDateTo(new Date(2010, 8, 5));
//        project.setName("Visual Studio");
//        projectsDAO.create(project);
//
//        flush();
//        IdHolder.projectId = project.getId();
//
//        //intel
//        project = new Projects();
//        project.setId(new Integer(3));
//        project.setDateFrom(new Date(2010, 10, 15));
//        project.setDateTo(new Date(2010, 12, 30));
//        project.setName("Intel");
//        projectsDAO.create(project);
//
//        flush();
//        IdHolder.projectId = project.getId();
//    }
}
