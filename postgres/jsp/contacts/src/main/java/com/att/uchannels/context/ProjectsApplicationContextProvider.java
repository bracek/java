package com.att.uchannels.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ProjectsApplicationContextProvider implements ApplicationContextAware {

    @Override
    public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
        ProjectsApplicationContext.setApplicationContext(ctx);
    }
}
