package com.globallogic.att.view.presidentList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Miroslav Katrak
 */

@Path("view/presidentList")
@Component
@Scope("singleton")
@Transactional
public class PresidentListController {

  private static final Logger log = LoggerFactory.getLogger(PresidentListController.class);
  
  @Autowired
  private PresidentListJpaDao presidentListJpaDao;

  public PresidentListController() {
    super();
    log.debug("constructor called");
  }
  
  @PostConstruct
  public void init() {
    log.debug("init called");
  }

  @GET
  @Path("model")
  @Produces(APPLICATION_JSON)
  @Transactional(readOnly = true)
  public PresidentListModelResponse getMerchantListModel() {
    return PresidentListModelResponse.newBuilder()
        .presidents(presidentListJpaDao.findPresidents())
        .response;
  }

}
