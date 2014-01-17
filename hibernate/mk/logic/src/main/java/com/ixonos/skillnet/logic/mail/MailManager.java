package com.ixonos.skillnet.logic.mail;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

/**
 * Created by IntelliJ IDEA. User: hustasl Date: Apr 8, 2009 Time: 1:14:31 PM To
 * change this template use File | Settings | File Templates.
 */
public interface MailManager {

	/**
	 * Send mail.
	 * 
	 * @param recipients
	 *            the recipients
	 * @param template
	 *            the template
	 * @param textParameters
	 *            the text parameters
	 * @param attachments
	 *            the attachments
	 * @param ccRecipients
	 *            the cc recipients
	 * @param bccRecipients
	 *            the bcc recipients
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final List<String> recipients,
			final List<String> ccRecipients, final List<String> bccRecipients,
			final MailTemplates.Template template,
			final List<String> textParameters,
			final Map<String, ClassPathResource> attachments) throws Exception;

	/**
	 * Send mail.
	 * 
	 * @param recipients
	 *            the recipients
	 * @param template
	 *            the template
	 * @param textParameters
	 *            the text parameters
	 * @param attachments
	 *            the attachments
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final List<String> recipients,
			MailTemplates.Template template, final List<String> textParameters,
			final Map<String, ClassPathResource> attachments) throws Exception;

	/**
	 * Send mail.
	 * 
	 * @param recipients
	 *            the recipients
	 * @param template
	 *            the template
	 * @param textParameters
	 *            the text parameters
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final List<String> recipients,
			final MailTemplates.Template template,
			final List<String> textParameters) throws Exception;

	/**
	 * Send mail.
	 * 
	 * @param recipients
	 *            the recipients
	 * @param template
	 *            the template
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final List<String> recipients,
			final MailTemplates.Template template) throws Exception;

	/**
	 * Send mail.
	 * 
	 * @param recipient
	 *            the recipient
	 * @param template
	 *            the template
	 * @param textParameters
	 *            the text parameters
	 * @param attachments
	 *            the attachments
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final String recipient,
			final MailTemplates.Template template,
			final List<String> textParameters,
			final Map<String, ClassPathResource> attachments) throws Exception;

	/**
	 * Send mail.
	 * 
	 * @param recipient
	 *            the recipient
	 * @param template
	 *            the template
	 * @param textParameters
	 *            the text parameters
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final String recipient,
			final MailTemplates.Template template,
			final List<String> textParameters) throws Exception;

	/**
	 * Send mail.
	 * 
	 * @param recipient
	 *            the recipient
	 * @param template
	 *            the template
	 * 
	 * @throws Exception
	 *             the exception
	 */
	 void sendMail(final String recipient,
			final MailTemplates.Template template) throws Exception;
}
