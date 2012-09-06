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

import org.hibernate.annotations.Cascade;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "node", uniqueConstraints = @UniqueConstraint(columnNames = {"parent_node_id", "skill_id"}))
@NamedQueries({
	@NamedQuery(name = "Node.findAll", query = "SELECT n FROM Node n"), 
	@NamedQuery(name = "Node.findByNodeId", query = "SELECT n FROM Node n WHERE n.nodeId = :nodeId")})
public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "node_id", nullable = false)
    private Integer nodeId;
     			
    @OneToMany( mappedBy = "parentNode", cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			  org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private List<Node> children;
      
    @JoinColumn(name = "parent_node_id", referencedColumnName = "node_id")
    @ManyToOne()
    private Node parentNode;

    
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id", nullable = false)
    @ManyToOne(optional = false)
    private Skill skill;

    public Node() {
    }

    public Node(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeId != null ? nodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.Node[nodeId=" + nodeId + "]";
    }

}
