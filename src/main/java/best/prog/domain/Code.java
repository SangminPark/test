package best.prog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedEntityGraphs({
  @NamedEntityGraph(name = "Code.findWithSubCode", attributeNodes = { @NamedAttributeNode("childCodes") })
})
@SuppressWarnings("serial")
@Entity
@Table(name = "COMMON_CODE")
public class Code extends BaseEntity {
  
  @Column(name = "CODE", nullable = false, length = 50, unique = true)
  private String code;

  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_UID", referencedColumnName = "UID")
  private Code parentCode;
  
  @OneToMany(mappedBy = "parentCode", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Code> childCodes = new HashSet<Code>();
  

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Code getParentCode() {
    return parentCode;
  }

  public void setParentCode(Code parentCode) {
    this.parentCode = parentCode;
  }

  public Set<Code> getChildCodes() {
    return childCodes;
  }

  public void setChildCodes(Set<Code> childCodes) {
    this.childCodes = childCodes;
  }

}
