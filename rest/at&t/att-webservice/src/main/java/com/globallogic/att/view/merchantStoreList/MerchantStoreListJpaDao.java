package com.globallogic.att.view.merchantStoreList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.globallogic.att.model.Merchant;
import com.globallogic.att.model.Store;

/**
 * @author Jaroslav Sebes
 */

@Component
public class MerchantStoreListJpaDao {

  @PersistenceContext
  private EntityManager em;
  
  public Merchant findMerchantById(Integer id) {
    return em.find(Merchant.class, id);
  }
  
  public List<Store> findMerchantStoresByMerchantId(Integer merchantId) {
    return em.createQuery(
        "select s from Store as s where s.merchant.id = :merchantId order by s.city", Store.class)
        .setParameter("merchantId", merchantId) 
        .getResultList();  
  }
}
