package com.globallogic.att.view.merchantStoreDetail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.globallogic.att.model.Merchant;
import com.globallogic.att.model.Store;

/**
 * @author Jaroslav Sebes
 */

@Component
public class MerchantStoreDetailJpaDao {

  @PersistenceContext
  private EntityManager em;
  
  public Merchant findMerchantById(Integer id) {
    return em.find(Merchant.class, id);
  }
  
  public Store findMerchantStoreById(Integer id) {
    return em.find(Store.class, id);
  }
}
