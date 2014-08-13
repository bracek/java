package com.globallogic.kaacoo.view.merchantStoreList;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.annotation.PostConstruct;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jaroslav Sebes
 */

@Path("view/merchantStoreList")
@Component
@Scope("singleton")
@Transactional
public class MerchantStoreListController {

  private static final Logger log = LoggerFactory.getLogger(MerchantStoreListController.class);
  
  @Autowired
  private MerchantStoreListJpaDao merchantStoreListJpaDao;

  public MerchantStoreListController() {
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
  public MerchantStoreListModelResponse getMerchantStoreListModel(
      @BeanParam MerchantStoreListModelRequest request) {
    
    Integer merchantId = request.getMerchantId();
    if (merchantId == null) {
      throw new WebApplicationException("Missing merchantId.", Status.BAD_REQUEST);
    }
    
    return MerchantStoreListModelResponse.newBuilder()
        .merchant(merchantStoreListJpaDao.findMerchantById(merchantId))
        .stores(merchantStoreListJpaDao.findMerchantStoresByMerchantId(merchantId))
        .response;
  }
}
