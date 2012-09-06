/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "code_table",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"group_code", "code"})}
)
@NamedQueries({
	@NamedQuery(name = "CodeTable.findAll", query = "SELECT c FROM CodeTable c"), 
	@NamedQuery(name = "CodeTable.findByCodeTableId", query = "SELECT c FROM CodeTable c WHERE c.codeTableId = :codeTableId"), 
	@NamedQuery(name = "CodeTable.findByGroupCode", query = "SELECT c FROM CodeTable c WHERE c.groupCode = :groupCode"), 
	@NamedQuery(name = "CodeTable.findByCode", query = "SELECT c FROM CodeTable c WHERE c.code = :code"), 
	@NamedQuery(name = "CodeTable.findByDescription", query = "SELECT c FROM CodeTable c WHERE c.description = :description")})
public class CodeTable implements Serializable, Comparable<CodeTable> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key for CodeTable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_table_id", nullable = false)
    private Integer codeTableId;

    @Basic(optional = false)
    @Column(name = "group_code", nullable = false, length = 32)
    private String groupCode;

    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 32)
    private String code;

    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 64)
    private String description;

    @OneToMany(mappedBy = "level")
    private List<Practicum> practicumCollection;

    @Column(name = "index", nullable = false)
    private Integer index;
    
    public CodeTable() {
    }

    public CodeTable(Integer codeTableId) {
        this.codeTableId = codeTableId;
    }

    public CodeTable(Integer codeTableId, String groupCode, String code, String description) {
        this.codeTableId = codeTableId;
        this.groupCode = groupCode;
        this.code = code;
        this.description = description;
    }

    public Integer getCodeTableId() {
        return codeTableId;
    }

    public void setCodeTableId(Integer codeTableId) {
        this.codeTableId = codeTableId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Practicum> getPracticumCollection() {
        return practicumCollection;
    }

    public void setPracticumCollection(List<Practicum> practicumCollection) {
        this.practicumCollection = practicumCollection;
    }

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeTableId != null ? codeTableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodeTable)) {
            return false;
        }
        CodeTable other = (CodeTable) object;
        if ((this.codeTableId == null && other.codeTableId != null) || (this.codeTableId != null && !this.codeTableId.equals(other.codeTableId))) {
            return false;
        }
        return true;
    }
       
    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.CodeTable[codeTableId=" + codeTableId + "]";
    }

	@Override
	public int compareTo(CodeTable codeTable) {
		if (getIndex() < (codeTable).getIndex()) {
			return -1;
		} else if (getIndex() > (codeTable).getIndex()) {
			return 1;
		} else {
			return 0;
		}
	}
    
}
