/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author magurja
 */

@Entity
@Table(name = "practicum_view")
public class PracticumView implements Serializable {
    
	@Id
	@Column(name = "id")
	private Integer id;
	
    @Column(name = "username")
    private String username;
    
    @Column(name = "skill")
    private String skill;
    
    @Column(name = "level")
    private String level;
    
    @Column(name = "level_index")
    private Integer level_index;
    
    @Column(name = "first_name")
    private String first_name;
    
    @Column(name = "last_name")
    private String last_name;
    
    @Column(name = "full_name")
    private String full_name;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "location")
    private String location;

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setSkill(final String skill) {
		this.skill = skill;
	}

	public String getSkill() {
		return skill;
	}

	public void setLevel(final String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel_index(final Integer level_index) {
		this.level_index = level_index;
	}

	public Integer getLevel_index() {
		return level_index;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public void setFirst_name(final String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }
    
    public void setLast_name(final String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }
    
    public void setFull_name(final String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
    
    public void setPosition(final String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
