package fi.ixonos.projects.logic.service.impl;

import fi.ixonos.projects.logic.bean.Authority;
import fi.ixonos.projects.logic.bean.CodeTable;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_USER;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_GM;
import static fi.ixonos.projects.logic.enumeration.ProjectsRole.ROLE_ADMIN;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.GenericDAO;
import fi.ixonos.projects.logic.service.AbstractGenericService;
import fi.ixonos.projects.logic.service.AuthorityService;
import fi.ixonos.projects.logic.service.CodeTableService;
import fi.ixonos.projects.logic.service.UsersService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.security.providers.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author katrami
 * @date Oct 14, 2010
 */
@Service("usersService")
public final class UsersServiceImpl extends AbstractGenericService<Users> implements UsersService {

    /** The authority service. */
    @Resource
    protected AuthorityService authorityService;
    /** The code table service. */
    @Resource
    protected CodeTableService codeTableService;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("usersDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }

    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#getUser(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public Users getUser(final String userName) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
        criteria.add(Restrictions.eq("username", userName));
        List<Users> users = readByCriteria(criteria);
        if (users.isEmpty()) {
            throw new Exception("userEdit.error.userNotFound");
        } else if (users.size() > 1) {
            throw new Exception("userEdit.error.moreUsers");
        } else {
            return users.get(0);
        }
    }

    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#changePassword(java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public boolean changePassword(final String userName,final  String newPassword) throws Exception {
        Users user = getUser(userName);
        user.setPassword(hashPassword(newPassword, userName));
        update(user);
        return true;
    }


    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#isPasswordCorrect(java.lang.String, java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public boolean isPasswordCorrect(final String userName,
final  String password) throws Exception {
        Users user = getUser(userName);
        return hashPassword(password, userName).equals(user.getPassword());
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
    @Override
    public String hashPassword(final String plainPassword,
final  String userName) throws Exception {
        MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder("SHA-256");
        return mdpe.encodePassword(plainPassword, userName);
    }

    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#getAllUsers()
     */
    @Transactional(readOnly = true)
    @Override
    public List<Users> getAllUsers() {
        List<Users> users = readAll();
        for (Users user : users) {
            user.getProjectsCollection().size();
        }
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Users> getSortedUsers(final String string) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
        criteria.addOrder(Order.asc(string));
        return readByCriteria(criteria);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Projects> getProjectsCollectionForUser(final String userName) throws Exception {
        final Users readUser = getUser(userName);

        Collection<Projects> projectsCollection = readUser.getProjectsCollection();
        for (Projects projects : projectsCollection) {
            projects.getUsersCollection().size();
        }

        return projectsCollection;
    }

    @Transactional
    @Override
    public boolean removeUser(final String userName) throws Exception {
        Users userForDelete = getUser(userName);
        // it is not allowed to delete manager if one or more users are still assigned to him
        List<Users> users = userForDelete.getUserCollection();
        if (users.size() > 0) {
            throw new Exception("userEdit.error.cantDelete");
        }

        Collection<Projects> projectsCollection = userForDelete.getProjectsCollection();
        for (Projects projects : projectsCollection) {
            boolean remove = projects.getUsersCollection().remove(userForDelete);
            if (!remove) {
                throw new Exception("unable to delete user" + userForDelete.getUsername() + "in project: " + projects.getName());
            }
        }
        delete(userForDelete);
        return true;
    }

    @Transactional
    @Override
    public void openProject(final String userName,
final  Projects project) throws Exception {
        Users user = getUser(userName);
        user.getProjectsOpenedCollection().add(project);
        createOrUpdate(user);
    }

    @Transactional
    @Override
    public void closeProject(final String userName,
final  Projects project) throws Exception {
        Users user = getUser(userName);
        user.getProjectsOpenedCollection().remove(project);
        createOrUpdate(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Projects> getOpenedProjects(final String userName) throws Exception {
        Users user = getUser(userName);
        Collection<Projects> projectsOpenedCollection = user.getProjectsOpenedCollection();
        for (Projects projects : projectsOpenedCollection) {
            projects.getUsersCollection().size();
        }
        return projectsOpenedCollection;
    }

    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#updateCurriculum(java.lang.String, byte[])
     */
    @Secured(ROLE_USER)
    @Transactional
    @Override
    public void updateCurriculum(final String userName,
final  byte[] cvByteArray) throws Exception {
        Users user = getUser(userName);
        user.setCurriculum(cvByteArray);
        user.setIsCurriculumAlreadyFillUp(true);
        update(user);
    }

    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#updatePhoto(java.lang.String, byte[])
     */
    @Secured(ROLE_USER)
    @Transactional
    @Override
    public void updatePhoto(final String userName,
final  byte[] photoByteArray) throws Exception {
        Users user = getUser(userName);
        user.setPhoto(photoByteArray);
        user.setPhotoUploaded(true);
        update(user);
    }

    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#updateUsersInfo(java.lang.String, fi.ixonos.projects.logic.bean.Users)
     */
    @Secured(ROLE_USER)
    @Override
    @Transactional
    public void updateUsersInfo(final String username,
final  Users user) throws Exception {

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
     * @see fi.ixonos.projects.logic.service.UsersService#updateUser(java.lang.String, java.util.List)
     */
    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean updateUser(final String userName,
final  List<String> authorities) throws Exception {
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
     * Gets the authority.
     *
     * @param authorityName the authority name
     *
     * @return the authority
     *
     * @throws Exception the exception
     */
    private Authority getAuthority(final String authorityName) throws Exception {
        CodeTable codeTable = new CodeTable();
        codeTable.setGroupCode("AUTHORITIES");
        codeTable.setCode(authorityName);
        List<CodeTable> codeTables = codeTableService.readByCriteria(codeTable);
        if (codeTables.isEmpty()) {
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
     * @see fi.ixonos.projects.logic.service.UsersService#getAllGroupManagers()
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
     * @see fi.ixonos.projects.logic.service.UsersService#updateUser(java.lang.String, fi.ixonos.projects.logic.bean.Users, java.lang.Boolean)
     */

    @Secured(ROLE_ADMIN)
    @Transactional
    @Override
    public boolean updateUser(final String userName,
final  Users manager,
final  Boolean enabled) throws Exception {
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
     * @see fi.ixonos.projects.logic.service.UsersService#isUserAlreadyRegistered(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public boolean isUserAlreadyRegistered(final String login) {
        Users checkUser = new Users();
        checkUser.setUsername(login);
        List<Users> users = readByCriteria(checkUser);
        return users.size() > 0;
    }


    /* (non-Javadoc)
     * @see fi.ixonos.projects.logic.service.UsersService#addNewUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, fi.ixonos.projects.logic.bean.Users, java.util.List)
     */
    @Transactional
    @Override
    public boolean addNewUser(final String login,
final  String password,
final  String name,
final             String surname,
final  String email,
final  String phoneNumber,
final  String location,
final             String position,
final  Boolean enabled,
final  Users manager,
final             List<String> authorities) throws Exception {

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

}
