package fi.ixonos.projects.logic.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katrami
 */
public class GenericDAOImplTest {

    public GenericDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class GenericDAOImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Object t = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        boolean expResult = false;
        boolean result = instance.create(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOrUpdate method, of class GenericDAOImpl.
     */
    @Test
    public void testCreateOrUpdate() {
        System.out.println("createOrUpdate");
        Object t = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        boolean expResult = false;
        boolean result = instance.createOrUpdate(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class GenericDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Object t = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        boolean expResult = false;
        boolean result = instance.update(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class GenericDAOImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Object t = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        boolean expResult = false;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class GenericDAOImpl.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Serializable id = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        Object expResult = null;
        Object result = instance.read(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class GenericDAOImpl.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Serializable id = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        Object expResult = null;
        Object result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAll method, of class GenericDAOImpl.
     */
    @Test
    public void testReadAll() {
        System.out.println("readAll");
        GenericDAOImpl instance = new GenericDAOImplImpl();
        List expResult = null;
        List result = instance.readAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readByCriteria method, of class GenericDAOImpl.
     */
    @Test
    public void testReadByCriteria_GenericType() {
        System.out.println("readByCriteria");
        Object t = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        List expResult = null;
        List result = instance.readByCriteria(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readByCriteria method, of class GenericDAOImpl.
     */
    @Test
    public void testReadByCriteria_DetachedCriteria() {
        System.out.println("readByCriteria");
        DetachedCriteria criteria = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        List expResult = null;
        List result = instance.readByCriteria(criteria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readUniqueByCriteria method, of class GenericDAOImpl.
     */
    @Test
    public void testReadUniqueByCriteria() {
        System.out.println("readUniqueByCriteria");
        DetachedCriteria criteria = null;
        GenericDAOImpl instance = new GenericDAOImplImpl();
        Object expResult = null;
        Object result = instance.readUniqueByCriteria(criteria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class GenericDAOImplImpl extends GenericDAOImpl {
    }
}
