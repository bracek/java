package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.security.providers.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.Authority;
import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.SkillDAO;
import com.ixonos.skillnet.logic.dao.UsersDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AuthorityService;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.service.UsersService;

/**
 * The Class UsersServiceImpl.
 * 
 * @author magurja
 */
@Service("usersService")
public final class UsersServiceImpl extends AbstractGenericService<Users> implements UsersService {

    /** The users dao. */
    @Resource
    protected UsersDAO usersDAO;
    
    /** The skills dao. */
    @Resource
    protected SkillDAO skillsDAO;

    /** The code table service. */
    @Resource
    protected CodeTableService codeTableService;

    /** The authority service. */
    @Resource
    protected AuthorityService authorityService;
    
    /** The skill service. */
    @Resource
    protected SkillService skillService;

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.AbstractGenericService#setServiceDAO(com.ixonos.skillnet.logic.dao.GenericDAO)
     */
	@SuppressWarnings("unchecked")
	@Autowired
    @Override
    public void setServiceDAO(@Qualifier("usersDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#getAllUsers()
     */
    @Secured({ROLE_ADMIN, ROLE_GM})
    @Transactional(readOnly = true)
    @Override
    public List<Users> getAllUsers() {
        List<Users> users = readAll();
        for (Users user : users) {
            user.getAuthorityCollection().size();
        }
        return users;
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#getAllAdministrators()
     */
    @Secured({ROLE_ADMIN, ROLE_GM})
    @Transactional(readOnly = true)
    @Override
    public List<Users> getAllAdministrators() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
       
        DetachedCriteria subquery1 = DetachedCriteria.forClass(Users.class);
        subquery1.setProjection(Projections.property("username"));
        subquery1.createAlias("authorityCollection", "a");
        subquery1.createAlias("a.authority", "c");
        subquery1.add(Restrictions.eq("c.code", ROLE_ADMIN));
        subquery1.add(Restrictions.eq("c.groupCode", "AUTHORITIES"));
        
        DetachedCriteria subquery2 = DetachedCriteria.forClass(Users.class);
        subquery2.setProjection(Projections.property("username"));
        subquery2.createAlias("groupMemberCollection", "gm");
        subquery2.createAlias("gm.group", "g");
        subquery2.createAlias("g.groupAuthorityCollection", "ga");
        subquery2.createAlias("ga.authority", "c");
        subquery2.add(Restrictions.eq("c.code", ROLE_ADMIN));
        subquery2.add(Restrictions.eq("c.groupCode", "AUTHORITIES"));
        
        Junction disjunction = Restrictions.disjunction();
        disjunction.add(Subqueries.propertyIn("username", subquery1));
        disjunction.add(Subqueries.propertyIn("username", subquery2));
        criteria.add(disjunction);
                      
        List<Users> users = readByCriteria(criteria);        
        return users;
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#getAllGroupManagers()
     */
    @Secured({ROLE_ADMIN, ROLE_GM})
    @Transactional(readOnly = true)
    @Override
    public List<Users> getAllGroupManagers() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);

        DetachedCriteria subquery1 = DetachedCriteria.forClass(Users.class);
        subquery1.setProjection(Projections.property("username"));
        subquery1.createAlias("authorityCollection", "a");
        subquery1.createAlias("a.authority", "c");
        subquery1.add(Restrictions.eq("c.code", ROLE_GM));
        subquery1.add(Restrictions.eq("c.groupCode", "AUTHORITIES"));

        DetachedCriteria subquery2 = DetachedCriteria.forClass(Users.class);
        subquery2.setProjection(Projections.property("username"));
        subquery2.createAlias("groupMemberCollection", "gm");
        subquery2.createAlias("gm.group", "g");
        subquery2.createAlias("g.groupAuthorityCollection", "ga");
        subquery2.createAlias("ga.authority", "c");
        subquery2.add(Restrictions.eq("c.code", ROLE_GM));
        subquery2.add(Restrictions.eq("c.groupCode", "AUTHORITIES"));

        Junction disjunction = Restrictions.disjunction();
        disjunction.add(Subqueries.propertyIn("username", subquery1));
        disjunction.add(Subqueries.propertyIn("username", subquery2));
        criteria.add(disjunction);

        List<Users> users = readByCriteria(criteria);
        return users;
    }



    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#removeUser(java.lang.String)
     */
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean removeUser(String userName) throws Exception {
        Users userForDelete = getUser(userName);
        // it is not allowed to delete manager if one or more users are still assigned to him
        List<Users> users = userForDelete.getUserCollection();
        if (users.size() > 0) {
            throw new Exception("userEdit.error.cantDelete");
        }
        // set created_by reference to null for skills created by this user
        List<Skill> createdSkills = userForDelete.getCreatedSkills();
        if (createdSkills.size() > 0) {
            for (Skill skill : createdSkills) {
                skill.setCreatedBy(null);
                skillService.update(skill);
            }
        }
        // set modified_by reference to null for skills created by this user
        List<Skill> modifiedSkills = userForDelete.getModifiedSkills();
        if (modifiedSkills.size() > 0) {
            for (Skill skill : modifiedSkills) {
                skill.setModifiedBy(null);
                skillService.update(skill);
            }
        }
        delete(userForDelete);
        return true;
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#getUser(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Users getUser(String userName) throws Exception {
    	DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
    	criteria.add(Restrictions.eq("username", userName));
        List<Users> users = readByCriteria(criteria);
        if (users.size() == 0) {
            throw new Exception("userEdit.error.userNotFound");
        } else if (users.size() > 1) {
            throw new Exception("userEdit.error.moreUsers");
        } else {
            return users.get(0);
        }
    }


    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#changePassword(java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public boolean changePassword(String userName, String newPassword) throws Exception {
        Users user = getUser(userName);
        user.setPassword(hashPassword(newPassword, userName));
        update(user);
        return true;
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#addNewUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, com.ixonos.skillnet.logic.bean.Users, java.util.List)
     */
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
	public boolean addNewUser(String login, String password, String name,
			String surname, String email, String phoneNumber, String location,
			String position, Boolean enabled, Users manager,
			List<String> authorities) throws Exception {
    	
        Users user = new Users();
        user.setUsername(login);
        user.setPassword(hashPassword(password, login));
        user.setEnabled(enabled);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setTelephoneNumber(phoneNumber);
        user.setLocation(location);
        user.setPosition(position);
        
        if (manager != null) {
            String managerName = manager.getUsername();
            Users usersManager = getUser(managerName);
            user.setManager(usersManager);
        }
        if (authorities.size() > 0) {
            user.setAuthorityCollection(new ArrayList<Authority>());
            for (String authorityName : authorities) {
                Authority authority = getAuthority(authorityName);
                authority.setUser(user);
                user.getAuthorityCollection().add(authority);
            }
        }
        create(user);
        return true;
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#isUserAlreadyRegistered(java.lang.String)
     */
    @Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
    @Transactional(readOnly = true)
    @Override
    public boolean isUserAlreadyRegistered(String login) {
        Users checkUser = new Users();
        checkUser.setUsername(login);
        List<Users> users = readByCriteria(checkUser);
        return users.size() > 0;
    }

    /**
     * Gets the authority.
     * 
     * @param authorityName the authority name
     * 
     * @return the authority
     * 
     * @throws Exception the exception
     */
    private Authority getAuthority(String authorityName) throws Exception {
        CodeTable codeTable = new CodeTable();
        codeTable.setGroupCode("AUTHORITIES");
        codeTable.setCode(authorityName);
        List<CodeTable> codeTables = codeTableService.readByCriteria(codeTable);
        if (codeTables.size() == 0) {
            throw new Exception("userEdit.error.authNotFound");
        } else if (codeTables.size() > 1) {
            throw new Exception("userEdit.error.authMore");
        } else {
            Authority authority = new Authority();
            authority.setAuthority(codeTables.get(0));
            return authority;
        }
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#updateUser(java.lang.String, com.ixonos.skillnet.logic.bean.Users, java.lang.Boolean)
     */
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean updateUser(String userName, Users manager, Boolean enabled) throws Exception {
        Users user = getUser(userName);
        // update manager
        if (manager != null) {
            String managerName = manager.getUsername();
            Users usersManager = getUser(managerName);
            user.setManager(usersManager);
        } else {
            user.setManager(null);
        }

        // update enabled
        user.setEnabled(enabled);

        update(user);
        return true;
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#updateUser(java.lang.String, java.util.List)
     */
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean updateUser(String userName, List<String> authorities) throws Exception {
        Users user = getUser(userName);
        // prepared list delete, add authorities
        List<Authority> authorityCollection = user.getAuthorityCollection();
        List<Authority> authoritiesForDelete = new ArrayList<Authority>();
        List<Authority> authoritiesForAdd = new ArrayList<Authority>();
        List<String> actualAuthorities = new ArrayList<String>();
        for (Authority authority : authorityCollection) {
            actualAuthorities.add(authority.getAuthority().getCode());
            if (!authorities.contains(authority.getAuthority().getCode())) {
                authoritiesForDelete.add(authority);
            }
        }
        for (String authority : authorities) {
            if (!actualAuthorities.contains(authority)) {
                authoritiesForAdd.add(getAuthority(authority));
            }
        }
        // delete
        for (Authority authority : authoritiesForDelete) {
            authorityCollection.remove(authority);
            authorityService.delete(authority);
        }
        // insert
        for (Authority authority : authoritiesForAdd) {
            authority.setUser(user);
            authorityCollection.add(authority);
        }
        update(user);
        return true;
    }

    /**
     * Hash password.
     * 
     * @param plainPassword the plain password
     * @param userName the user name
     * 
     * @return the string
     * 
     * @throws Exception the exception
     */
    private String hashPassword(String plainPassword, String userName) throws Exception {
        MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder("SHA-256");
        return mdpe.encodePassword(plainPassword, userName);
    }


    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#isPasswordCorrect(java.lang.String, java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public boolean isPasswordCorrect(String userName, String password) throws Exception {
        Users user = getUser(userName);
        return hashPassword(password, userName).equals(user.getPassword());
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#updateCurriculum(java.lang.String, byte[])
     */
    @Secured(ROLE_USER)
    @Transactional
    @Override
    public void updateCurriculum(String userName, byte[] cvByteArray) throws Exception {
        Users user = getUser(userName);
        user.setCurriculum(cvByteArray);
        user.setIsCurriculumAlreadyFillUp(true);
        update(user);
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.UsersService#updateUsersInfo(java.lang.String, com.ixonos.skillnet.logic.bean.Users)
     */
    @Secured(ROLE_USER)
    @Override
    @Transactional
    public void updateUsersInfo(String username, Users user) throws Exception {

        final Users updateUser = getUser(username);
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setTelephoneNumber(user.getTelephoneNumber());
        updateUser.setPosition(user.getPosition());
        updateUser.setLocation(user.getLocation());
        updateUser.setEmail(user.getEmail());
        update(updateUser);
    }

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.UsersService#getUsersWithFewSkills(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Users> getUsersWithFewSkills(Integer minimalCount) {
		Integer minSkills = minimalCount;
		if (minSkills == null) {
			minSkills = skillService.getMinimalCountOfSkills();
		}
		
        List<Users> result = usersDAO.getUsersWithFewSkills(minSkills);
        
        return result;
	}
}