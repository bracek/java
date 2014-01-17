
package fi.ixonos.projects.logic.bean;

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
 * @author katrami
 * @date Oct 18, 2010
 */
@Entity
@Table(name = "config")
@NamedQueries({
    @NamedQuery(name = "Config.findAll", query = "SELECT c FROM Config c"),
    @NamedQuery(name = "Config.findByProperty", query = "SELECT c FROM Config c WHERE c.property = :property"),
    @NamedQuery(name = "Config.findByValue", query = "SELECT c FROM Config c WHERE c.value = :value"),
    @NamedQuery(name = "Config.findByDescription", query = "SELECT c FROM Config c WHERE c.description = :description")})
public class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "property")
    private String property;
    @Column(name = "value")
    private String value;
    @Column(name = "description")
    private String description;

    public Config() {
    }

    public Config(final String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(final String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
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
        hash += (property != null ? property.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.property == null && other.property != null) || (this.property != null && !this.property.equals(other.property))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fi.ixonos.projects.logic.bean.Config[property=" + property + "]";
    }

}
