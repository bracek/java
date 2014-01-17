package com.ixonos.skillnet.logic.dao;

import java.util.List;

import com.ixonos.skillnet.logic.bean.Users;

/**
*
* @author magurja
*/
public interface UsersDAO extends GenericDAO<Users> {

	/**
	 * Gets the users with number of skills less than given parameter <code>minimumSkills</code>.
	 * 
	 * @param minimumSkills the minimum skills
	 * 
	 * @return the users with few skills
	 */
	public List<Users> getUsersWithFewSkills(final Integer minimumSkills);
}
