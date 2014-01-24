package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author bracek
 */
@Entity
@Table(name = "book",catalog = "mk",schema = "public")
@NamedQueries({
		@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
		@NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
		@NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.name = :name"),
		@NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
		@NamedQuery(name = "Book.findByPhone", query = "SELECT b FROM Book b WHERE b.phone = :phone") })
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "name", length = 255)
	private String name;
	@Column(name = "author", length = 255)
	private String author;
	@Column(name = "phone", length = 255)
	private String phone;

	public Book() {
	}

	public Book(final Integer id) {
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
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
		if (!(object instanceof Book)) {
			return false;
		}
		final Book other = (Book) object;
		if (id == null && other.id != null || id != null
				&& !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sk.mka.bean.Book[id=" + id + "]";
	}

}
