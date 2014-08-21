package com.globallogic.att.model;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaroslav Sebes
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@SuppressWarnings("serial")
public class Store implements Serializable {

  @XmlElement
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE, REFRESH, DETACH })
  @JoinColumn(name = "merchantid", nullable = false)
  private Merchant merchant;

  @XmlElement
  @Column(length = 50, nullable = false)
  private String street;

  @XmlElement
  @Column(length = 50, nullable = false)
  private String city;

  @XmlElement
  @Column(length = 50, nullable = false)
  private String state;

  @XmlElement
  @Column(length = 50, nullable = false)
  private String zip;

  @XmlElement
  @Column(length = 50, nullable = false)
  private String phone;

  @XmlElement
  @Column(length = 250, nullable = false)
  private String hours;

  public Store() {
    this(null);
  }

  public Store(Integer id) {
    super();
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public static class Builder implements Serializable {

    public final Store entity;

    private Builder() {
      super();
      entity = new Store();
    }

    public Builder id(Integer id) {
      entity.setId(id);
      return this;
    }

    public Builder merchant(Merchant merchant) {
      entity.setMerchant(merchant);
      return this;
    }

    public Builder street(String street) {
      entity.setStreet(street);
      return this;
    }

    public Builder city(String city) {
      entity.setCity(city);
      return this;
    }

    public Builder state(String state) {
      entity.setState(state);
      return this;
    }

    public Builder zip(String zip) {
      entity.setZip(zip);
      return this;
    }

    public Builder phone(String phone) {
      entity.setPhone(phone);
      return this;
    }

    public Builder hours(String hours) {
      entity.setHours(hours);
      return this;
    }
  }

  public static Builder newBuilder() {
    return new Builder();
  }
}
