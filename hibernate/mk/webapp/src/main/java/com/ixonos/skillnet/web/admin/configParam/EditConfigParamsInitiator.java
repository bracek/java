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

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;

import com.ixonos.skillnet.logic.bean.Config;
import com.ixonos.skillnet.logic.service.ConfigService;

/**
 * @author stibron
 * 
 */
public class EditConfigParamsInitiator extends AnnotateDataBinderInit {

	private final ConfigService configService = (final ConfigService) SpringUtil
			.getApplicationContext().getBean("configService");

	@Override
	public void doAfterCompose(final Page page,
 final Component[] comps)
			throws Exception {

		final Map<String, Config> configParams = configService.getProperties();
		final EditConfigParamsWindow editConfigParamsWindow = (EditConfigParamsWindow) comps[0];

		// SMTP_HOST
		final Config smtpHostParam = configParams.get(SMTP_HOST);
		if (smtpHostParam != null) {
			editConfigParamsWindow.setSmtpHostParam(smtpHostParam);
			page.setVariable("smtpHostParam", smtpHostParam);
		}

		// SMTP_PROTOCOL
		final Config smtpProtocolParam = configParams.get(SMTP_PROTOCOL);
		if (smtpProtocolParam != null) {
			editConfigParamsWindow.setSmtpHostParam(smtpProtocolParam);
			page.setVariable("smtpProtocolParam", smtpProtocolParam);
		}

		// SMTP_PORT
		final Config smtpPortParam = configParams.get(SMTP_PORT);
		if (smtpPortParam != null) {
			editConfigParamsWindow.setSmtpPortParam(smtpPortParam);
			page.setVariable("smtpPortParam", smtpPortParam);
		}

		// LDAP_HOST
		final Config ldapHostParam = configParams.get(LDAP_HOST);
		if (ldapHostParam != null) {
			editConfigParamsWindow.setLdapHostParam(ldapHostParam);
			page.setVariable("ldapHostParam", ldapHostParam);
		}

		// LDAP_PORT
		final Config ldapPortParam = configParams.get(LDAP_PORT);
		if (ldapPortParam != null) {
			editConfigParamsWindow.setLdapPortParam(ldapPortParam);
			page.setVariable("ldapPortParam", ldapPortParam);
		}

		// LDAP_BASE_DN
		final Config ldapBaseDNParam = configParams.get(LDAP_BASE_DN);
		if (ldapBaseDNParam != null) {
			editConfigParamsWindow.setLdapBaseDNParam(ldapBaseDNParam);
			page.setVariable("ldapBaseDNParam", ldapBaseDNParam);
		}

		// MAIL_ADDRESS
		final Config mailAddressParam = configParams.get(MAIL_ADDRESS);
		if (mailAddressParam != null) {
			editConfigParamsWindow.setMailAddressParam(mailAddressParam);
			page.setVariable("mailAddressParam", mailAddressParam);
		}

		// MAIL_ADDRESS_PERSONAL
		final Config mailAddressPersonalParam = configParams
				.get(MAIL_ADDRESS_PERSONAL);
		if (mailAddressPersonalParam != null) {
			editConfigParamsWindow
					.setMailAddressPersonalParam(mailAddressPersonalParam);
			page.setVariable("mailAddressPersonalParam",
					mailAddressPersonalParam);
		}

		// MINIMUM_SKILLS
		final Config minimumSkillsParam = configParams.get(MINIMUM_SKILLS);
		if (minimumSkillsParam != null) {
			editConfigParamsWindow.setMinimumSkillsParam(minimumSkillsParam);
			page.setVariable("minimumSkillsParam", minimumSkillsParam);
		}

		super.doAfterCompose(page, comps);
	}
}
