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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "acl_object_identity")
@NamedQueries({
	@NamedQuery(name = "AclObjectIdentity.findAll", query = "SELECT a FROM AclObjectIdentity a"), 
	@NamedQuery(name = "AclObjectIdentity.findById", query = "SELECT a FROM AclObjectIdentity a WHERE a.id = :id"), 
	@NamedQuery(name = "AclObjectIdentity.findByObjectIdIdentity", query = "SELECT a FROM AclObjectIdentity a WHERE a.objectIdIdentity = :objectIdIdentity"), 
	@NamedQuery(name = "AclObjectIdentity.findByEntriesInheriting", query = "SELECT a FROM AclObjectIdentity a WHERE a.entriesInheriting = :entriesInheriting")})
public class AclObjectIdentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "object_id_identity", nullable = false)
    private Long objectIdIdentity;

    @Basic(optional = false)
    @Column(name = "entries_inheriting", nullable = false)
    private Boolean entriesInheriting;

    @JoinColumn(name = "object_id_class", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private AclClass objectIdClass;

    @OneToMany(mappedBy = "parentObject")
    private List<AclObjectIdentity> aclObjectIdentityCollection;

    @JoinColumn(name = "parent_object", referencedColumnName = "id")
    @ManyToOne
    private AclObjectIdentity parentObject;

    @JoinColumn(name = "owner_sid", referencedColumnName = "id")
    @ManyToOne
    private AclSid ownerSid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aclObjectIdentity")
    private List<AclEntry> aclEntryCollection;

    public AclObjectIdentity() {
    }

    public AclObjectIdentity(final Integer id) {
        this.id = id;
    }

    public AclObjectIdentity(final Integer id,final  Long objectIdIdentity,final  Boolean entriesInheriting) {
        this.id = id;
        this.objectIdIdentity = objectIdIdentity;
        this.entriesInheriting = entriesInheriting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Long getObjectIdIdentity() {
        return objectIdIdentity;
    }

    public void setObjectIdIdentity(final Long objectIdIdentity) {
        this.objectIdIdentity = objectIdIdentity;
    }

    public Boolean getEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(final Boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }

    public AclClass getObjectIdClass() {
        return objectIdClass;
    }

    public void setObjectIdClass(final AclClass objectIdClass) {
        this.objectIdClass = objectIdClass;
    }

    public List<AclObjectIdentity> getAclObjectIdentityCollection() {
        return aclObjectIdentityCollection;
    }

    public void setAclObjectIdentityCollection(final List<AclObjectIdentity> aclObjectIdentityCollection) {
        this.aclObjectIdentityCollection = aclObjectIdentityCollection;
    }

    public AclObjectIdentity getParentObject() {
        return parentObject;
    }

    public void setParentObject(final AclObjectIdentity parentObject) {
        this.parentObject = parentObject;
    }

    public AclSid getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(final AclSid ownerSid) {
        this.ownerSid = ownerSid;
    }

    public List<AclEntry> getAclEntryCollection() {
        return aclEntryCollection;
    }

    public void setAclEntryCollection(final List<AclEntry> aclEntryCollection) {
        this.aclEntryCollection = aclEntryCollection;
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
        if (!(object instanceof AclObjectIdentity)) {
            return false;
        }
        AclObjectIdentity other = (AclObjectIdentity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.AclObjectIdentity[id=" + id + "]";
    }

}
