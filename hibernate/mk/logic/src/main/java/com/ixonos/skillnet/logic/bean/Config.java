package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author stibron
 *
 */
@Entity
@Table(name = "config")
@NamedQueries({
	@NamedQuery(name = "Config.findAll", query = "SELECT c FROM Config c"),
	@NamedQuery(name = "Config.findByProperty", query = "SELECT c FROM Config c WHERE c.property = :property") 
})
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "property", nullable = false, unique = true)
	private String property;
	
    @Column(name = "value", nullable = true)
	private String value;
	
    @Column(name = "description", nullable = true)
	private String description;
	
	/**
	 * Common constructor
	 */
	public Config() {
		super();
	}

	/**
	 * Field constructor
	 * 
	 * @param property
	 * @param value
	 * @param description
	 */
	public Config(String property, String value, String description) {
		super();
		this.property = property;
		this.value = value;
		this.description = description;
	}
	
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Config))
			return false;
		Config castOther = (Config) other;
		return new EqualsBuilder().append(property, castOther.property)
				.isEquals();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(property).toHashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("property", property)
				.append("value", value)
				.append("description", description)
				.toString();
	}
}
