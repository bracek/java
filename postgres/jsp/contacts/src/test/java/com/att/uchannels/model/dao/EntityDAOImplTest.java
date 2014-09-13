package com.att.uchannels.model.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.att.uchannels.AbstractWebTest;
import com.att.uchannels.TestInterface;
import com.att.uchannels.domain.Entity;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class EntityDAOImplTest extends AbstractWebTest implements TestInterface {

	@Resource
	private EntityDAO entityDAO;

	@Test
	@Override
	public void testInsert() {
		final Entity obj = new Entity();
		obj.setAttribute(TEST_ATTRIBUTE);
		entityDAO.create(obj);

		flush();
		IdHolder.contactId = obj.getId();
	}

	@Override
	public void testLoad() {
		final Entity ent = entityDAO.read(IdHolder.id);
		assertEquals(TEST_ATTRIBUTE, ent.getAttribute());
	}

	@Override
	public void testUpdate() {
		Entity obj = entityDAO.read(IdHolder.id);
		obj.setAttribute(TEST_ATTRIBUTE_UP);
		entityDAO.update(obj);
		flush();

		obj = entityDAO.read(IdHolder.id);
		assertEquals(TEST_ATTRIBUTE_UP, obj.getAttribute());
	}

	@Override
	public void testDelete() {
		Entity obj = entityDAO.read(IdHolder.id);
		boolean isDeleted = entityDAO.delete(obj);
		flush();

		assertTrue("User was not successfully deleted", isDeleted);
	}
}
