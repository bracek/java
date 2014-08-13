package com.globallogic.kaacoo.view.merchantStoreList;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.globallogic.kaacoo.model.Merchant;
import com.globallogic.kaacoo.model.Store;

/**
 * @author Jaroslav Sebes
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
public class MerchantStoreListModelResponse implements Serializable {

  @XmlElement
  public Merchant merchant;
  
  @XmlElement
  private List<Store> stores;

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public List<Store> getStores() {
    return stores;
  }

  public void setStores(List<Store> stores) {
    this.stores = stores;
  }

  public static class Builder implements Serializable {

    public final MerchantStoreListModelResponse response;

    private Builder() {
      super();
      response = new MerchantStoreListModelResponse();
    }
    
    public Builder merchant(Merchant merchant) {
      response.setMerchant(merchant);
      return this;
    }

    public Builder stores(List<Store> stores) {
      response.setStores(stores);
      return this;
    }
  }

  public static Builder newBuilder() {
    return new Builder();
  }
}
