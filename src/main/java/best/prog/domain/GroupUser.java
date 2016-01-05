package best.prog.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "GROUP_USER")
public class GroupUser extends BaseEntity {
  
  @ManyToOne
  @JoinColumn(name = "GROUP_UID", referencedColumnName = "UID")  
  private Group group;
  
  @ManyToOne
  @JoinColumn(name = "USER_UID", referencedColumnName = "UID")
  private User user;


  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

 
}
