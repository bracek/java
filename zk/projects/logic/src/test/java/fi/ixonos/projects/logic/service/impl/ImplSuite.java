package fi.ixonos.projects.logic.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author katrami
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    fi.ixonos.projects.logic.service.impl.UsersDetailServiceImplTest.class,
    fi.ixonos.projects.logic.service.impl.ProjectsServiceImplTest.class,
    fi.ixonos.projects.logic.service.impl.UsersServiceImplTest.class})
public class ImplSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
