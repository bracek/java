package fi.ixonos.projects.logic.dao.impl;

import fi.ixonos.projects.logic.AbstractWebTest;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.UsersDAO;
import javax.annotation.Resource;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author katrami
 */
public class UsersDAOImplTest extends AbstractWebTest implements TestInterface {

    @Resource
    private UsersDAO usersDAO;

    public static Test suite() {
        TestSuite suite = new TestSuite(UsersDAOImplTest.class);
        return suite;
    }

    @Override
    public void testInsert() {
        Users user = new Users();
        user.setName(testName);
        user.setSurname(testSurname);
        user.setUsername(testUsername);
        user.setPassword(testPassword);
        user.setEmail(testEmail);
        user.setEnabled(Boolean.TRUE);
        usersDAO.create(user);

        flush();
        IdHolder.userId = user.getUserId();
    }

    @Override
    public void testLoad() {
        Users user = usersDAO.read(IdHolder.userId);
        assertEquals(testName, user.getName());
        assertEquals(testSurname, user.getSurname());
        assertEquals(testUsername, user.getUsername());
    }

    @Override
    public void testUpdate() {
        Users user = usersDAO.read(IdHolder.userId);
        user.setUsername(testUpdatedTestName);
        usersDAO.update(user);
        flush();

        user = usersDAO.read(IdHolder.userId);
        assertEquals(testUpdatedTestName, user.getUsername());
    }

    @Override
    public void testDelete() {
        Users user = usersDAO.read(IdHolder.userId);
        boolean isDeleted = usersDAO.delete(user);
        flush();
        assertTrue("User was not successfully deleted", isDeleted);
    }
}
