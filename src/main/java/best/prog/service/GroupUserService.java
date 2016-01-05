package best.prog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Group;
import best.prog.domain.GroupUser;
import best.prog.domain.User;
import best.prog.repository.GroupUserRepository;


@Service
@Transactional
public class GroupUserService {

    @Autowired
    GroupUserRepository groupUserRepository;

    /**
     * 생성
     * @param groupUser
     * @return
     */
    public GroupUser create(GroupUser groupUser) {
      groupUserRepository.save(groupUser);
      return groupUser;
    }

    /**
     * 삭제
     * @param groupUser
     * @return
     */
    public GroupUser delete(GroupUser groupUser) {
      groupUserRepository.delete(groupUser);
      return groupUser;
    }

    /**
     * 정보 조회
     * @param groupUser
     * @return
     */
    public GroupUser findGroupUser(GroupUser groupUser) {
        return groupUserRepository.findOne(groupUser.getUid());
    }

    /**
     * 생성
     * @param group
     * @param user
     * @return
     */
    public GroupUser create(Group group, User user) {
      GroupUser groupUser = new GroupUser();
      groupUser.setGroup(group);
      groupUser.setUser(user);
      return create(groupUser);
    }

    /**
     * 여러사용자에게 한 그룹을 지정한다.
     * @param group
     * @param users
     */
    public void addUsersToGroup(Group group, List<User> users) {
      for(User user : users) {
        create(group, user);
      }
    }
    
    /**
     * 여러구룹을 한 사용자에게 지정한다.
     * @param user
     * @param groups
     */
    public void addGroupsToUser(User user, List<Group> groups) {
      for(Group group : groups) {
        create(group, user);
      }
    }

    /**
     * 삭제
     * @param group
     * @param user
     * @return
     */
    public GroupUser delete(Group group, User user) {     
      GroupUser groupUser = new GroupUser();
      groupUser.setGroup(group);
      groupUser.setUser(user);
      groupUser = findGroupUser(groupUser);
      return delete(groupUser);
    }
    
    /**
     * 한그룹에 연관된 사용자들을 모두 연결해제한다.
     * @param group
     */
    public void deleteAllByGroup(Group group) {   
      groupUserRepository.deleteAllByGroup(group);
    }
    
    /**
     * 한사용자에게 연관된 모든 그룹을 연결해제한다.
     * @param group
     */
    public void deleteAllByUser(User user) {
      groupUserRepository.deleteAllByUser(user);
    }
    
    /**
     * 정보 조회
     * @param groupUser
     * @return
     */
    public List<GroupUser> findAllByGroup(Group group) {
        return groupUserRepository.findAllByGroup(group);
    }

    public List<GroupUser> findAllByUser(User user) {
      return groupUserRepository.findAllByUser(user);
    }


}
