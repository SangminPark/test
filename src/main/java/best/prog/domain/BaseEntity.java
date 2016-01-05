package best.prog.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "UID", length = 36)
  private String uid;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATE_DATE")
  private Date createDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATE_DATE")
  private Date updateDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "REMOVE_DATE")
  private Date removeDate;

  @OneToOne
  @JoinColumn(name = "CREATE_USER_UID", referencedColumnName = "UID")
  private User createUser;

  @OneToOne
  @JoinColumn(name = "UPDATE_USER_UID", referencedColumnName = "UID")
  private User updateUser;

  @OneToOne
  @JoinColumn(name = "REMOVE_USER_UID", referencedColumnName = "UID")
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
  
  @PostPersist
  public void postPersist() {

  }
  
  @PostUpdate
  public void postUpdate() {

  }
  
  @PostRemove
  public void postRemove() {

  }
  
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
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
