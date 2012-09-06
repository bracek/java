package fi.ixonos.projects.logic.service.impl;

import fi.ixonos.projects.logic.AbstractWebTest;
import fi.ixonos.projects.logic.TestInterface;
import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.impl.IdHolder;
import fi.ixonos.projects.logic.service.ProjectsService;
import fi.ixonos.projects.logic.service.UsersService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author katrami
 */
public class ProjectsServiceImplTest extends AbstractWebTest implements TestInterface {

    @Resource
    private ProjectsService projectsService;
    @Resource
    private UsersService usersService;

    public ProjectsServiceImplTest() {
        this.testName = "test_project_android";
        this.testUpdatedTestName = "test_project_updated_android";
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ProjectsServiceImplTest.class);
        return suite;
    }

    @Override
    public void testInsert() {
        final Projects projects = new Projects();
        projects.setName(testName);
        projects.setDateFrom(new Date());
        projects.setDateTo(new Date());
        projectsService.create(projects);

        flush();
        IdHolder.projectId = projects.getProjectsId();
    }

    @Override
    public void testLoad() {
        Projects project = projectsService.read(IdHolder.projectId);
        flush();
        assertNotNull(project);

    }

    @Override
    public void testUpdate() {
        Projects project = projectsService.read(IdHolder.projectId);

        project.setName(testUpdatedTestName);
        projectsService.update(project);
        flush();
        project = projectsService.read(IdHolder.projectId);
        assertEquals(testUpdatedTestName, project.getName());

        //remove project
        project = projectsService.read(IdHolder.projectId);
        boolean isDeleted = projectsService.delete(project);
        flush();
        assertTrue("Project was not successfully deleted", isDeleted);
    }

    public void insertProjectWithUsers() {

        final Projects projects = new Projects();
        projects.setName("1_" + testName);
        projects.setDateFrom(new Date());
        projects.setDateTo(new Date());
        projectsService.create(projects);
        flush();
        IdHolder.projectId = projects.getProjectsId();

        final List projectsCollection = new ArrayList<Projects>();
        projectsCollection.add(projects);


        Users users = new Users();
        users.setEnabled(Boolean.TRUE);
        users.setPassword(testPassword);
        users.setUsername(testUsername);
        users.setName(testUsername);
        users.setSurname(testUsername);
        users.setEmail(testEmail);
//        users.setProjectsCollection(projectsCollection);
        usersService.create(users);
        flush();


        final List usersCollection = new ArrayList<Projects>();
        usersCollection.add(users);

        projects.setUsersCollection(usersCollection);
        projectsService.create(projects);
        flush();

    }

    public void loadUserByProjects() {
        final Projects selectedProjects = projectsService.read(IdHolder.projectId);
        final Collection<Users> users = selectedProjects.getUsersCollection();
        assertEquals(1, users.size());
    }

    @Override
    public void testDelete() {
        insertProjectWithUsers();
        loadUserByProjects();

        Projects project = projectsService.read(IdHolder.projectId);
        flush();
        boolean isDeleted = projectsService.delete(project);
        flush();
        assertTrue("Project was not successfully deleted", isDeleted);

        try {
            Users user = usersService.getUser(testUsername);
            boolean delete = usersService.delete(user);
            flush();
            assertTrue("User was not successfully deleted", delete);
        } catch (Exception ex) {
            Logger.getLogger(ProjectsServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
