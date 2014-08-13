package com.globallogic.kaacoo.view.merchantStoreList;

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
public class MerchantStoreListModelRequest implements Serializable {

  @XmlElement
  @QueryParam("merchantId") 
  private Integer merchantId;

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public static class Builder implements Serializable {

    public final MerchantStoreListModelRequest request;

    private Builder() {
      super();
      request = new MerchantStoreListModelRequest();
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
