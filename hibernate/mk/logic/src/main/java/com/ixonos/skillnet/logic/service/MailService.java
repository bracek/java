package com.ixonos.skillnet.logic.service;

import javax.mail.internet.InternetAddress;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: May 22, 2009
 * Time: 9:11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MailService {

    /**
     * send email to new imported/created user
     * @param newUser
     * @return
     */
    public boolean sendNewAccountCreatedEmail(InternetAddress newUser, List<String> textParameters);
    
    /**
     * send email to deleted user
     * @param deletedUser
     * @return
     */
    public boolean sendUserAccountDeletedEmail(InternetAddress deletedUser, List<String> textParameters);
    
    
    /**
     * send email to user with changed password
     * @param user
     * @return
     */
    public boolean sendChangedPasswordEmail(InternetAddress user, List<String> textParameters);

    /**
     * sends nightly notification
     * @param textParameters
     * @return
     */
    public boolean sendAdminNightlyNotification(List<String> textParameters);
    
    /**
     * Method sends notification to users with few skills recorded in Skillnet.
     * 
     * @return true, if successful
     */
    public boolean sendFewSkillsNotification();

}
