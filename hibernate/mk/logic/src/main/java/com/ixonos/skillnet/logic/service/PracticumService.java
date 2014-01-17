package com.ixonos.skillnet.logic.service;

import java.util.List;

import com.ixonos.skillnet.logic.bean.Practicum;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.bean.Users;

/**
 *
 * @author magurja
 */
public interface PracticumService extends HibernateGenericService<Practicum> {	
	
	List<Practicum> getPracticum(Users userId, Skill skillId);
}
