package com.globallogic.att.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @author Jaroslav Sebes
 */

@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(ContextRefreshListener.class);

    @Autowired
    private PocDatabasePopulator pocDatabasePopulator;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Context refreshed");
        pocDatabasePopulator.populateDatabase();
    }
}
