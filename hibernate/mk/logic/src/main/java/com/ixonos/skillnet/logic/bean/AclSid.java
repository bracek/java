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
@Table(name = "acl_sid")
@NamedQueries({
	@NamedQuery(name = "AclSid.findAll", query = "SELECT a FROM AclSid a"), 
	@NamedQuery(name = "AclSid.findById", query = "SELECT a FROM AclSid a WHERE a.id = :id"), 
	@NamedQuery(name = "AclSid.findByPrincipal", query = "SELECT a FROM AclSid a WHERE a.principal = :principal"), 
	@NamedQuery(name = "AclSid.findBySid", query = "SELECT a FROM AclSid a WHERE a.sid = :sid")})
public class AclSid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "principal", nullable = false)
    private Boolean principal;

    @Basic(optional = false)
    @Column(name = "sid", nullable = false, length = 100)
    private String sid;

    @OneToMany(mappedBy = "ownerSid")
    private List<AclObjectIdentity> aclObjectIdentityCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sid")
    private List<AclEntry> aclEntryCollection;

    public AclSid() {
    }

    public AclSid(Integer id) {
        this.id = id;
    }

    public AclSid(Integer id, Boolean principal, String sid) {
        this.id = id;
        this.principal = principal;
        this.sid = sid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<AclObjectIdentity> getAclObjectIdentityCollection() {
        return aclObjectIdentityCollection;
    }

    public void setAclObjectIdentityCollection(List<AclObjectIdentity> aclObjectIdentityCollection) {
        this.aclObjectIdentityCollection = aclObjectIdentityCollection;
    }

    public List<AclEntry> getAclEntryCollection() {
        return aclEntryCollection;
    }

    public void setAclEntryCollection(List<AclEntry> aclEntryCollection) {
        this.aclEntryCollection = aclEntryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AclSid)) {
            return false;
        }
        AclSid other = (AclSid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.AclSid[id=" + id + "]";
    }

}
