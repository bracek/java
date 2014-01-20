package com.ixonos.skillnet.web.companytree;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zul.event.TreeDataListener;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;

@Component("companyTreeModel")
public class CompanyTreeModelImpl implements CompanyTreeModel {
	
	@Resource
	UsersService usersService;
		
	private static Logger logger = Logger.getLogger(CompanyTreeModelImpl.class);
	private HashMap<Users, List<Users>> mapUser = new HashMap<Users, List<Users>>();

	@Override
	public void addTreeDataListener(final TreeDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Object getChild(final Object parent,
final  int index) {
		if (((Users)parent).getUserId()==null)
			try {
				return getRootUser(index);
			} catch (Exception e) {
				logger.error(e);
			}
		return getUsersChild((Users)parent, index);
	}

	@Override
	@Transactional
	public int getChildCount(final Object parent) {
		if (((Users)parent).getUserId()==null)
			return getRootUserCount();
		return getUsersChildCount((Users) parent);
	}

	@Override
	public int[] getPath(final Object arg0,
final  Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Object getRoot() {	
		return new Users();
	}

	@Override
	@Transactional
	public boolean isLeaf(final Object user) {
		return getChildCount(user) == 0;
	}

	@Override
	public void removeTreeDataListener(final TreeDataListener arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private Users getRootUser(final int index) throws Exception {
		if (mapUser.containsKey(new Users())) {
			return mapUser.get(new Users()).get(index);
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
		criteria.add(Restrictions.isNull("manager"));
    	List<Users> users = usersService.readByCriteria(criteria);
    	mapUser.put(new Users(), users);
    	if (users.size()==0) {
    		throw new Exception("No user found!");
    	} else {
    		return users.get(index);    		
    	} 
    }    
	
	private int getRootUserCount() {
		if (mapUser.containsKey(new Users())) {
			return mapUser.get(new Users()).size();
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
		criteria.add(Restrictions.isNull("manager"));
    	List<Users> users = usersService.readByCriteria(criteria);
    	mapUser.put(new Users(), users);
    	return users.size();    		
    }
	
	private Users getUsersChild(final Users parent,
final  int index) {
		if (mapUser.containsKey(parent)) {
			return mapUser.get(parent).get(index);
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
		criteria.add(Restrictions.eq("manager.id", parent.getUserId()));
    	List<Users> users = usersService.readByCriteria(criteria);
    	mapUser.put(parent, users);
   		return users.get(index);    		 
    }
	
	private int getUsersChildCount(final Users parent) {
		if (mapUser.containsKey(parent)) {
			return mapUser.get(parent).size();
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
		criteria.add(Restrictions.eq("manager.id", parent.getUserId()));
    	List<Users> users = usersService.readByCriteria(criteria);
    	mapUser.put(parent, users);
   		return users.size();
	}
	
	public void clearMapUser() {
		mapUser.clear();
	}

}
