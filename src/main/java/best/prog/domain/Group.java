package best.prog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedEntityGraphs({
  @NamedEntityGraph(
      name = "Group.findWithUser", 
      attributeNodes = {
          @NamedAttributeNode(value = "groupUsers", subgraph = "subUser")
      },
      subgraphs = {
          @NamedSubgraph(name = "subUser", attributeNodes = {
              @NamedAttributeNode("user")
          })
      }
  )
})
@SuppressWarnings("serial")
@Entity
@Table(name = "GRP")
public class Group extends BaseEntity {
  
  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
  private Set<RoleGroup> roleGroups = new HashSet<RoleGroup>();
  
  @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
  private Set<GroupUser> groupUsers = new HashSet<GroupUser>();
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<RoleGroup> getRoleGroups() {
    return roleGroups;
  }

  public void setRoleGroups(Set<RoleGroup> roleGroups) {
    this.roleGroups = roleGroups;
  }

  public Set<GroupUser> getGroupUsers() {
    return groupUsers;
  }

  public void setGroupUsers(Set<GroupUser> groupUsers) {
    this.groupUsers = groupUsers;
  }

}
