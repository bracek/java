package com.globallogic.att.app;

import javax.ws.rs.ext.ContextResolver;

import com.globallogic.att.view.AttViewPackageMarkup;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jaroslav Sebes
 */

public class Application extends ResourceConfig {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public Application() {
        log.debug("Att WebService Application initialization.");

        packages(true, AttViewPackageMarkup.class.getPackage().getName());
        register(RequestContextFilter.class);
        register(new MoxyJsonFeature());
        register(moxyJsonResolver());
        register(new ResponseFilter());
    }

    public static ContextResolver<MoxyJsonConfig> moxyJsonResolver() {
        return new MoxyJsonConfig()
                .setFormattedOutput(true)
                .setIncludeRoot(false)
                .setMarshalEmptyCollections(true)
                .marshallerProperty(MarshallerProperties.JSON_REDUCE_ANY_ARRAYS, false)
                .resolver();
    }
}
