/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author magurja
 */
@Entity
@Table(name = "practicum")
@NamedQueries({
        @NamedQuery(name = "Practicum.findAll", query = "SELECT p FROM Practicum p"),
        @NamedQuery(name = "Practicum.findByPracticumId", query = "SELECT p FROM Practicum p WHERE p.practicumId = :practicumId"),
        @NamedQuery(name = "Practicum.findByDateFrom", query = "SELECT p FROM Practicum p WHERE p.dateFrom = :dateFrom"),
        @NamedQuery(name = "Practicum.findByDateTo", query = "SELECT p FROM Practicum p WHERE p.dateTo = :dateTo")})
public class Practicum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "practicum_id", nullable = false)
    private Integer practicumId;

    @Basic(optional = false)
    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @JoinColumn(name = "level", referencedColumnName = "code_table_id", nullable = false)
    @ManyToOne(optional = false)
    private CodeTable level;

    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id", nullable = false)
    @ManyToOne(optional = false)
    private Skill skillId;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;
    
    @Column(name = "description", nullable = true, length = 128)
    private String description;

    public Practicum() {
    }

    public Practicum(final Integer practicumId) {
        this.practicumId = practicumId;
    }

    public Practicum(final Integer practicumId,
final  Date dateFrom) {
        this.practicumId = practicumId;
        this.dateFrom = dateFrom;
    }

    public Integer getPracticumId() {
        return practicumId;
    }

    public void setPracticumId(final Integer practicumId) {
        this.practicumId = practicumId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(final Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(final Date dateTo) {
        this.dateTo = dateTo;
    }

    public CodeTable getLevel() {
        return level;
    }

    public void setLevel(final CodeTable level) {
        this.level = level;
    }

    public Skill getSkillId() {
        return skillId;
    }

    public void setSkillId(final Skill skillId) {
        this.skillId = skillId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(final Users userId) {
        this.userId = userId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (practicumId != null ? practicumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Practicum)) {
            return false;
        }
        Practicum other = (Practicum) object;
        if ((this.practicumId == null && other.practicumId != null) || (this.practicumId != null && !this.practicumId.equals(other.practicumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.Practicum[practicumId=" + practicumId + "]";
    }

}
