package best.prog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Role;
import best.prog.repository.RoleRepository;


@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    /**
     * 회원 생성
     */
    public Role create(Role role) {
        validateDuplicateRole(role);
        roleRepository.save(role);
        return role;
    }

    private void validateDuplicateRole(Role role) {
        Role findRole = roleRepository.findByName(role.getName());
        if (findRole != null) {
            throw new IllegalStateException("이미 존재하는 Role입니다.");
        }
    }
    
    /**
     * 수정
     * @param role
     * @return
     */
    public Role update(Role role) {
    	Role findRole = roleRepository.findOne(role.getUid());
    	findRole.setName(role.getName());
    	roleRepository.saveAndFlush(findRole);
    	return findRole;
    }

    /**
     * 삭제
     * @param role
     * @return
     */
    public void delete(Role role) {    	
    	roleRepository.delete(role);
    }

    /**
     * 정보 조회
     * @param role
     * @return
     */
    public Role findRole(Role role) {
        return roleRepository.findOne(role.getUid());
    }

    /**
     * 전체 조회
     */
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

}
