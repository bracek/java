package com.ixonos.skillnet.logic.service;

import java.util.List;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams;

/**
 * The Interface UsersService.
 * 
 * @author magurja
 */
public interface UsersService extends HibernateGenericService<Users> {

    /**
     * Gets the all users.
     * 
     * @return the all users
     */
     List<Users> getAllUsers();

    /**
     * Gets the all administrators.
     * 
     * @return the all administrators
     */
     List<Users> getAllAdministrators();

    /**
     * Gets the all group managers.
     * 
     * @return the all group managers
     */
     List<Users> getAllGroupManagers();

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

    /**
     * Change password.
     * 
     * @param userName the user name
     * @param newPassword the new password
     * 
     * @return true, if successful
     * 
     * @throws Exception the exception
     */
     boolean changePassword(String userName, String newPassword) throws Exception;

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
     * Update curriculum.
     * 
     * @param userName the user name
     * @param cvByteArray the cv byte array
     * 
     * @throws Exception the exception
     */
     void updateCurriculum(final String userName,final  byte[] cvByteArray) throws Exception;

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
     * Gets the user.
     * 
     * @param userName the user name
     * 
     * @return the user
     * 
     * @throws Exception the exception
     */
     Users getUser(final String userName) throws Exception;

    /**
     * Checks if is password correct.
     * 
     * @param userName the user name
     * @param password the password
     * 
     * @return true, if is password correct
     * 
     * @throws Exception the exception
     */
     boolean isPasswordCorrect(final String userName,final  String password) throws Exception;

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
     * Gets the users with few skills, resp. users with percentage of
     * number of recorded skills (from all valuable skills) less than MINIMUM_SKILLS config parameter.
     * (See also {@link SkillnetConfigParams})
     * 
     * @param minimalCount the minimal count of skills, if is null, the minimal count is calculated 
     * according to Configuration parameter MINIMUM_SKILLS and current count of skills in DB. 
     * 
     * @return the users with few skills
     */
     List<Users> getUsersWithFewSkills(final Integer minimalCount);
}
