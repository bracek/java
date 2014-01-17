package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author bracek
 */
@Entity
@Table(name = "osoba", catalog = "mk", schema = "public")
@NamedQueries({
		@NamedQuery(name = "Osoba.findAll", query = "SELECT o FROM Osoba o"),
		@NamedQuery(name = "Osoba.findById", query = "SELECT o FROM Osoba o WHERE o.id = :id"),
		@NamedQuery(name = "Osoba.findByPassword", query = "SELECT o FROM Osoba o WHERE o.password = :password"),
		@NamedQuery(name = "Osoba.findByFirstName", query = "SELECT o FROM Osoba o WHERE o.firstName = :firstName"),
		@NamedQuery(name = "Osoba.findByLastName", query = "SELECT o FROM Osoba o WHERE o.lastName = :lastName"),
		@NamedQuery(name = "Osoba.findByCreated", query = "SELECT o FROM Osoba o WHERE o.created = :created"),
		@NamedQuery(name = "Osoba.findByPasswordRepeat", query = "SELECT o FROM Osoba o WHERE o.passwordRepeat = :passwordRepeat"),
		@NamedQuery(name = "Osoba.findByLogin", query = "SELECT o FROM Osoba o WHERE o.login = :login") })
public class Osoba implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "password", length = 255)
	private String password;
	@Column(name = "first_name", length = 255)
	private String firstName;
	@Column(name = "last_name", length = 255)
	private String lastName;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "password_repeat", length = 255)
	private String passwordRepeat;
	@Column(name = "login", length = 255)
	private String login;

	public Osoba() {
	}

	public Osoba(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(final String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
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
		if (!(object instanceof Osoba)) {
			return false;
		}
		final Osoba other = (Osoba) object;
		if (id == null && other.id != null || id != null
				&& !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sk.mka.bean.Osoba[id=" + id + "]";
	}
}
