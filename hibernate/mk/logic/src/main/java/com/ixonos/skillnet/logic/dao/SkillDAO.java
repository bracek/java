package com.ixonos.skillnet.logic.dao;

import com.ixonos.skillnet.logic.bean.Skill;

/**
*
* @author magurja
*/
public interface SkillDAO extends GenericDAO<Skill> {

	/**
	 * Gets the valuable skills count.
	 * 
	 * @return the valuable skills count
	 */
	 Integer getValuableSkillsCount();
}
