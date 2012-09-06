package com.ixonos.skillnet.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.zkoss.util.resource.Labels;

import com.ixonos.skillnet.logic.mail.MailTemplates;
import com.ixonos.skillnet.web.locale.GeneralLabelLocator;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 1, 2009
 * Time: 10:54:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillnetInitServlet extends HttpServlet {
    public static final String CONFIG_FILENAME = "config-skillnetweb.properties";
    public static final String SKILLNET_VERSION = "skillnetversion";
    private static final String MAIL_TEMPLATES_DIRECTORY = "mail_templates";
    
    private static Logger logger = Logger.getLogger(SkillnetInitServlet.class);
    
    public static Properties props;    
    
    @Override
    public void init(ServletConfig config) throws ServletException {
		logger.info("#init: Method has just been started.");
		super.init(config);
		initProperties(config);
		initMailTemplates();
        Labels.register(new GeneralLabelLocator());		
    }
    
    private void initProperties(ServletConfig config) {
    	InputStream propStream = SkillnetInitServlet.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
		props = new Properties();
		try {
			props.load(propStream);
			String skillnetVersion = props.getProperty("skillnet.version");
			logger.debug("#init: SKILLNET version loaded: '" + skillnetVersion + "'");
			//storing version into the application scope
			config.getServletContext().setAttribute(SKILLNET_VERSION, skillnetVersion);			
        } catch (IOException e) {
			logger.error("#init: Could not load property file " + CONFIG_FILENAME + ".");			
		}
    }
    
    private void initMailTemplates() {    			
		MailTemplates.Template[] templates = MailTemplates.Template.values();
		for (MailTemplates.Template template : templates) {
			ClassPathResource classPathResource = new ClassPathResource(MAIL_TEMPLATES_DIRECTORY + "/" + template.toString().toLowerCase() + ".html");
			if (!classPathResource.exists()) {
				classPathResource = new ClassPathResource(MAIL_TEMPLATES_DIRECTORY + "/" + template.toString().toLowerCase() + ".htm");
				if (!classPathResource.exists()) {
					logger.error("#init: Template file '" + template.toString().toLowerCase() + ".html' or " + "'" + template.toString().toLowerCase() + ".htm' cannot be found!");
					continue;
				}
			}
			String templateText;
			try {
				templateText = convertStreamToString(classPathResource.getInputStream());
				MailTemplates.getInstance().addTemplate(template.toString(), templateText);
			} catch (IOException e) {
				logger.error("#init: Error during reading template '" + template + "' from input stream!");				
			}					
		}
    }
    
    public String convertStreamToString(InputStream is) {    	        
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb = new StringBuilder();    	
    	String line = null;
    	try {
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    	    }
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			is.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return sb.toString();
    }
}
