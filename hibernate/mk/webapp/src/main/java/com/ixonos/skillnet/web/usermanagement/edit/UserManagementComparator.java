package com.ixonos.skillnet.web.usermanagement.edit;

import java.util.Comparator;

import com.ixonos.skillnet.logic.bean.Users;

public class UserManagementComparator implements Comparator {
	private boolean _asc;
	private String _column;

	public UserManagementComparator(final boolean asc,final  String column) {
		_asc = asc;
		_column = column;
	}

	public int compare(final Object o1,final  Object o2) {
		Users user1 = (Users) o1;
		Users user2 = (Users) o2;
		int v = 0;
		if ("Username".equals(_column)) {
			v = user1.getHumanReadableUsername().compareTo(user2.getHumanReadableUsername());			
		} else if ("Roles".equals(_column)) {
			v = user1.getAuthorities().compareTo(user2.getAuthorities());
		} else if ("Manager".equals(_column)) {
			if (user1.getManager()==null)
				v=-1;
			else if (user2.getManager()==null)
				v=1;
			else
				v = user1.getManager().getHumanReadableUsername().compareTo(user2.getManager().getHumanReadableUsername());						
		} else if ("Enabled".equals(_column)) {
			v = user1.getEnabled().compareTo(user2.getEnabled());
		} 		
		return _asc ? v : -v;
	}
}
