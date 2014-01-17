package sk.mka.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.BeansException;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 1, 2009
 * Time: 1:01:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SkillnetApplicationContextProvider implements ApplicationContextAware {

    @Override
    public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
         SkillnetApplicationContext.setApplicationContext(ctx);
     }

}
