package com.globallogic.att.view.merchantStoreDetail;

import java.io.Serializable;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaroslav Sebes
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
public class MerchantStoreDetailModelRequest implements Serializable {

  @XmlElement
  @QueryParam("merchantId") 
  private Integer merchantId;
  
  @XmlElement
  @QueryParam("storeId") 
  private Integer storeId;

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }
  
  public static class Builder implements Serializable {
    
    public final MerchantStoreDetailModelRequest request;
    
    private Builder() {
      super();
      request = new MerchantStoreDetailModelRequest();
    }
    
    public Builder storeId(Integer storeId) {
      request.setStoreId(storeId);
      return this;
    }
    
    public Builder merchantId(Integer merchantId) {
      request.setMerchantId(merchantId);
      return this;
    }
  }
  
  public static Builder newBuilder() {
    return new Builder();
  }
}
