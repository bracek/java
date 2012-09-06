package fi.ixonos.projects.logic.service.impl;

import fi.ixonos.projects.logic.AbstractWebTest;
import fi.ixonos.projects.logic.TestInterface;
import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.impl.IdHolder;
import fi.ixonos.projects.logic.service.ProjectsService;
import fi.ixonos.projects.logic.service.UsersService;
import java.util.ArrayList;
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
public class UsersServiceImplTest extends AbstractWebTest implements TestInterface {

    @Resource
    private UsersService usersService;
    @Resource
    private ProjectsService projectsService;

    public UsersServiceImplTest() {
        this.testName = "test_hustaty";
        this.testUpdatedTestName = "test_updated_hustaty";
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(UsersServiceImplTest.class);
        return suite;
    }

    @Override
    public void testInsert() {

        final Users users = new Users();
        users.setUsername(this.testName);
        users.setName(this.testName);
        users.setSurname(this.testName);
        users.setPassword(testPassword);
        users.setTelephoneNumber(this.testTelephonNumber);
        users.setEmail(testEmail);
        users.setEnabled(true);
        users.setManager(null);
        usersService.create(users);
        flush();
        IdHolder.id = users.getUserId();

    }

    @Override
    public void testLoad() {
        Users users = usersService.read(IdHolder.id);
        assertNotNull(users);
    }

    @Override
    public void testUpdate() {
        Users project = usersService.read(IdHolder.id);
        project.setUsername(testUpdatedTestName);
        usersService.update(project);
        flush();
        project = usersService.read(IdHolder.id);
        assertEquals(testUpdatedTestName, project.getUsername());


        final Users userWithUserDetail = usersService.read(IdHolder.id);
        boolean isDeleted = usersService.delete(userWithUserDetail);
        flush();
        assertTrue("Users was not successfully deleted", isDeleted);

        insertUsersWithProjects();
    }

    //TODO neccessary to save project for user project first, then user
    public void insertUsersWithProjects() {
//        final Users users = new Users();
//        users.setName(this.testName + 1);
//        users.setSurname(this.testName + 1);
//        users.setUsername(this.testName + 1);
//        users.setEmail(emailTest);
//        users.setEnabled(Boolean.TRUE);
//        users.setManager(null);
//        users.setPassword(passwordTest);
//        final UsersDetail usersDetail = new UsersDetail();
//        usersDetail.setName("x1");
//        usersDetail.setSurname("y1");
//        usersDetailService.create(usersDetail);
//        users.setUsersDetail(usersDetail);
//        usersService.create(users);
//        flush();
//
//        IdHolder.userId = users.getUserId();
//        final List usersCollection = new ArrayList<Users>();
//        usersCollection.add(users);
//
//        Projects projects = new Projects();
//        projects.setName("testProject1");
//        projects.setDescription("testProject description");
//        projects.setDateFrom(new Date());
//        projects.setDateTo(new Date());
//        projects.setUsersCollection(usersCollection);
//
//        projectsService.create(projects);
//        flush();
//        IdHolder.projectId = projects.getProjectsId();

        final List usersCollection = new ArrayList<Users>();

        final List projectsCollection = new ArrayList<Projects>();
        Projects projects = new Projects();
        projects.setName("testProject1");
        projects.setDescription("testProject description");
        projects.setDateFrom(new Date());
        projects.setDateTo(new Date());
        projects.setUsersCollection(usersCollection);
        projectsService.create(projects);
        flush();
        projectsCollection.add(projects);
        IdHolder.projectId = projects.getProjectsId();


        final Users users = new Users();
        users.setName(this.testName + 1);
        users.setSurname(this.testName + 1);
        users.setUsername(this.testName + 1);
        users.setEmail(testEmail);
        users.setEnabled(Boolean.TRUE);
        users.setManager(null);
        users.setPassword(testPassword);
        usersCollection.add(users);
        users.setProjectsCollection(projectsCollection);
        usersService.create(users);
        flush();
        IdHolder.userId = users.getUserId();



    }

    @Override
    public void testDelete() {

        //remove project, which has now users now
        Projects project = projectsService.read(IdHolder.projectId);
        boolean isDeleted = projectsService.delete(project);
        flush();
        assertTrue("Projects was not successfully deleted", isDeleted);


        //remove first user, which had assigned project
        final Users users = usersService.read(IdHolder.userId);
        isDeleted = usersService.delete(users);
        flush();
        assertTrue("Users was not successfully deleted", isDeleted);

    }

    public void testHassPassword() {
        try {
            String hashPassword = usersService.hashPassword("oto", "oto");
            assertNotNull(hashPassword);
        } catch (Exception ex) {
            Logger.getLogger(UsersServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
