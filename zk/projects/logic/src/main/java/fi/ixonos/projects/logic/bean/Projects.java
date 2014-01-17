package fi.ixonos.projects.logic.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author katrami
 * @date Oct 28, 2010
 */
@Entity
@Table(name = "projects", uniqueConstraints =
@UniqueConstraint(columnNames = {"projects_id", "name"}))
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
    @NamedQuery(name = "Projects.findByProjectsId", query = "SELECT p FROM Projects p WHERE p.projectsId = :projectsId"),
    @NamedQuery(name = "Projects.findByName", query = "SELECT p FROM Projects p WHERE p.name = :name"),
    @NamedQuery(name = "Projects.findByDescription", query = "SELECT p FROM Projects p WHERE p.description = :description"),
    @NamedQuery(name = "Projects.findByDateFrom", query = "SELECT p FROM Projects p WHERE p.dateFrom = :dateFrom"),
    @NamedQuery(name = "Projects.findByDateTo", query = "SELECT p FROM Projects p WHERE p.dateTo = :dateTo")})
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "projects_id")
    private Integer projectsId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Users.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "projects_users", joinColumns = {
        @JoinColumn(name = "project_id", referencedColumnName = "projects_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, uniqueConstraints =
    @UniqueConstraint(columnNames = {"project_id", "user_id"}))
    private Collection<Users> usersCollection;
    @ManyToMany(mappedBy = "projectsOpenedCollection", targetEntity = Users.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Users> usersOpenedCollection;
    @Transient
    private boolean dirty = false;

    public Projects() {
    }

    public Projects(final Integer projectsId) {
        this.projectsId = projectsId;
    }

    public Integer getProjectsId() {
        return projectsId;
    }

    public void setProjectsId(final Integer projectsId) {
        this.projectsId = projectsId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(final Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<Users> getUsersOpenedCollection() {
        return usersOpenedCollection;
    }

    public void setUsersOpenedCollection(final Collection<Users> usersOpenedCollection) {
        this.usersOpenedCollection = usersOpenedCollection;
    }

    public void setDirty(final boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty(){
        return dirty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectsId != null ? projectsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.projectsId == null && other.projectsId != null) || (this.projectsId != null && !this.projectsId.equals(other.projectsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fi.ixonos.projects.logic.bean.Projects[projectsId=" + projectsId + "]";
    }
}
