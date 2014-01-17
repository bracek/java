/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bracek
 */
@Entity
@Table(name = "pizza_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PizzaOrder.findAll", query = "SELECT p FROM PizzaOrder p"),
    @NamedQuery(name = "PizzaOrder.findById", query = "SELECT p FROM PizzaOrder p WHERE p.id = :id"),
    @NamedQuery(name = "PizzaOrder.findByAddress", query = "SELECT p FROM PizzaOrder p WHERE p.address = :address"),
    @NamedQuery(name = "PizzaOrder.findByDeliveryDate", query = "SELECT p FROM PizzaOrder p WHERE p.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "PizzaOrder.findByName", query = "SELECT p FROM PizzaOrder p WHERE p.name = :name"),
    @NamedQuery(name = "PizzaOrder.findByTotal", query = "SELECT p FROM PizzaOrder p WHERE p.total = :total"),
    @NamedQuery(name = "PizzaOrder.findByVersion", query = "SELECT p FROM PizzaOrder p WHERE p.version = :version")})
public class PizzaOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Float total;
    @Column(name = "version")
    private Integer version;
    @ManyToMany(mappedBy = "pizzaOrderCollection")
    private Collection<Pizza> pizzaCollection;

    public PizzaOrder() {
    }

    public PizzaOrder(final Long id) {
        this.id = id;
    }

    public PizzaOrder(final Long id,final  String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(final Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(final Float total) {
        this.total = total;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    @XmlTransient
    public Collection<Pizza> getPizzaCollection() {
        return pizzaCollection;
    }

    public void setPizzaCollection(final Collection<Pizza> pizzaCollection) {
        this.pizzaCollection = pizzaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PizzaOrder)) {
            return false;
        }
        PizzaOrder other = (PizzaOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.PizzaOrder[ id=" + id + " ]";
    }
    
}
