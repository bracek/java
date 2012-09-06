package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.mail.MailManager;
import com.ixonos.skillnet.logic.mail.MailTemplates;
import com.ixonos.skillnet.logic.service.MailService;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.service.UsersService;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: May 22, 2009
 * Time: 9:23:09 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    /** The mail manager. */
    @Resource                        
    private MailManager mailManager;

    /** The users service. */
    @Resource
    private UsersService usersService;
    
    /** The skill service. */
    @Resource
    protected SkillService skillService;

    /** logger for Log4j. */
    private final static Logger logger = Logger.getLogger(MailServiceImpl.class);

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.MailService#sendNewAccountCreatedEmail(javax.mail.internet.InternetAddress, java.util.List)
     */
    @Secured(ROLE_ADMIN)
    @Override
    public boolean sendNewAccountCreatedEmail(InternetAddress newUser, List<String> textParameters) {

        MailTemplates.Template template = MailTemplates.Template.ACCOUNT_CREATED;
        Map<String, ClassPathResource> attachments = new HashMap<String, ClassPathResource>();
        attachments.put("ixonos_logo.jpg", new ClassPathResource("mail_templates/skillnet_logo.jpg"));

        //administrator notification
        List<Users> users = usersService.getAllAdministrators();
        List<String> adminEmails = new ArrayList<String>();
        for (Users user : users) {
                adminEmails.add(user.getEmail());
        }
        
        try {
            logger.info("#sendNewAccountCreatedEmail - Sending mail to " + newUser.getAddress());
            mailManager.sendMail(newUser.getAddress(), template, textParameters, attachments);
            mailManager.sendMail(adminEmails, template, textParameters.subList(0,3), attachments);
        } catch (Exception e) {
            logger.error("#sendNewAccountCreatedEmail - failed ! " + e);
            return false;
        }
        
        return true;
        
    }

    /* (non-Javadoc)
     * @see com.ixonos.skillnet.logic.service.MailService#sendAdminNightlyNotification(java.util.List)
     */
    @Override
    public boolean sendAdminNightlyNotification(List<String> textParameters) {

        MailTemplates.Template template = MailTemplates.Template.ADMIN_NIGHTLY_NOTIFICATION;

        Map<String, ClassPathResource> attachments = new HashMap<String, ClassPathResource>();
        attachments.put("ixonos_logo.jpg", new ClassPathResource("mail_templates/skillnet_logo.jpg"));

        List<Users> users = usersService.getAllAdministrators();
        List<String> adminEmails = new ArrayList<String>();
        for (Users user : users) {
                adminEmails.add(user.getEmail());
        }

        try {
            logger.info("#sendAdminNightlyNotification - Sending mail to " + adminEmails);
            mailManager.sendMail(adminEmails, template, textParameters, attachments);
        } catch (Exception e) {
            logger.error("#sendAdminNightlyNotification - failed ! " + e);
            return false;
        }

        return true;
    }

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.MailService#sendChangedPasswordEmail(javax.mail.internet.InternetAddress, java.util.List)
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
    @Override
	public boolean sendChangedPasswordEmail(InternetAddress user,
			List<String> textParameters) {
		MailTemplates.Template template = MailTemplates.Template.PASSWORD_CHANGED;
        Map<String, ClassPathResource> attachments = new HashMap<String, ClassPathResource>();
        attachments.put("ixonos_logo.jpg", new ClassPathResource("mail_templates/skillnet_logo.jpg"));

        try {
        	logger.info("#sendChangedPasswordEmail - Sending mail to " + user);
        	mailManager.sendMail(user.getAddress(), template, textParameters, attachments);
        } catch (Exception e) {
        	logger.error("#sendChangedPasswordEmail - failed ! " + e);
            return false;
        }
        
        return true;
        
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.MailService#sendUserAccountDeletedEmail(javax.mail.internet.InternetAddress, java.util.List)
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	@Override
	public boolean sendUserAccountDeletedEmail(InternetAddress deletedUser,
			List<String> textParameters) {
		MailTemplates.Template template = MailTemplates.Template.ACCOUNT_DELETED;
        Map<String, ClassPathResource> attachments = new HashMap<String, ClassPathResource>();
        attachments.put("ixonos_logo.jpg", new ClassPathResource("mail_templates/skillnet_logo.jpg"));

        try {
        	logger.info("#sendUserAccountDeletedEmail - Sending mail to " + deletedUser);
        	mailManager.sendMail(deletedUser.getAddress(), template, textParameters, attachments);
        } catch (Exception e) {
        	logger.error("#sendUserAccountDeletedEmail - failed ! " + e);
            return false;
        }
        
        return true;
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.MailService#sendFewSkillsNotification()
	 */
	@Override
	public boolean sendFewSkillsNotification() {
		MailTemplates.Template template = MailTemplates.Template.FEW_SKILL_TEMPLATE;

        Map<String, ClassPathResource> attachments = new HashMap<String, ClassPathResource>();
        attachments.put("ixonos_logo.jpg", new ClassPathResource("mail_templates/skillnet_logo.jpg"));

        Integer minSkills = skillService.getMinimalCountOfSkills();
        List<Users> users = usersService.getUsersWithFewSkills(minSkills);
        
        for (Users user : users) {
        	List<String> fewSkillsEmails = new ArrayList<String>();
        	List<String> fewSkillsCcEmails = new ArrayList<String>();
        	
        	fewSkillsEmails.add(user.getEmail());
        	if (user.getManager() != null) {
        		fewSkillsCcEmails.add(user.getManager().getEmail());
        	}
        	
        	List<String> textParameters = new ArrayList<String>();
            textParameters.add(minSkills.toString());
        	textParameters.add(user.getHumanReadableUsername());
        	
        	try {
                logger.info("#sendFewSkillsNotification - Sending mail to " + fewSkillsEmails);
                mailManager.sendMail(fewSkillsEmails, fewSkillsCcEmails, null, template, textParameters, attachments);
            } catch (Exception e) {
                logger.error("#sendFewSkillsNotification - failed ! " + e);
                //return false;
            }
        }

        return true;
	}
}
