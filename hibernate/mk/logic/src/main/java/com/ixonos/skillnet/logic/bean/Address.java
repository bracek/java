/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;


/**
 * Annotation configured address bean.
 * 
 * @author David Winterfeldt
 */
@Entity
@Table(name="ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = 7851794269407495684L;
    
    private Integer id = null;
    private String address = null;
    private String city = null;
    private String state = null;
    private String zipPostal = null;
    private String country = null;
    private Date created = null;
    
    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(final Integer id) {
        this.id = id;
    }
    
    /**
     * Gets address.
     */
	public String getAddress() {
		return address;
	}

    /**
     * Sets address.
     */
	public void setAddress(final String address) {
		this.address = address;
	}

    /**
     * Gets city.
     */
	public String getCity() {
		return city;
	}

    /**
     * Sets city.
     */
	public void setCity(final String city) {
		this.city = city;
	}

    /**
     * Gets state.
     */
	public String getState() {
		return state;
	}

    /**
     * Sets state.
     */
	public void setState(final String state) {
		this.state = state;
	}

    /**
     * Gets zip or postal code.
     */
	@Column(name="ZIP_POSTAL")
	public String getZipPostal() {
		return zipPostal;
	}

    /**
     * Sets zip or postal code.
     */
	public void setZipPostal(final String zipPostal) {
		this.zipPostal = zipPostal;
	}

	/**
	 * Gets country.
	 */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets date created.
     */
	public Date getCreated() {
		return created;
	}

    /**
     * Sets date created.
     */
	public void setCreated(final Date created) {
		this.created = created;
	}
	
    /**
     * Returns a string representation of the object. 
     */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  addresss=" + address);
        sb.append("  city=" + city);
        sb.append("  state=" + state);
        sb.append("  zipPostal=" + zipPostal);
        sb.append("  country=" + country);
        sb.append("  created=" + created);
        
        return sb.toString();
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    /**
     * Indicates whether some other object is equal to this one.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Address other = (Address) obj;
    
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        
        return true;
    }

    /**
     * Validates 'addressForm' view state after binding to address.
     * Spring Web Flow activated validation ('validate' + ${state}).
     */
    public void validateAddressForm(final MessageContext context) {
        if (!StringUtils.hasText(address)) {
            context.addMessage(new MessageBuilder().error().source("address").code("address.form.address.error").build());
        }
        
        if (!StringUtils.hasText(city)) {
            context.addMessage(new MessageBuilder().error().source("city").code("address.form.city.error").build());
        }
        
        if (!StringUtils.hasText(state)) {
            context.addMessage(new MessageBuilder().error().source("state").code("address.form.state.error").build());
        }
        
        if (!StringUtils.hasText(zipPostal)) {
            context.addMessage(new MessageBuilder().error().source("zipPostal").code("address.form.zipPostal.error").build());
        }
        
        if (!StringUtils.hasText(country)) {
            context.addMessage(new MessageBuilder().error().source("country").code("address.form.country.error").build());
        }
    }
    
}
