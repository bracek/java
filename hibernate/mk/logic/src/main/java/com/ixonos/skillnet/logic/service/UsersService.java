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
    public List<Users> getAllUsers();

    /**
     * Gets the all administrators.
     * 
     * @return the all administrators
     */
    public List<Users> getAllAdministrators();

    /**
     * Gets the all group managers.
     * 
     * @return the all group managers
     */
    public List<Users> getAllGroupManagers();

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
    public boolean changePassword(String userName, String newPassword) throws Exception;

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
     * Update curriculum.
     * 
     * @param userName the user name
     * @param cvByteArray the cv byte array
     * 
     * @throws Exception the exception
     */
    public void updateCurriculum(String userName, byte[] cvByteArray) throws Exception;

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
     * Gets the user.
     * 
     * @param userName the user name
     * 
     * @return the user
     * 
     * @throws Exception the exception
     */
    public Users getUser(String userName) throws Exception;

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
    public boolean isPasswordCorrect(String userName, String password) throws Exception;

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
     * Gets the users with few skills, resp. users with percentage of
     * number of recorded skills (from all valuable skills) less than MINIMUM_SKILLS config parameter.
     * (See also {@link SkillnetConfigParams})
     * 
     * @param minimalCount the minimal count of skills, if is null, the minimal count is calculated 
     * according to Configuration parameter MINIMUM_SKILLS and current count of skills in DB. 
     * 
     * @return the users with few skills
     */
    public List<Users> getUsersWithFewSkills(Integer minimalCount);
}
