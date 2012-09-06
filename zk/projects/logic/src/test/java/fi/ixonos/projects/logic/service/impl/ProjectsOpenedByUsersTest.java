package fi.ixonos.projects.logic.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestSuite;
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
import javax.annotation.Resource;
import static org.junit.Assert.*;

/**
 *
 * @author katrami
 */
public class ProjectsOpenedByUsersTest extends AbstractWebTest implements TestInterface {

    @Resource
    private ProjectsService projectsService;
    @Resource
    private UsersService usersService;
    private String testDescription;

    public ProjectsOpenedByUsersTest() {
        this.testName = "test_android";
        this.testUpdatedTestName = "test_updated_android";
        this.testDescription = "test_project_desciption";
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ProjectsOpenedByUsersTest.class);
        return suite;
    }

    @Override
    public void testInsert() {
        final Projects projects = new Projects();
        projects.setName(testName);
        projects.setDateFrom(new Date());
        projects.setDateTo(new Date());
        projects.setDescription(this.testDescription);
        projectsService.create(projects);
        flush();
        IdHolder.projectId = projects.getProjectsId();

        Projects project = projectsService.read(IdHolder.projectId);
        boolean isDeleted = projectsService.delete(project);
        flush();
        assertTrue("Project was not successfully deleted", isDeleted);
    }

    public void testInsertProjectsWithUsers() {

        final Projects projects = new Projects();
        projects.setName(testName);
        projects.setDateFrom(new Date());
        projects.setDateTo(new Date());
        projectsService.create(projects);
        flush();

        final List projectsCollection = new ArrayList<Projects>();
        projectsCollection.add(projects);

        final List usersCollection = new ArrayList<Projects>();

        Users users = new Users();
        users.setEnabled(Boolean.TRUE);
        users.setUsername(testUsername);
        users.setName(testUsername);
        users.setSurname(testUsername);
        users.setEmail(testEmail);
        users.setPassword("password");
        users.setProjectsOpenedCollection(projectsCollection);
        usersService.create(users);
        usersCollection.add(users);

        projects.setUsersOpenedCollection(usersCollection);
        projectsService.update(projects);
        flush();
        IdHolder.projectId = projects.getProjectsId();
    }

    public void testLoadUserByProjects() {
        final Projects selectedProjects = projectsService.read(IdHolder.projectId);
        final Collection<Users> users = selectedProjects.getUsersOpenedCollection();
        assertEquals(1, users.size());
    }

    @Override
    public void testLoad() {
        Projects project = projectsService.read(IdHolder.projectId);
        assertEquals(testName, project.getName());
    }

    @Override
    public void testUpdate() {
        Projects project = projectsService.read(IdHolder.projectId);
        project.setName(testUpdatedTestName);
        projectsService.update(project);
        project = projectsService.read(IdHolder.projectId);
        assertEquals(testUpdatedTestName, project.getName());
    }

    public void testRemoveUserFromOpenedUserCollection() {
        final Projects project = projectsService.read(IdHolder.projectId);
        List<Users> usersOpenedByProject = (List<Users>) project.getUsersOpenedCollection();
        Users user = usersOpenedByProject.get(0);
        project.getUsersOpenedCollection().remove(user);
        projectsService.update(project);
        assertEquals(0, project.getUsersOpenedCollection().size());
    }

    @Override
    public void testDelete() {

        try {
            Users user = usersService.getUser(testUsername);
            boolean delete = usersService.delete(user);
            flush();
            assertTrue("User was not successfully deleted", delete);
        } catch (Exception ex) {
            Logger.getLogger(ProjectsServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        final Projects project = projectsService.read(IdHolder.projectId);
        boolean isDeleted = projectsService.delete(project);
        flush();
        assertTrue("Project was not successfully deleted", isDeleted);
    }
}
