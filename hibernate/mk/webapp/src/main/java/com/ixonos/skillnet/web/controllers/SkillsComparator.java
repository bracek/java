package com.ixonos.skillnet.web.controllers;

import java.util.Comparator;

import com.ixonos.skillnet.logic.bean.Skill;

@SuppressWarnings("unchecked")
public class SkillsComparator implements Comparator {
	private boolean _asc;
	private String _column;

	public SkillsComparator(boolean asc, String column) {
		_asc = asc;
		_column = column;
	}

	public int compare(Object o1, Object o2) {
		Skill skill1 = (Skill) o1;
		Skill skill2 = (Skill) o2;
		int v = 0;
		if ("Name".equals(_column)) {
			if (skill1.getName()==null)
				v=-1;
			else if (skill2.getName()==null)
				v=1;
			else
				v = skill1.getName().compareToIgnoreCase(skill2.getName());
		} else if ("Description".equals(_column)) {
			if (skill1.getDescription()==null)
				v=-1;
			else if (skill2.getDescription()==null)
				v=1;
			else
				v = skill1.getDescription().compareToIgnoreCase(skill2.getDescription());
		} else if ("Evaluable".equals(_column)) {
			if (skill1.getValuable()==null)
				v=-1;
			else if (skill2.getValuable()==null)
				v=1;
			else
				v = skill1.getValuable().compareTo(skill2.getValuable());
		}
		return _asc ? v : -v;
	}
}
