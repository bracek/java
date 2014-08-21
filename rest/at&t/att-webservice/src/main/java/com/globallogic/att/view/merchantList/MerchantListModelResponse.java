package com.globallogic.att.view.merchantList;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.globallogic.att.model.Merchant;

/**
 * @author Jaroslav Sebes
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
public class MerchantListModelResponse implements Serializable {

  @XmlElement
  private List<Merchant> merchants;

  public List<Merchant> getMerchants() {
    return merchants;
  }

  public void setMerchants(List<Merchant> merchants) {
    this.merchants = merchants;
  }

  public static class Builder implements Serializable {

    public final MerchantListModelResponse response;

    private Builder() {
      super();
      response = new MerchantListModelResponse();
    }

    public Builder merchants(List<Merchant> merchants) {
      response.setMerchants(merchants);
      return this;
    }
  }

  public static Builder newBuilder() {
    return new Builder();
  }
}
