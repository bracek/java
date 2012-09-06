package stibrik.springapp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "product")
public class Product implements Serializable {

	// -------------------------------- ATTRS ----------------------------------
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id
	 */
	@Id
	private Integer id;
	
	/**
	 * Price
	 */

	@Column(name = "price")
	private Double price;
	
	/**
	 * Description
	 */
	@Column(name = "description")
	private String description;
	
	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------	
	/**
	 * This method returns value of field <code>id</code>
	 * @return The id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * This method sets value o field <code>id</code>.
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * This method returns value of field <code>price</code>
	 * @return The price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * This method sets value o field <code>price</code>.
	 * @param price The price to set.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * This method returns value of field <code>description</code>
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * This method sets value o field <code>description</code>.
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "\n";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("Product ( ")
	        .append(super.toString()).append(TAB)
	        .append("id = ").append(this.id).append(TAB)
	        .append("price = ").append(this.price).append(TAB)
	        .append("description = ").append(this.description).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}	
}
