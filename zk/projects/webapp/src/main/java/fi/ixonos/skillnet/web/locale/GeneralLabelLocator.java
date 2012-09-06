package fi.ixonos.skillnet.web.locale;

import fi.ixonos.projects.web.servlet.ProjectsInitServlet;
import java.net.URL;
import java.util.Locale;

import org.zkoss.util.resource.LabelLocator;

/** 
 * Class which implements a locator for general labels.<br/>
 * It must be registered for Labels with
 * <i>Labels.register(org.zkoss.util.resource.LabelLocator)</i>
 */
public class GeneralLabelLocator implements LabelLocator {

    private static final String MENU_FILE_NAME = "labels";
    private static final String MENU_FILE_SUFFIX = ".properties";

    public GeneralLabelLocator() {
    }

    /* (non-Javadoc)
     * @see org.zkoss.util.resource.LabelLocator#locate(java.util.Locale)
     */
    @Override
    public URL locate(Locale locale) throws Exception {
        if (locale == null) {
            locale = new Locale("en");
        }
        String filename = MENU_FILE_NAME + "_" + locale.getLanguage() + MENU_FILE_SUFFIX;
        String defaultFilename = MENU_FILE_NAME + MENU_FILE_SUFFIX;
        String path = "fi/ixonos/projects/web/bundle/labels/";
        URL url = ProjectsInitServlet.class.getClassLoader().getResource(path + filename);
        if (url == null) {
            url = ProjectsInitServlet.class.getClassLoader().getResource(path + defaultFilename);
        }

        return url;
    }
}
