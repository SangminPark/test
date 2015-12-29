package best.prog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ValueObject.
 *
 * @author 박상민
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class User extends BaseEntity {
  
  @Column(name = "user_id", nullable = false, length = 50, unique = true)
  private String userId;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "passwd", nullable = false, length = 255)
  private String passwd;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "tel", length = 20)
  private String tel;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

}
