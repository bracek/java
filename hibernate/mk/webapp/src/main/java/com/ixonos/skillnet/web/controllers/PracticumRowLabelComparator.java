package com.ixonos.skillnet.web.controllers;

import java.util.Comparator;

import com.ixonos.skillnet.logic.bean.Practicum;

@SuppressWarnings("unchecked")
public class PracticumRowLabelComparator implements Comparator {
	private boolean _asc;
	private String _column;
	private boolean _considerNullValues = true;

	public PracticumRowLabelComparator(boolean asc, String column) {
		_asc = asc;
		_column = column;
	}
	
	public PracticumRowLabelComparator(boolean asc, String column, boolean considerNullValues) {
		_asc = asc;
		_column = column;
		_considerNullValues = considerNullValues;
	}

	public int compare(Object o1, Object o2) {
		Practicum practicum1 = (Practicum) o1;
		Practicum practicum2 = (Practicum) o2;
		int v = 0;
		if ("Date From".equals(_column)) {
			if (practicum1.getDateFrom()==null)
				if (!_considerNullValues) 
					return 1;
				else v=1;
			else if (practicum2.getDateFrom()==null)
				if (!_considerNullValues) 
					return -1;
				else v=-1;
			else
				v = practicum1.getDateFrom().compareTo(practicum2.getDateFrom());
		} else if ("Date To".equals(_column)) {
			if (practicum1.getDateTo()==null && practicum2.getDateTo()==null) {
				if (practicum1.getDateFrom()==null)
					return 1;
				else if (practicum2.getDateFrom()==null)
					return -1;
			} else if (practicum1.getDateTo()==null)
				if (!_considerNullValues && practicum1.getDateFrom()==null)
					return 1;
				else v=1;
			else if (practicum2.getDateTo()==null)
				if (!_considerNullValues && practicum2.getDateFrom()==null) 
					return -1;
				else v=-1;
			else
				v = practicum1.getDateTo().compareTo(practicum2.getDateTo());
		} else if ("Level".equals(_column)) {
			if (practicum1.getLevel()==null)
				if (!_considerNullValues)
					return 1;
				else v=1;
			else if (practicum2.getLevel()==null)
				if (!_considerNullValues) 
					return -1;
				else v=-1;
			else
				v = practicum1.getLevel().getIndex().compareTo(practicum2.getLevel().getIndex());
		} else if ("User".equals(_column)) {
			if (practicum1.getUserId()==null)
				v=1;
			else if (practicum2.getUserId()==null)
				v=-1;
			else
				v = practicum1.getUserId().getUsername().compareTo(practicum2.getUserId().getUsername());
		} else if ("Skill".equals(_column)) {
			if (practicum1.getSkillId()==null)
				v=1;
			else if (practicum2.getSkillId()==null)
				v=-1;
			else
				v = practicum1.getSkillId().getName().compareTo(practicum2.getSkillId().getName());
		} else if ("Manager".equals(_column)) {
			if (practicum1.getUserId().getManager()==null)
				v=1;
			else if (practicum2.getUserId().getManager()==null)
				v=-1;
			else
				v = practicum1.getUserId().getManager().getUsername().compareTo(practicum2.getUserId().getManager().getUsername());
		}
		
		return _asc ? -v : v;
	}
}
