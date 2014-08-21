package com.globallogic.att.view.merchantList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.globallogic.att.model.Merchant;

/**
 * @author Jaroslav Sebes
 */

@Component
public class MerchantListJpaDao {

  @PersistenceContext
  private EntityManager em;
  
  public List<Merchant> findMerchants() {
    return em.createQuery("select m from Merchant as m order by m.firstName", Merchant.class)
        .getResultList();
  }
}
