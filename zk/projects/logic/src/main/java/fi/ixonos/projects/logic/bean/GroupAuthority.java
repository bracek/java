package fi.ixonos.projects.logic.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author katrami
 * @date Nov 09, 2010
 */
@Entity
@Table(name = "group_authority",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"authority", "group_id"})})
@NamedQueries({
    @NamedQuery(name = "GroupAuthority.findAll", query = "SELECT g FROM GroupAuthority g"),
    @NamedQuery(name = "GroupAuthority.findByGroupAuthorityId", query = "SELECT g FROM GroupAuthority g WHERE g.groupAuthorityId = :groupAuthorityId"),
    @NamedQuery(name = "GroupAuthority.findByAuthority", query = "SELECT g FROM GroupAuthority g WHERE g.authority = :authority")})
public class GroupAuthority implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_authority_id", nullable = false)
    private Integer groupAuthorityId;
    @JoinColumn(name = "authority", referencedColumnName = "code_table_id", nullable = false)
    @ManyToOne(optional = false)
    private CodeTable authority;
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
    @ManyToOne(optional = false)
    private Groups group;

    public GroupAuthority() {
    }

    public GroupAuthority(final Integer groupAuthorityId) {
        this.groupAuthorityId = groupAuthorityId;
    }

    public GroupAuthority(final Integer groupAuthorityId,
final  CodeTable authority) {
        this.groupAuthorityId = groupAuthorityId;
        this.authority = authority;
    }

    public Integer getGroupAuthorityId() {
        return groupAuthorityId;
    }

    public void setGroupAuthorityId(final Integer groupAuthorityId) {
        this.groupAuthorityId = groupAuthorityId;
    }

    public CodeTable getAuthority() {
        return authority;
    }

    public void setAuthority(final CodeTable authority) {
        this.authority = authority;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(final Groups group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupAuthorityId != null ? groupAuthorityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupAuthority)) {
            return false;
        }
        GroupAuthority other = (GroupAuthority) object;
        if ((this.groupAuthorityId == null && other.groupAuthorityId != null) || (this.groupAuthorityId != null && !this.groupAuthorityId.equals(other.groupAuthorityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ixonos.skillnet.bean.GroupAuthority[groupAuthorityId=" + groupAuthorityId + ", name=" + authority + "]";
    }
}
