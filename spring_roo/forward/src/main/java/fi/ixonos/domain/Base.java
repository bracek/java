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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bracek
 */
@Entity
@Table(name = "base")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Base.findAll", query = "SELECT b FROM Base b"),
    @NamedQuery(name = "Base.findById", query = "SELECT b FROM Base b WHERE b.id = :id"),
    @NamedQuery(name = "Base.findByName", query = "SELECT b FROM Base b WHERE b.name = :name"),
    @NamedQuery(name = "Base.findByVersion", query = "SELECT b FROM Base b WHERE b.version = :version")})
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "icon")
    private byte[] icon;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "version")
    private Integer version;
    @OneToMany(mappedBy = "base")
    private Collection<Pizza> pizzaCollection;

    public Base() {
    }

    public Base(final Long id) {
        this.id = id;
    }

    public Base(final Long id,
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(final byte[] icon) {
        this.icon = icon;
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
        if (!(object instanceof Base)) {
            return false;
        }
        Base other = (Base) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.Base[ id=" + id + " ]";
    }
    
}
