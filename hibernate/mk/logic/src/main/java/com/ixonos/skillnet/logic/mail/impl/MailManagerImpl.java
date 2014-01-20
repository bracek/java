package com.ixonos.skillnet.logic.mail.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MAIL_ADDRESS;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MAIL_ADDRESS_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MAIL_ADDRESS_PERSONAL;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MAIL_ADDRESS_PERSONAL_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_HOST;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_HOST_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_PORT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_PORT_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_PROTOCOL;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_PROTOCOL_DEFAULT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ixonos.skillnet.logic.mail.MailManager;
import com.ixonos.skillnet.logic.mail.MailTemplates;
import com.ixonos.skillnet.logic.mail.MailTemplates.Template;
import com.ixonos.skillnet.logic.service.ConfigService;

/**
 * Created by IntelliJ IDEA. User: hustasl Date: Apr 8, 2009 Time: 1:15:24 PM To
 * change this template use File | Settings | File Templates.
 */
@Service("mailManager")
public class MailManagerImpl implements MailManager {

	/**
	 * logger
	 */
	private static final Logger logger = Logger
			.getLogger(MailManagerImpl.class);

	/**
	 * configuration service reference - smtp host, port and other application
	 * level setup
	 */
	@Resource
	private ConfigService configService;

	/**
	 * 
	 * @param recipients
	 * @param template
	 * @param textParameters
	 * @param attachments
	 * @throws Exception
	 */
	@Override
	public void sendMail(final List<String> recipients,
			final List<String> ccRecipients,
 final List<String> bccRecipients,
			final MailTemplates.Template template,
			final List<String> textParameters,
			final Map<String,
 ClassPathResource> attachments) throws Exception {
		final JavaMailSenderImpl sender = new JavaMailSenderImpl();

		sender.setHost(configService.getStringProperty(SMTP_HOST,
				SMTP_HOST_DEFAULT));
		sender.setProtocol(configService.getStringProperty(SMTP_PROTOCOL,
				SMTP_PROTOCOL_DEFAULT));
		sender.setPort(configService.getIntProperty(SMTP_PORT,
				SMTP_PORT_DEFAULT));

		final MimeMessage message = sender.createMimeMessage();
		final String mailAddress = configService.getStringProperty(
				MAIL_ADDRESS, MAIL_ADDRESS_DEFAULT);
		final String mailAddressPersonal = configService.getStringProperty(
				MAIL_ADDRESS_PERSONAL, MAIL_ADDRESS_PERSONAL_DEFAULT);
		final InternetAddress internetAddress = new InternetAddress(
				mailAddress, mailAddressPersonal);
		message.setFrom(internetAddress);

		// multipart message
		final MimeMessageHelper helper = new MimeMessageHelper(message, true,
				"UTF-8");

		// add recipients
		helper.setTo(recipients.toArray(new String[recipients.size()]));

		// add copy recipients
		if (ccRecipients != null && ccRecipients.size() > 0) {
			helper.setCc(ccRecipients.toArray(new String[ccRecipients.size()]));
		}

		// add bcc recipients
		if (bccRecipients != null && bccRecipients.size() > 0) {
			helper.setBcc(bccRecipients.toArray(new String[bccRecipients.size()]));
		}

		// read template
		String templateText = MailTemplates.getInstance().getTemplate(template);

		// set subject
		helper.setSubject(MailTemplates.getInstance().getSubjectFromTemplate(
				templateText));

		// append text parameters
		if (textParameters != null) {
			for (int i = 0; i < textParameters.size(); i++) {
				templateText = templateText.replace("$TP" + (i + 1),
						textParameters.get(i));
			}
		}

		// append attachments
		if (attachments != null) {
			int i = 0;
			for (final String attachmentName : attachments.keySet()) {
				helper.addAttachment(attachmentName,
						attachments.get(attachmentName));
				templateText = templateText.replace("$ATT" + (i + 1),
						attachmentName);
				i++;
			}
		}

		// set mail text
		// TODO fix mail in plain text
		helper.setText(templateText.replaceAll("\\<.*?>", ""), templateText);

		// execute send message
		logger.info("Sending message with template '" + template + "' to "
				+ recipients.toString());
		sender.send(message);
	}

	/**
	 * 
	 * @param recipients
	 * @param template
	 * @param textParameters
	 * @throws Exception
	 */
	@Override
	public void sendMail(final List<String> recipients,
			final Template template,
 final List<String> textParameters)
			throws Exception {
		sendMail(recipients, null, null, template, textParameters, null);

	}

	/**
	 * 
	 * @param recipients
	 * @param template
	 * @throws Exception
	 */
	@Override
	public void sendMail(final List<String> recipients,
 final Template template)
			throws Exception {
		sendMail(recipients, null, null, template, null, null);
	}

	/**
	 * 
	 * @param recipient
	 * @param template
	 * @param textParameters
	 * @param attachments
	 * @throws Exception
	 */
	@Override
	public void sendMail(final String recipient,
 final Template template,
			final List<String> textParameters,
			final Map<String,
 ClassPathResource> attachments) throws Exception {
		final List<String> recipients = new ArrayList<String>();
		recipients.add(recipient);
		sendMail(recipients, null, null, template, textParameters, attachments);
	}

	/**
	 * 
	 * @param recipient
	 * @param template
	 * @param textParameters
	 * @throws Exception
	 */
	@Override
	public void sendMail(final String recipient,
 final Template template,
			final List<String> textParameters) throws Exception {
		final List<String> recipients = new ArrayList<String>();
		recipients.add(recipient);
		sendMail(recipients, null, null, template, textParameters, null);
	}

	/**
	 * 
	 * @param recipient
	 * @param template
	 * @throws Exception
	 */
	@Override
	public void sendMail(final String recipient,
 final Template template)
			throws Exception {
		final List<String> recipients = new ArrayList<String>();
		recipients.add(recipient);
		sendMail(recipients, null, null, template, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ixonos.skillnet.logic.mail.MailManager#sendMail(java.util.List,
	 * com.ixonos.skillnet.logic.mail.MailTemplates.Template, java.util.List,
	 * java.util.Map)
	 */
	@Override
	public void sendMail(final List<String> recipients,
			final Template template,
 final List<String> textParameters,
			final Map<String,
 ClassPathResource> attachments) throws Exception {
		sendMail(recipients, null, null, template, textParameters, attachments);
	}

}
