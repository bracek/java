package com.ixonos.skillnet.web.admin.configParam;

import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_BASE_DN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_HOST;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.LDAP_PORT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MAIL_ADDRESS;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MAIL_ADDRESS_PERSONAL;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MINIMUM_SKILLS;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_HOST;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_PORT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.SMTP_PROTOCOL;

import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ixonos.skillnet.logic.bean.Config;
import com.ixonos.skillnet.logic.service.ConfigService;
import com.ixonos.skillnet.logic.service.MailService;

/**
 * 
 * @author stibron
 * 
 */
public class EditConfigParamsWindow extends Window {

	/**
	 * Log4j logger for this class
	 */
	private static Logger logger = Logger
			.getLogger(EditConfigParamsWindow.class);

	private static final long serialVersionUID = 1L;

	private final ConfigService configService = (ConfigService) SpringUtil
			.getApplicationContext().getBean("configService");

	private final MailService mailService = (MailService) SpringUtil
			.getApplicationContext().getBean("mailService");

	private Config smtpHost;
	private Config smtpProtocol;
	private Config smtpPort;

	private Config ldapHost;
	private Config ldapPort;
	private Config ldapBaseDN;

	private Config mailAddress;
	private Config mailAddressPersonal;

	private Config minimumSkills;

	public void saveProperties(final Event event) throws Exception {
		try {
			final Textbox textboxSmtpHostValue = (Textbox) this
					.getFellow("smtpHostValue");
			final Textbox textboxSmtpHostDescription = (Textbox) this
					.getFellow("smtpHostDescription");

			final Textbox textboxSmtpProtocolValue = (Textbox) this
					.getFellow("smtpProtocolValue");
			final Textbox textboxSmtpProtocolDescription = (Textbox) this
					.getFellow("smtpProtocolDescription");

			final Textbox textboxSmtpPortValue = (Textbox) this
					.getFellow("smtpPortValue");
			final Textbox textboxSmtpPortDescription = (Textbox) this
					.getFellow("smtpPortDescription");

			final Textbox textboxLdapHostValue = (Textbox) this
					.getFellow("ldapHostValue");
			final Textbox textboxLdapHostDescription = (Textbox) this
					.getFellow("ldapHostDescription");

			final Textbox textboxLdapPortValue = (Textbox) this
					.getFellow("ldapPortValue");
			final Textbox textboxLdapPortDescription = (Textbox) this
					.getFellow("ldapPortDescription");

			final Textbox textboxLdapBaseDNValue = (Textbox) this
					.getFellow("ldapBaseDNValue");
			final Textbox textboxLdapBaseDNDescription = (Textbox) this
					.getFellow("ldapBaseDNDescription");

			final Textbox mailAddressValue = (Textbox) this
					.getFellow("mailAddressValue");
			final Textbox mailAddressDescription = (Textbox) this
					.getFellow("mailAddressDescription");

			final Textbox mailAddressPersonalValue = (Textbox) this
					.getFellow("mailAddressPersonalValue");
			final Textbox mailAddressPersonalDescription = (Textbox) this
					.getFellow("mailAddressPersonalDescription");

			final Textbox minimumSkillsValue = (Textbox) this
					.getFellow("minimumSkillsValue");
			final Textbox minimumSkillsDescription = (Textbox) this
					.getFellow("minimumSkillsDescription");

			configService.saveProperty(SMTP_HOST,
					textboxSmtpHostValue.getValue(),
					textboxSmtpHostDescription.getValue());
			configService.saveProperty(SMTP_PROTOCOL,
					textboxSmtpProtocolValue.getValue(),
					textboxSmtpProtocolDescription.getValue());
			configService.saveProperty(SMTP_PORT,
					textboxSmtpPortValue.getValue(),
					textboxSmtpPortDescription.getValue());
			configService.saveProperty(LDAP_HOST,
					textboxLdapHostValue.getValue(),
					textboxLdapHostDescription.getValue());
			configService.saveProperty(LDAP_PORT,
					textboxLdapPortValue.getValue(),
					textboxLdapPortDescription.getValue());
			configService.saveProperty(LDAP_BASE_DN,
					textboxLdapBaseDNValue.getValue(),
					textboxLdapBaseDNDescription.getValue());
			configService.saveProperty(MAIL_ADDRESS,
					mailAddressValue.getValue(),
					mailAddressDescription.getValue());
			configService.saveProperty(MAIL_ADDRESS_PERSONAL,
					mailAddressPersonalValue.getValue(),
					mailAddressPersonalDescription.getValue());
			configService.saveProperty(MINIMUM_SKILLS,
					minimumSkillsValue.getValue(),
					minimumSkillsDescription.getValue());

			Messagebox.show("Properties were successfully saved",
					"CONGRATULATIONS", Messagebox.OK, Messagebox.INFORMATION);

		} catch (final Exception e) {
			logger.error("#saveProperties: Error on save properties.", e);
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK,
					Messagebox.ERROR);
		}
	}

	public void runFsn(final Event event) throws Exception {
		mailService.sendFewSkillsNotification();
	}

	public Config getSmtpHostParam() {
		return smtpHost;
	}

	public void setSmtpHostParam(final Config smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Config getSmtpProtocolParam() {
		return smtpProtocol;
	}

	public void setSmtpProtocolParam(final Config smtpProtocol) {
		this.smtpProtocol = smtpProtocol;
	}

	public Config getSmtpPortParam() {
		return smtpPort;
	}

	public void setSmtpPortParam(final Config smtpPort) {
		this.smtpPort = smtpPort;
	}

	public Config getLdapPortParam() {
		return ldapPort;
	}

	public void setLdapPortParam(final Config ldapPort) {
		this.ldapPort = ldapPort;
	}

	public Config getLdapBaseDNParam() {
		return ldapBaseDN;
	}

	public void setLdapBaseDNParam(final Config ldapBaseDN) {
		this.ldapBaseDN = ldapBaseDN;
	}

	public Config getLdapHostParam() {
		return ldapHost;
	}

	public void setLdapHostParam(final Config ldapHost) {
		this.ldapHost = ldapHost;
	}

	public void setMailAddressParam(final Config mailAddress) {
		this.mailAddress = mailAddress;
	}

	public Config getMailAddressParam() {
		return mailAddress;
	}

	public void setMailAddressPersonalParam(final Config mailAddressPersonal) {
		this.mailAddressPersonal = mailAddressPersonal;
	}

	public Config getMailAddressPersonalParam() {
		return mailAddressPersonal;
	}

	public void setMinimumSkillsParam(final Config minimumSkills) {
		this.minimumSkills = minimumSkills;
	}

	public Config getMinimumSkillsParam() {
		return minimumSkills;
	}
}
