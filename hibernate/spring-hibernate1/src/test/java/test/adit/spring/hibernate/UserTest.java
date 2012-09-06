package test.adit.spring.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.adit.spring.hibernate.dao.UserDao;
import org.adit.spring.hibernate.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/app-config.xml" })
public class UserTest {
	private UserDao dao;

	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Test
	public void testCreateData() {
		int expectedResult = 1;
		User user = new User();
		user.setAge(23);
		user.setUserName("Adit");
		user.setRegistered(true);
		dao.saveUser(user);
		Assert.assertEquals(expectedResult, dao.getAllUser(new User()).size());
	}

	@Test
	public void testRetrieveData() {
		List<User> userList = dao.getAllUser(new User());
		Assert.assertEquals(1, userList.size());
		User userExpected = userList.get(0);
		User userResult = dao.selectUserById(userExpected.getUserId());
		Assert.assertEquals(userExpected.getUserId(), userResult.getUserId());
	}

	@Test
	public void testUpdateData() {
		List<User> userList = dao.getAllUser(new User());
		Assert.assertEquals(1, userList.size());
		User userExpected = userList.get(0);
		userExpected.setUserName("Singgih");
		dao.saveUser(userExpected);
		User userResult = dao.selectUserById(userExpected.getUserId());
		Assert.assertEquals(userExpected.getUserName(),
				userResult.getUserName());
	}

	@Test
	public void testDeleteData() {
		List<User> userList = dao.getAllUser(new User());
		Assert.assertEquals(1, userList.size());
		User userExpected = userList.get(0);
		dao.deleteUser(userExpected);
		User userResult = dao.selectUserById(userExpected.getUserId());
		Assert.assertEquals(userResult, null);

	}
}
