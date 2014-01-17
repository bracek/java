package com.ixonos.skillnet.web.usermanagement.find;

import java.io.Serializable;
import java.util.Date;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Skill;

public class QueryModel implements Comparable<QueryModel>{
	
	private Skill skill;
	private CodeTable level;
	private Integer length;
	private Date dateFrom;
	private Date dateTo;
	
	public Skill getSkill() {
		return skill;
	}
	
	public void setSkill(final Skill skill) {
		this.skill = skill;
	}
	
	public CodeTable getLevel() {
		return level;
	}
	
	public void setLevel(final CodeTable level) {
		this.level = level;
	}
	
	public Integer getLength() {
		return length;
	}
	
	public void setLength(final Integer length) {
		this.length = length;
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
	
	@Override
    public String toString() {
        return "com.ixonos.skillnet.web.usermanagement.find.QueryModel[skill=" + skill.getName() 
        + ", level=" + level.getCode() + ", length=" + length + "]";
    }
	
	@Override
	public int compareTo(final QueryModel qModel) {
		return getSkill().getName().compareTo(qModel.getSkill().getName());
	}
}
