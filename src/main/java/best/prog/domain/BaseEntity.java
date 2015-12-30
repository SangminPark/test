package best.prog.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * ValueObject.
 *
 * @author 박상민
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", length = 36)
  private String id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_date")
  private Date updateDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "remove_date")
  private Date removeDate;

  @OneToOne
  @JoinColumn(name = "create_user_id", referencedColumnName = "id")
  private User createUser;

  @OneToOne
  @JoinColumn(name = "update_user_id", referencedColumnName = "id")
  private User updateUser;

  @OneToOne
  @JoinColumn(name = "remove_user_id", referencedColumnName = "id")
  private User removeUser;
   
  @PrePersist
  public void prePersist() {
    this.createDate = new Date();
  }
  
  @PreUpdate
  public void preUpdate() {
    this.updateDate = new Date();
  }
  
  @PreRemove
  public void preRemove() {
    this.removeDate = new Date();
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  
  public void setUpdateDate(Date updateDate) {
    this.updateDate = new Date();
  }

  public Date getRemoveDate() {
    return removeDate;
  }

  public void setRemoveDate(Date removeDate) {
    this.removeDate = removeDate;
  }

  public User getCreateUser() {
    return createUser;
  }

  public void setCreateUser(User createUser) {
    this.createUser = createUser;
  }

  public User getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(User updateUser) {
    this.updateUser = updateUser;
  }

  public User getRemoveUser() {
    return removeUser;
  }

  public void setRemoveUser(User removeUser) {
    this.removeUser = removeUser;
  }
}
