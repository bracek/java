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

     Users getUser(final String username) throws Exception;

     boolean changePassword(final String userName,final  String text) throws Exception;

     boolean isPasswordCorrect(final String userName,final  String text) throws Exception;

    /**
     * Removes the user.
     *
     * @param userName the user name
     *
     * @return true, if successful
     *
     * @throws Exception the exception
     */
     boolean removeUser(final String userName) throws Exception;

    /***
     *
     * @param userName
     * @return list of projects in which user is working
     * @throws Exception
     */
     Collection<Projects> getProjectsCollectionForUser(final String userName) throws Exception;

    /***
     *
     * @param userName
     * @return open project for its management
     * @throws Exception
     */
     void openProject(final String userName,final  Projects project) throws Exception;

    /***
     *
     * @param userName
     * @return close project after its management
     * @throws Exception
     */
     void closeProject(final String userName,final  Projects project) throws Exception;

    /***
     *
     * @param userName
     * @return close project after its management
     * @throws Exception
     */
     Collection<Projects> getOpenedProjects(final String userName) throws Exception;

    /**
     * Gets the all users.
     *
     * @return the all users
     */
     List<Users> getAllUsers();

    /**
     * Returns list of users sorted by given column.
     * @param string column name
     * @return users list
     */
     List<Users> getSortedUsers(final String string);

    /**
     * Update curriculum.
     *
     * @param userName the user name
     * @param cvByteArray the cv byte array
     *
     * @throws Exception the exception
     */
     void updateCurriculum(final String userName,final  byte[] cvByteArray) throws Exception;

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
     void updateUsersInfo(final String username,final  Users user) throws Exception;

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
     boolean updateUser(final String userName,final  List<String> authorities) throws Exception;

    /**
     * Gets the all group managers.
     *
     * @return the all group managers
     */
     List<Users> getAllGroupManagers();

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
     boolean updateUser(final String userName,final  Users manager,final  Boolean enabled) throws Exception;


    /**
     * Checks if is user already registered.
     *
     * @param login the login
     *
     * @return true, if is user already registered
     */

     boolean isUserAlreadyRegistered(final String login);

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
     boolean addNewUser(final String login,final  String password,final  String name,final  String surname,final  String email,final  String phoneNumber,final  String location,final  String position,final  Boolean enabled,final  Users manager,final  List<String> authorities) throws Exception;

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
