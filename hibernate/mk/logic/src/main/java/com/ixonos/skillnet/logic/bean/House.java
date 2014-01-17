package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author bracek
 */
@Entity
@Table(name = "house", catalog = "mk", schema = "public")
@NamedQueries({
		@NamedQuery(name = "House.findAll", query = "SELECT h FROM House h"),
		@NamedQuery(name = "House.findById", query = "SELECT h FROM House h WHERE h.id = :id"),
		@NamedQuery(name = "House.findByName", query = "SELECT h FROM House h WHERE h.name = :name") })
public class House implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "name", length = 255)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "houseFk")
	private List<House> houseCollection;
	@JoinColumn(name = "house_fk", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private House houseFk;
	@OneToMany(mappedBy = "houseId")
	private List<Student> studentCollection;

	public House() {
	}

	public House(final Integer id) {
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

	public List<House> getHouseCollection() {
		return houseCollection;
	}

	public void setHouseCollection(final List<House> houseCollection) {
		this.houseCollection = houseCollection;
	}

	public House getHouseFk() {
		return houseFk;
	}

	public void setHouseFk(final House houseFk) {
		this.houseFk = houseFk;
	}

	public List<Student> getStudentCollection() {
		return studentCollection;
	}

	public void setStudentCollection(final List<Student> studentCollection) {
		this.studentCollection = studentCollection;
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
		if (!(object instanceof House)) {
			return false;
		}
		final House other = (House) object;
		if (id == null && other.id != null || id != null
				&& !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sk.mka.bean.House[id=" + id + "]";
	}

}
