package com.ixonos.skillnet.logic.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author bracek
 */
@Entity
@Table(name = "message", catalog = "mk", schema = "public")
@NamedQueries({
		@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
		@NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
		@NamedQuery(name = "Message.findByMessage", query = "SELECT m FROM Message m WHERE m.message = :message"),
		@NamedQuery(name = "Message.findByUserfrom", query = "SELECT m FROM Message m WHERE m.userfrom = :userfrom") })
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "message_id", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "message", length = 255)
	private String message;
	@Column(name = "userfrom", length = 255)
	private String userfrom;

	public Message() {
	}

	public Message(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getUserfrom() {
		return userfrom;
	}

	public void setUserfrom(final String userfrom) {
		this.userfrom = userfrom;
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
		if (!(object instanceof Message)) {
			return false;
		}
		final Message other = (Message) object;
		if (id == null && other.id != null || id != null
				&& !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sk.mka.bean.Message[id=" + id + "]";
	}

}
