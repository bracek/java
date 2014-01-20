/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ixonos.domain;

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
@Table(name = "topping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topping.findAll", query = "SELECT t FROM Topping t"),
    @NamedQuery(name = "Topping.findById", query = "SELECT t FROM Topping t WHERE t.id = :id"),
    @NamedQuery(name = "Topping.findByName", query = "SELECT t FROM Topping t WHERE t.name = :name"),
    @NamedQuery(name = "Topping.findByVersion", query = "SELECT t FROM Topping t WHERE t.version = :version")})

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Topping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "version")
    private Integer version;
    @JoinTable(name = "pizza_toppings", joinColumns = {
        @JoinColumn(name = "toppings", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "pizza", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Pizza> pizzaCollection;

    public Topping() {
    }

    public Topping(final Long id) {
        this.id = id;
    }

    public Topping(final Long id,
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
        if (!(object instanceof Topping)) {
            return false;
        }
        Topping other = (Topping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.Topping[ id=" + id + " ]";
    }
    
}
