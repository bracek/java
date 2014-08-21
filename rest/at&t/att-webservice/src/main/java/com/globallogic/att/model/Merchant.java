package com.globallogic.att.model;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = { @UniqueConstraint(name = "merchant_title_uk", columnNames = { "title" }) })
@SuppressWarnings("serial")
public class Merchant implements Serializable {

  @XmlElement
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @XmlElement
  @Column(length = 50, unique = true, nullable = false)
  private String title;

  @OneToMany(mappedBy = "merchant", fetch = LAZY, orphanRemoval = true, cascade = {
      PERSIST, MERGE, REMOVE, REFRESH, DETACH })
  private List<Store> stores;

  public Merchant() {
    this(null);
  }
  
  public Merchant(Integer id) {
    super();
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Store> getStores() {
    return stores;
  }

  public void setStores(List<Store> stores) {
    this.stores = stores;
  }
  
  public static class Builder implements Serializable {
    
    public final Merchant entity;
    
    private Builder() {
      super();
      entity = new Merchant();
    }
    
    public Builder id(Integer id) {
      entity.setId(id);
      return this;
    }
    
    public Builder title(String title) {
      entity.setTitle(title);
      return this;
    }
    
    public Builder stores(List<Store> stores) {
      entity.setStores(stores);
      return this;
    }
  }
  
  public static Builder newBuilder() {
    return new Builder();
  }
}
