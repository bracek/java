package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author bracek
 */
@Entity
@Table(name = "student", catalog = "mk", schema = "public")
@NamedQueries({
		@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
		@NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
		@NamedQuery(name = "Student.findByName", query = "SELECT s FROM Student s WHERE s.name = :name") })
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "name", length = 255)
	private String name;
	@JoinColumn(name = "house_id", referencedColumnName = "id")
	@ManyToOne
	private House houseId;

	public Student() {
	}

	public Student(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public House getHouseId() {
		return houseId;
	}

	public void setHouseId(final House houseId) {
		this.houseId = houseId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += id != null ? id.hashCode() : 0;
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Student)) {
			return false;
		}
		final Student other = (Student) object;
		if (id == null && other.id != null || id != null
				&& !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sk.mka.bean.Student[id=" + id + "]";
	}

}
