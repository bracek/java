/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bracek
 */
@Entity
@Table(name = "pizza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p"),
    @NamedQuery(name = "Pizza.findById", query = "SELECT p FROM Pizza p WHERE p.id = :id"),
    @NamedQuery(name = "Pizza.findByName", query = "SELECT p FROM Pizza p WHERE p.name = :name"),
    @NamedQuery(name = "Pizza.findByPrice", query = "SELECT p FROM Pizza p WHERE p.price = :price"),
    @NamedQuery(name = "Pizza.findByVersion", query = "SELECT p FROM Pizza p WHERE p.version = :version")})
public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Float price;
    @Column(name = "version")
    private Integer version;
    @ManyToMany(mappedBy = "pizzaCollection")
    private Collection<Topping> toppingCollection;
    @JoinTable(name = "pizza_order_pizzas", joinColumns = {
        @JoinColumn(name = "pizzas", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "pizza_order", referencedColumnName = "id")})
    @ManyToMany
    private Collection<PizzaOrder> pizzaOrderCollection;
    @JoinColumn(name = "base", referencedColumnName = "id")
    @ManyToOne
    private Base base;

    public Pizza() {
    }

    public Pizza(final Long id) {
        this.id = id;
    }

    public Pizza(final Long id,
final  String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(final Float price) {
        this.price = price;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    @XmlTransient
    public Collection<Topping> getToppingCollection() {
        return toppingCollection;
    }

    public void setToppingCollection(final Collection<Topping> toppingCollection) {
        this.toppingCollection = toppingCollection;
    }

    @XmlTransient
    public Collection<PizzaOrder> getPizzaOrderCollection() {
        return pizzaOrderCollection;
    }

    public void setPizzaOrderCollection(final Collection<PizzaOrder> pizzaOrderCollection) {
        this.pizzaOrderCollection = pizzaOrderCollection;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(final Base base) {
        this.base = base;
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
        if (!(object instanceof Pizza)) {
            return false;
        }
        Pizza other = (Pizza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.Pizza[ id=" + id + " ]";
    }
    
}
