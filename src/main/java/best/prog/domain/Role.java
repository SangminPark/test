package best.prog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {
  
  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
  private List<RoleGroup> roleGroups = new ArrayList<RoleGroup>();
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RoleGroup> getRoleGroups() {
    return roleGroups;
  }

  public void setRoleGroups(List<RoleGroup> roleGroups) {
    this.roleGroups = roleGroups;
  }

}
