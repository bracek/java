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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "authority", uniqueConstraints = @UniqueConstraint(columnNames = {"authority", "user_id"}))
@NamedQueries({
	@NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a"), 
	@NamedQuery(name = "Authority.findByAuthorityId", query = "SELECT a FROM Authority a WHERE a.authorityId = :authorityId"), 
	@NamedQuery(name = "Authority.findByAuthority", query = "SELECT a FROM Authority a WHERE a.authority = :authority")})
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id", nullable = false)
    private Integer authorityId;

    @JoinColumn(name = "authority", referencedColumnName = "code_table_id", nullable = false)
    @ManyToOne(optional = false)
    private CodeTable authority;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private Users user;

    public Authority() {
    }

    public Authority(final Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Authority(final Integer authorityId,
final  CodeTable authority) {
        this.authorityId = authorityId;
        this.authority = authority;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(final Integer authorityId) {
        this.authorityId = authorityId;
    }

    public CodeTable getAuthority() {
        return authority;
    }

    public void setAuthority(final CodeTable authority) {
        this.authority = authority;
    }

    public Users getUser() {
		return user;
	}

	public void setUser(final Users user) {
		this.user = user;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorityId != null ? authorityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.authorityId == null && other.authorityId != null) || (this.authorityId != null && !this.authorityId.equals(other.authorityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.Authority[authorityId=" + authorityId + ", name= " + authority + "]";
    }
}
