package org.adit.spring.hibernate.dao;

import java.util.List;

import org.adit.spring.hibernate.entity.User;

public interface UserDao {
	public void saveUser(final User user);

	public List<User> getAllUser(final User user);

	public User selectUserById(final String userId);

	public void deleteUser(final User user);
}
