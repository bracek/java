package com.globallogic.kaacoo.app;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jaroslav Sebes
 */

@Provider
public class ResponseFilter implements ContainerResponseFilter {

  private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);

  @Override
  public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
    log.debug("Response Filter called ::: " + req.getUriInfo().getPath());
    res.getHeaders().add("Access-Control-Allow-Origin", "*");
  }
}
