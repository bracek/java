package org.adit.spring.hibernate.dao;

import java.util.List;

import org.adit.spring.hibernate.entity.User;

public interface UserDao {
	 void saveUser(final User user);

	 List<User> getAllUser(final User user);

	 User selectUserById(final String userId);

	 void deleteUser(final User user);
}
