package com.ixonos.skillnet.logic.service;

import java.util.List;
import com.ixonos.skillnet.logic.bean.PracticumView;

/**
 *
 * @author magurja
 */
public interface PracticumViewService extends HibernateGenericService<PracticumView> {	
	 public List<PracticumView> getPracticumsWithSkills(final List<String> skills,final  List<Integer> levels);	   
	 public List<String> getUsersWithSkills(final List<String> skills,final  List<Integer> levels);
}
