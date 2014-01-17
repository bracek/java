package org.adit.spring.hibernate.dao;

import java.util.List;

import org.adit.spring.hibernate.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//This will make easier to autowired
@Repository("userDao")
// Default is read only
@Transactional
public class UserDaoImpl implements UserDao {
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional(readOnly = false)
	public void saveUser(final User user) {
		hibernateTemplate.saveOrUpdate(user);

	}

	@Transactional(readOnly = false)
	public void deleteUser(final User user) {
		hibernateTemplate.delete(user);

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser(final User user) {
		return (List<User>) hibernateTemplate.find("from "
				+ User.class.getName());
	}

	public User selectUserById(final String userId) {
		return hibernateTemplate.get(User.class, userId);
	}

}
