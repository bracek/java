package fi.ixonos.projects.web.servlet;

import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.skillnet.web.locale.GeneralLabelLocator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.zkoss.util.resource.Labels;

public class ProjectsInitServlet extends HttpServlet {

    public static final String CONFIG_FILENAME = "config-webapp.properties";
    public static final String PROJECTS_VERSION = "skillnetversion";
    private static Logger logger = Logger.getLogger(ProjectsInitServlet.class);
    public static Properties props;
    private static TrustManager[] _trustManagers;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        logger.info("#init: Method has just been started.");
        super.init(config);
        initProperties(config);
        Labels.register(new GeneralLabelLocator());
        trustIxonosTlsCertificates();
    }

    private void initProperties(final ServletConfig config) {
        InputStream propStream = ProjectsInitServlet.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
        props = new Properties();
        try {
            props.load(propStream);
            String projectsVersion = props.getProperty("projects.version");
            logger.debug("#init: PROJECTS version loaded: '" + projectsVersion + "'");
            //storing version into the application scope
            config.getServletContext().setAttribute(PROJECTS_VERSION, projectsVersion);
        } catch (IOException e) {
            logger.error(e);
            logger.error("#init: Could not load property file " + CONFIG_FILENAME + ".");
        }
    }

    private void trustIxonosTlsCertificates() {
        try {
            TrustManager trustManager = (TrustManager) ProjectsApplicationContext.getApplicationContext().getBean("trustManager");
            SSLContext context;
            if (_trustManagers == null) {
                _trustManagers = new TrustManager[]{trustManager};
            }
            try {
                context = SSLContext.getInstance("SSL");
                context.init(null, _trustManagers, new SecureRandom());
            } catch (GeneralSecurityException gse) {
                throw new IllegalStateException(gse.getMessage());
            }
            SSLContext.setDefault(context);
        } catch(NoSuchBeanDefinitionException e) {
            logger.error(e);
            logger.error("#init: Failed to load custom TrustManager. Fallback to default TrustManager.");
        }
    }

}
