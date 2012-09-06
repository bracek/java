package com.ixonos.skillnet.logic.mail;

import java.util.List;
import org.springframework.core.io.ClassPathResource;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: hustasl Date: Apr 8, 2009 Time: 1:14:31 PM To
 * change this template use File | Settings | File Templates.
 */
public interface MailManager {

    /**
     * Send mail.
     * 
     * @param recipients the recipients
     * @param template the template
     * @param textParameters the text parameters
     * @param attachments the attachments
     * @param ccRecipients the cc recipients
     * @param bccRecipients the bcc recipients
     * 
     * @throws Exception the exception
     */
	public void sendMail(List<String> recipients, List<String> ccRecipients, 
            List<String> bccRecipients,
			MailTemplates.Template template, List<String> textParameters,
			Map<String, ClassPathResource> attachments) throws Exception;
	
	/**
	 * Send mail.
	 * 
	 * @param recipients the recipients
	 * @param template the template
	 * @param textParameters the text parameters
	 * @param attachments the attachments
	 * 
	 * @throws Exception the exception
	 */
	public void sendMail(List<String> recipients,
			MailTemplates.Template template, List<String> textParameters,
			Map<String, ClassPathResource> attachments) throws Exception;

    /**
     * Send mail.
     * 
     * @param recipients the recipients
     * @param template the template
     * @param textParameters the text parameters
     * 
     * @throws Exception the exception
     */
	public void sendMail(List<String> recipients,
			MailTemplates.Template template, List<String> textParameters)
			throws Exception;

    /**
     * Send mail.
     * 
     * @param recipients the recipients
     * @param template the template
     * 
     * @throws Exception the exception
     */
	public void sendMail(List<String> recipients,
			MailTemplates.Template template) throws Exception;

    /**
     * Send mail.
     * 
     * @param recipient the recipient
     * @param template the template
     * @param textParameters the text parameters
     * @param attachments the attachments
     * 
     * @throws Exception the exception
     */
    public void sendMail(String recipient, MailTemplates.Template template,
			List<String> textParameters,
			Map<String, ClassPathResource> attachments) throws Exception;

    /**
     * Send mail.
     * 
     * @param recipient the recipient
     * @param template the template
     * @param textParameters the text parameters
     * 
     * @throws Exception the exception
     */
    public void sendMail(String recipient, MailTemplates.Template template,
			List<String> textParameters) throws Exception;

    /**
     * Send mail.
     * 
     * @param recipient the recipient
     * @param template the template
     * 
     * @throws Exception the exception
     */
    public void sendMail(String recipient, MailTemplates.Template template)
			throws Exception;
}
