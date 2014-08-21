package com.globallogic.att.view.merchantList;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jaroslav Sebes
 */

@Path("view/merchantList")
@Component
@Scope("singleton")
@Transactional
public class MerchantListController {

  private static final Logger log = LoggerFactory.getLogger(MerchantListController.class);
  
  @Autowired
  private MerchantListJpaDao merchantListJpaDao;

  public MerchantListController() {
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
  public MerchantListModelResponse getMerchantListModel() {
    return MerchantListModelResponse.newBuilder()
        .merchants(merchantListJpaDao.findMerchants())
        .response;
  }

}
