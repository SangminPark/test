package best.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Group;
import best.prog.domain.Role;
import best.prog.domain.RoleGroup;
import best.prog.repository.RoleGroupRepository;


@Service
@Transactional
public class RoleGroupService {

    @Autowired
    RoleGroupRepository roleGroupRepository;

    /**
     * 회원 생성
     */
    public void create(Role role, Group group) {
      RoleGroup roleGroup = new RoleGroup();
      roleGroup.setGroup(group);
      roleGroup.setRole(role);
        
      roleGroupRepository.save(roleGroup);
    }

}
