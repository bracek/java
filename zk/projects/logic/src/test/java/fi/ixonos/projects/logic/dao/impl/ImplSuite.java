package fi.ixonos.projects.logic.dao.impl;

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
@Suite.SuiteClasses({fi.ixonos.projects.logic.dao.impl.GenericDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.GroupMemberDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.GroupAuthorityDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.GroupsDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.ProjectsDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.UsersDetailImplTest.class,
    fi.ixonos.projects.logic.dao.impl.CodeTableDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.AuthorityDAOImplTest.class,
    fi.ixonos.projects.logic.dao.impl.UsersDAOImplTest.class})
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
