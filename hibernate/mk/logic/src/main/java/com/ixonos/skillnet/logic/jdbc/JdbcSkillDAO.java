package com.ixonos.skillnet.logic.jdbc;

import java.util.List;

import com.ixonos.skillnet.logic.bean.Skill;

public interface JdbcSkillDAO extends JdbcGenericDAO<Skill> {

	public List<Skill> findAlike(final String name);

}
