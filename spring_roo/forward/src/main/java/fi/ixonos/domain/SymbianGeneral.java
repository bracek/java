/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bracek
 */
@Entity
@Table(name = "symbian_general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SymbianGeneral.findAll", query = "SELECT s FROM SymbianGeneral s"),
    @NamedQuery(name = "SymbianGeneral.findById", query = "SELECT s FROM SymbianGeneral s WHERE s.id = :id"),
    @NamedQuery(name = "SymbianGeneral.findByVersion", query = "SELECT s FROM SymbianGeneral s WHERE s.version = :version")})
public class SymbianGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "application_icon")
    private byte[] applicationIcon;
    @Column(name = "version")
    private Integer version;

    public SymbianGeneral() {
    }

    public SymbianGeneral(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(byte[] applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SymbianGeneral)) {
            return false;
        }
        SymbianGeneral other = (SymbianGeneral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.SymbianGeneral[ id=" + id + " ]";
    }
    
}
