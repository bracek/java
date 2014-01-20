package fi.ixonos.projects.logic.bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author magurja
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled")})
public class Users implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "telephoneNumber", nullable = true, length = 50)
    private String telephoneNumber;
    @Column(name = "location", nullable = true, length = 150)
    private String location;
    @Column(name = "position", nullable = true, length = 100)
    private String position;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 256)
    private String password;
    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Authority> authorityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<GroupMember> groupMemberCollection;
    @OneToMany(mappedBy = "manager")
    private List<Users> userCollection;
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users manager;
    @Transient
    private String authorities;
    private byte[] photo;
    @Transient
    private boolean photoUploaded;
    private byte[] curriculum;
    @Transient
    private boolean isCurriculumAlreadyFillUp;
    @ManyToMany(mappedBy = "usersCollection", targetEntity = Projects.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Projects> projectsCollection = Collections.emptyList();
    @ManyToMany(targetEntity = Projects.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "projects_opened_users", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "projects_id", referencedColumnName = "projects_id")})
    private Collection<Projects> projectsOpenedCollection = Collections.emptyList();

    public Users() {
    }

    public Users(final Integer userId) {
        this.userId = userId;
    }

    public Users(final Integer userId,
final  String username,
final  String password,
final  Boolean enabled) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Users> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(final List<Users> userCollection) {
        this.userCollection = userCollection;
    }

    public Users getManager() {
        return manager;
    }

    public void setManager(final Users manager) {
        this.manager = manager;
    }

    public void setAuthorities(final String authorities) {
        this.authorities = authorities;
    }

    public String getAuthorities() {
        return authorities;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(final String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public byte[] getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(final byte[] curriculum) {
        this.curriculum = curriculum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getHumanReadableUsername() {
        return this.getSurname() + " " + this.getName() + " [" + this.getUsername() + "]";
    }

    public boolean isIsCurriculumAlreadyFillUp() {
        return isCurriculumAlreadyFillUp;
    }

    public void setIsCurriculumAlreadyFillUp(final boolean isCurriculumAlreadyFillUp) {
        this.isCurriculumAlreadyFillUp = isCurriculumAlreadyFillUp;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.getUserId() != null) || (this.userId != null && !this.userId.equals(other.getUserId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Users.class + "[userId=" + userId + ", username=" + username + ", email= " + this.email + "]";
    }

    @Override
    public int compareTo(final Object o) {
        if (o instanceof Users) {
            Users user = (Users) o;
            return (this.username.compareTo(user.getUsername()));
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(final byte[] photo) {
        this.photo = photo;
    }

    public boolean isPhotoUploaded() {
        return photoUploaded;
    }

    public void setPhotoUploaded(final boolean photoUploaded) {
        this.photoUploaded = photoUploaded;
    }

    public Collection<Projects> getProjectsCollection() {
        return projectsCollection;
    }

    public void setProjectsCollection(final Collection<Projects> projectsCollection) {
        this.projectsCollection = projectsCollection;
    }

    public Collection<Projects> getProjectsOpenedCollection() {
        return projectsOpenedCollection;
    }

    public void setProjectsOpenedCollection(final Collection<Projects> projectsOpenedCollection) {
        this.projectsOpenedCollection = projectsOpenedCollection;
    }

    public List<Authority> getAuthorityCollection() {
        return authorityCollection;
    }

    public void setAuthorityCollection(final List<Authority> authorityCollection) {
        this.authorityCollection = authorityCollection;
    }

    public List<GroupMember> getGroupMemberCollection() {
        return groupMemberCollection;
    }

    public void setGroupMemberCollection(final List<GroupMember> groupMemberCollection) {
        this.groupMemberCollection = groupMemberCollection;
    }
}
