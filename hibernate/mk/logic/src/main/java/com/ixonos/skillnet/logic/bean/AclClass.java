/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "acl_class")
@NamedQueries({
	@NamedQuery(name = "AclClass.findAll", query = "SELECT a FROM AclClass a"), 
	@NamedQuery(name = "AclClass.findById", query = "SELECT a FROM AclClass a WHERE a.id = :id"), 
	@NamedQuery(name = "AclClass.findByClass1", query = "SELECT a FROM AclClass a WHERE a.class1 = :class1")})
public class AclClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "class", nullable = false, length = 100)
    private String class1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objectIdClass")
    private List<AclObjectIdentity> aclObjectIdentityCollection;

    public AclClass() {
    }

    public AclClass(final Integer id) {
        this.id = id;
    }

    public AclClass(final Integer id,final  String class1) {
        this.id = id;
        this.class1 = class1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(final String class1) {
        this.class1 = class1;
    }

    public List<AclObjectIdentity> getAclObjectIdentityCollection() {
        return aclObjectIdentityCollection;
    }

    public void setAclObjectIdentityCollection(final List<AclObjectIdentity> aclObjectIdentityCollection) {
        this.aclObjectIdentityCollection = aclObjectIdentityCollection;
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
        if (!(object instanceof AclClass)) {
            return false;
        }
        AclClass other = (AclClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.AclClass[id=" + id + "]";
    }

}
