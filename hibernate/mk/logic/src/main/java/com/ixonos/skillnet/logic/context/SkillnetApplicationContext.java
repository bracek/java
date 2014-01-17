package com.ixonos.skillnet.logic.context;

import org.springframework.context.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 1, 2009
 * Time: 1:02:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class SkillnetApplicationContext {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(final ApplicationContext applicationCtx) {
        applicationContext = applicationCtx;
    }

}
