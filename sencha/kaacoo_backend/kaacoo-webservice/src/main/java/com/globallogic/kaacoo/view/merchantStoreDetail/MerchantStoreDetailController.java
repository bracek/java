package com.globallogic.kaacoo.view.merchantStoreDetail;

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

@Path("view/merchantStoreDetail")
@Component
@Scope("singleton")
@Transactional
public class MerchantStoreDetailController {

  private static final Logger log = LoggerFactory.getLogger(MerchantStoreDetailController.class);
  
  @Autowired
  private MerchantStoreDetailJpaDao merchantStoreDetailJpaDao;
  
  public MerchantStoreDetailController() {
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
  public MerchantStoreDetailModelResponse getMerchantStoreDetailModelResponse(
      @BeanParam MerchantStoreDetailModelRequest request) {
    
    Integer merchantId = request.getMerchantId();
    Integer storeId = request.getStoreId();
    if (merchantId == null || storeId == null) {
      throw new WebApplicationException("Missing merchantId or storeId.", Status.BAD_REQUEST);
    }
    
    return MerchantStoreDetailModelResponse.newBuilder()
        .merchant(merchantStoreDetailJpaDao.findMerchantById(merchantId))
        .store(merchantStoreDetailJpaDao.findMerchantStoreById(storeId))
        .response;
  }
}
