package best.prog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Group;
import best.prog.repository.GroupRepository;


@Service
@Transactional
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    /**
     * 회원 생성
     */
    public Group create(Group group) {
        validateDuplicateGroup(group);
        groupRepository.save(group);
        return group;
    }

    private void validateDuplicateGroup(Group group) {
        Group findGroup = groupRepository.findByName(group.getName());
        if (findGroup != null) {
            throw new IllegalStateException("이미 존재하는 Group입니다.");
        }
    }
    
    /**
     * 수정
     * @param group
     * @return
     */
    public Group update(Group group) {
    	Group findGroup = groupRepository.findOne(group.getUid());
    	findGroup.setName(group.getName());
    	groupRepository.saveAndFlush(findGroup);
    	return findGroup;
    }

    /**
     * 삭제
     * @param group
     * @return
     */
    public void delete(Group group) {    	
    	groupRepository.delete(group);
    }

    /**
     * 정보 조회
     * @param group
     * @return
     */
    public Group findGroup(Group group) {
        return groupRepository.findOne(group.getUid());
    }

    /**
     * 전체 조회
     */
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

}
