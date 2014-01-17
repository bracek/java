package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author bracek
 */
@Entity
@Table(name = "product", catalog = "mk", schema = "public")
@NamedQueries({
		@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
		@NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
		@NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description") })
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "product_id", allocationSize = 1)
	private Integer id;
	@Column(name = "price", precision = 17, scale = 17)
	private Double price;
	@Column(name = "description", length = 255)
	private String description;

	public Product() {
	}

	public Product(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += id != null ? id.hashCode() : 0;
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Product)) {
			return false;
		}
		final Product other = (Product) object;
		if (id == null && other.id != null || id != null
				&& !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sk.mka.bean.Product[id=" + id + "]";
	}

}
