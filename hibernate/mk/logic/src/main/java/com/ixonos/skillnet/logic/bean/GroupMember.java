/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "group_member",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"group_id", "user_id"})}
)
@NamedQueries({
	@NamedQuery(name = "GroupMember.findAll", query = "SELECT g FROM GroupMember g"), 
	@NamedQuery(name = "GroupMember.findByGroupMemberId", query = "SELECT g FROM GroupMember g WHERE g.groupMemberId = :groupMemberId")})
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_member_id", nullable = false)
    private Integer groupMemberId;

    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
    @ManyToOne(optional = false)    
    private Groups group;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private Users user;

    public GroupMember() {
    }

    public GroupMember(final Integer groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public Integer getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(final Integer groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public Groups getGroup() {
		return group;
	}

	public void setGroup(final Groups group) {
		this.group = group;
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
        hash += (groupMemberId != null ? groupMemberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMember)) {
            return false;
        }
        GroupMember other = (GroupMember) object;
        if ((this.groupMemberId == null && other.groupMemberId != null) || (this.groupMemberId != null && !this.groupMemberId.equals(other.groupMemberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.GroupMember[groupMemberId=" + groupMemberId + ", name=" + user.getUsername() + "]";
    }
}
