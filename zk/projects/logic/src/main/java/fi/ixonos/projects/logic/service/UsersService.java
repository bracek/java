package fi.ixonos.projects.logic.service;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
public interface UsersService extends HibernateGenericService<Users> {

    public Users getUser(String username) throws Exception;

    public boolean changePassword(String userName, String text) throws Exception;

    public boolean isPasswordCorrect(String userName, String text) throws Exception;

    /**
     * Removes the user.
     *
     * @param userName the user name
     *
     * @return true, if successful
     *
     * @throws Exception the exception
     */
    public boolean removeUser(String userName) throws Exception;

    /***
     *
     * @param userName
     * @return list of projects in which user is working
     * @throws Exception
     */
    public Collection<Projects> getProjectsCollectionForUser(final String userName) throws Exception;

    /***
     *
     * @param userName
     * @return open project for its management
     * @throws Exception
     */
    public void openProject(final String userName, Projects project) throws Exception;

    /***
     *
     * @param userName
     * @return close project after its management
     * @throws Exception
     */
    public void closeProject(final String userName, Projects project) throws Exception;

    /***
     *
     * @param userName
     * @return close project after its management
     * @throws Exception
     */
    public Collection<Projects> getOpenedProjects(final String userName) throws Exception;

    /**
     * Gets the all users.
     *
     * @return the all users
     */
    public List<Users> getAllUsers();

    /**
     * Returns list of users sorted by given column.
     * @param string column name
     * @return users list
     */
    public List<Users> getSortedUsers(String string);

    /**
     * Update curriculum.
     *
     * @param userName the user name
     * @param cvByteArray the cv byte array
     *
     * @throws Exception the exception
     */
    public void updateCurriculum(String userName, byte[] cvByteArray) throws Exception;

    /**
     * Update Photo.
     *
     * @param userName the user name
     * @param photoByteArray the photo byte array
     *
     * @throws Exception the exception
     */
    void updatePhoto(String userName, byte[] photoByteArray) throws Exception;

    /**
     * Update users info.
     *
     * @param username the username
     * @param user the user
     *
     * @throws Exception the exception
     */
    public void updateUsersInfo(String username, Users user) throws Exception;

    /**
     * Update user.
     *
     * @param userName the user name
     * @param authorities the authorities
     *
     * @return true, if successful
     *
     * @throws Exception the exception
     */
    public boolean updateUser(String userName, List<String> authorities) throws Exception;

    /**
     * Gets the all group managers.
     *
     * @return the all group managers
     */
    public List<Users> getAllGroupManagers();

    /**
     * Update user.
     *
     * @param userName the user name
     * @param manager the manager
     * @param enabled the enabled
     *
     * @return true, if successful
     *
     * @throws Exception the exception
     */
    public boolean updateUser(String userName, Users manager, Boolean enabled) throws Exception;


    /**
     * Checks if is user already registered.
     *
     * @param login the login
     *
     * @return true, if is user already registered
     */

    public boolean isUserAlreadyRegistered(String login);

    /**
     * Adds the new user.
     *
     * @param login the login
     * @param password the password
     * @param name the name
     * @param surname the surname
     * @param email the email
     * @param phoneNumber the phone number
     * @param location the location
     * @param position the position
     * @param enabled the enabled
     * @param manager the manager
     * @param authorities the authorities
     *
     * @return true, if successful
     *
     * @throws Exception the exception
     */
    public boolean addNewUser(String login, String password, String name, String surname, String email, String phoneNumber, String location, String position, Boolean enabled, Users manager, List<String> authorities) throws Exception;

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
    String hashPassword(String plainPassword, String userName) throws Exception;

}
