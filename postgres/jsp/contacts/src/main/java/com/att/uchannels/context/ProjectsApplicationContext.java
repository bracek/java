package com.att.uchannels.context;

import org.springframework.context.ApplicationContext;

public class ProjectsApplicationContext {

    private static ApplicationContext ctx;

    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * Injected from the class "ApplicationContextProvider" which is automatically
     * loaded during Spring-Initialization.
     */
    public static void setApplicationContext(final ApplicationContext applicationContext) {
        ctx = applicationContext;
    }
}
