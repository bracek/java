package com.ixonos.skillnet.logic.service;

import java.util.List;

import com.ixonos.skillnet.logic.bean.Skill;

/**
 * 
 * @author magurja
 */
public interface SkillService extends HibernateGenericService<Skill> {

	public List<Skill> findAlike(final String name);

	public List<String> getNodePaths(final int skillId,final  String rootName);
	
	public Integer getMinimalCountOfSkills();

}
