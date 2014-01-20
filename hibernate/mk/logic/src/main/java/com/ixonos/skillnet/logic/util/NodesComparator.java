package com.ixonos.skillnet.logic.util;

import java.util.Comparator;

import com.ixonos.skillnet.logic.bean.Node;


@SuppressWarnings("unchecked")
public class NodesComparator implements Comparator {
	private boolean _asc;


	public NodesComparator(final boolean asc) {
		_asc = asc;
	}

	public int compare(final Object o1,
final  Object o2) {
		Node node1 = (Node) o1;
		Node node2 = (Node) o2;
		String node1SkillName = node1.getSkill().getName();
		String node2SkillName = node2.getSkill().getName();
		int v = 0;
			if (node1SkillName==null)
				v=-1;
			else if (node2SkillName ==null)
				v=1;
			else
				v = node1SkillName.compareTo(node2SkillName);
		
		return _asc ? v : -v;
	}
}
