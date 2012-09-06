/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "acl_entry")
@NamedQueries({
	@NamedQuery(name = "AclEntry.findAll", query = "SELECT a FROM AclEntry a"), 
	@NamedQuery(name = "AclEntry.findById", query = "SELECT a FROM AclEntry a WHERE a.id = :id"), 
	@NamedQuery(name = "AclEntry.findByAceOrder", query = "SELECT a FROM AclEntry a WHERE a.aceOrder = :aceOrder"), 
	@NamedQuery(name = "AclEntry.findByMask", query = "SELECT a FROM AclEntry a WHERE a.mask = :mask"), 
	@NamedQuery(name = "AclEntry.findByGranting", query = "SELECT a FROM AclEntry a WHERE a.granting = :granting"), 
	@NamedQuery(name = "AclEntry.findByAuditSuccess", query = "SELECT a FROM AclEntry a WHERE a.auditSuccess = :auditSuccess"), 
	@NamedQuery(name = "AclEntry.findByAuditFailure", query = "SELECT a FROM AclEntry a WHERE a.auditFailure = :auditFailure")})
public class AclEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "ace_order", nullable = false)
    private Integer aceOrder;

    @Basic(optional = false)
    @Column(name = "mask", nullable = false)
    private Integer mask;

    @Basic(optional = false)
    @Column(name = "granting", nullable = false)
    private Boolean granting;

    @Basic(optional = false)
    @Column(name = "audit_success", nullable = false)
    private Boolean auditSuccess;

    @Basic(optional = false)
    @Column(name = "audit_failure", nullable = false)
    private Boolean auditFailure;

    @JoinColumn(name = "acl_object_identity", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private AclObjectIdentity aclObjectIdentity;

    @JoinColumn(name = "sid", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private AclSid sid;

    public AclEntry() {
    }

    public AclEntry(Integer id) {
        this.id = id;
    }

    public AclEntry(Integer id, Integer aceOrder, Integer mask, Boolean granting, Boolean auditSuccess, Boolean auditFailure) {
        this.id = id;
        this.aceOrder = aceOrder;
        this.mask = mask;
        this.granting = granting;
        this.auditSuccess = auditSuccess;
        this.auditFailure = auditFailure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

    public AclObjectIdentity getAclObjectIdentity() {
        return aclObjectIdentity;
    }

    public void setAclObjectIdentity(AclObjectIdentity aclObjectIdentity) {
        this.aclObjectIdentity = aclObjectIdentity;
    }

    public AclSid getSid() {
        return sid;
    }

    public void setSid(AclSid sid) {
        this.sid = sid;
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
        if (!(object instanceof AclEntry)) {
            return false;
        }
        AclEntry other = (AclEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.AclEntry[id=" + id + "]";
    }

}
