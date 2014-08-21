package com.globallogic.att.view.merchantStoreDetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.globallogic.att.model.Merchant;
import com.globallogic.att.model.Store;

/**
 * @author Jaroslav Sebes
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
public class MerchantStoreDetailModelResponse implements Serializable {

  @XmlElement
  public Merchant merchant;
  
  @XmlElement
  public Store store;
  
  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }
  
  public static class Builder implements Serializable {
    
    public final MerchantStoreDetailModelResponse response;
    
    private Builder() {
      super();
      response = new MerchantStoreDetailModelResponse();
    }
    
    public Builder merchant(Merchant merchant) {
      response.setMerchant(merchant);
      return this;
    }
    
    public Builder store(Store store) {
      response.setStore(store);
      return this;
    }
  }
  
  public static Builder newBuilder() {
    return new Builder();
  }
}
