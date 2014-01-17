package com.ixonos.skillnet.logic.mail;

import java.util.HashMap;
import java.util.Map;

public class MailTemplates {

    /**
     * com.ixonos.skillnet.web.servlet.SkillnetInitServlet #initMailTemplates()
     * lowercase comparison against resources directory content
     * "*.html" or "*.htm"
     */
    public enum Template {ACCOUNT_CREATED, ADMIN_NIGHTLY_NOTIFICATION, PASSWORD_CHANGED, ACCOUNT_DELETED, FEW_SKILL_TEMPLATE};
	
	private static MailTemplates mailTemplates;

    private Map<String, String> templatesMap;
	
	private MailTemplates() {
		this.templatesMap = new HashMap<String, String>();
	}
	
	public static MailTemplates getInstance() {
		if (mailTemplates == null) {
			mailTemplates = new MailTemplates();
		}
		return mailTemplates;
	}
	
	public void addTemplate(final String templateName,final  String templateText) {
		templatesMap.put(templateName, templateText);
	}
	
	public String getTemplate(final Template template) throws Exception {
		String templateText = templatesMap.get(template.toString());
		if (templateText == null) {
			throw new Exception("Template " + template.toString() + " not found!");
		} else {
			return templateText;
		}
	}
	
	public String getSubjectFromTemplate(final String templateText) {
		String titleStart = "<title>";
		int startIndex = templateText.indexOf(titleStart);
		int endIndex = templateText.indexOf("</title>");
		if (startIndex == -1 || endIndex == -1) return "";
		return templateText.substring(startIndex + titleStart.length(), endIndex);
	}
}
