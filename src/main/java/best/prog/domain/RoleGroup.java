package best.prog.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ROLE_GROUP")
public class RoleGroup extends BaseEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ROLE_UID", referencedColumnName = "UID")
  private Role role;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "GROUP_UID", referencedColumnName = "UID")  
  private Group group;
  

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
