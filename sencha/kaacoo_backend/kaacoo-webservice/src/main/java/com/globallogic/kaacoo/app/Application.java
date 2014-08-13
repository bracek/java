package com.globallogic.kaacoo.app;

import javax.ws.rs.ext.ContextResolver;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globallogic.kaacoo.view.KaacooViewPackageMarkup;

/**
 * @author Jaroslav Sebes
 */

public class Application extends ResourceConfig {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public Application() {
    log.debug("Kaacoo WebService Application initialization.");

    packages(true, KaacooViewPackageMarkup.class.getPackage().getName());
    register(RequestContextFilter.class);
    register(new MoxyJsonFeature());
    register(moxyJsonResolver());
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
