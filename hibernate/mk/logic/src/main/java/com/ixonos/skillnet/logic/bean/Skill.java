/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import com.ixonos.skillnet.logic.bean.common.AbstractBean;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author magurja
 */
@Entity
@Table(name = "Skill")
@NamedQueries({
	@NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s"), 
	@NamedQuery(name = "Skill.findBySkillId", query = "SELECT s FROM Skill s WHERE s.skillId = :skillId"),
	@NamedQuery(name = "Skill.findByName", query = "SELECT s FROM Skill s WHERE s.name = :name"), 
	@NamedQuery(name = "Skill.findByDescription", query = "SELECT s FROM Skill s WHERE s.description = :description"), 
	@NamedQuery(name = "Skill.findByCreated", query = "SELECT s FROM Skill s WHERE s.created = :created"), 
	@NamedQuery(name = "Skill.findByModified", query = "SELECT s FROM Skill s WHERE s.modified = :modified"), 
	@NamedQuery(name = "Skill.findByValuable", query = "SELECT s FROM Skill s WHERE s.valuable = :valuable")})
public class Skill /*extends AbstractBean*/ implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id", nullable = false)
    private Integer skillId;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(name = "modified")
    @Temporal(TemporalType.DATE)
    private Date modified;

    @Basic(optional = false)
    @Column(name = "valuable", nullable = false)
    private Boolean valuable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill")
    private List<Node> nodeCollection;

    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users createdBy;

    @JoinColumn(name = "modified_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users modifiedBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillId")
    private List<Practicum> practicumCollection;
    
    @Column(name = "keyword", length = 32)
    private String keyWord;

    public Skill() {
    }

    public Skill(final Integer skillId) {
        this.skillId = skillId;
    }

    public Skill(final Integer skillId,final  String name,final  Date created,final  Boolean valuable) {
        this.skillId = skillId;
        this.name = name;
        this.created = created;
        this.valuable = valuable;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(final Integer skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(final Date modified) {
        this.modified = modified;
    }

    public Boolean getValuable() {
        return valuable;
    }

    public void setValuable(final Boolean valuable) {
        this.valuable = valuable;
    }

    public List<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(final List<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final Users createdBy) {
        this.createdBy = createdBy;
    }

    public Users getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final Users modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public List<Practicum> getPracticumCollection() {
        return practicumCollection;
    }

    public void setPracticumCollection(final List<Practicum> practicumCollection) {
        this.practicumCollection = practicumCollection;
    }

    public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyWord() {
		return keyWord;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (skillId != null ? skillId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.skillId == null && other.skillId != null) || (this.skillId != null && !this.skillId.equals(other.skillId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.Skill[skillId=" + skillId + ", name=" + name + "]";
        //return super.toString();
    }

}
