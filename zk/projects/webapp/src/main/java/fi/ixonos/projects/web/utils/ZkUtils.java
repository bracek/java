package fi.ixonos.projects.web.utils;

import org.zkoss.zk.ui.Component;



/**
 *
 * @author katrami
 * @date Oct 29, 2010
 */
public class ZkUtils {

    public static Component getParentComponent(final Component component,final  Class clazz) throws Exception {
        int index = 0;
        while ((component != null) && !clazz.isInstance((component = component.getParent()))) {
            index++;
            if (index == 50) {
                throw new Exception("No parent " + clazz + " found!");
            }
        }
        if (component == null) {
            throw new Exception("No parent " + clazz + " found!");
        }
        return component;
    }
}
