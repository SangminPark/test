package best.prog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Group;
import best.prog.domain.GroupUser;
import best.prog.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-appConfig.xml")
@Transactional
public class GroupServiceTest {

    @Autowired GroupService groupService;
    @Autowired GroupUserService groupUserService;

    @Autowired UserService userService;
    
    int index = 0;
    public User createUser() throws Exception {
      User user = new User();
      user.setUserId("bestmenbal_" + index);
      user.setName("박상민_" + index);
      user.setPasswd("1234_" + index);
      user = userService.create(user);
      index++;
      return user;
    }

    public Group createGroup() throws Exception {
      Group group = new Group();
      group.setName("userGroup_" + index);
      group = groupService.create(group);
      index++;
      return group;
    }


    @Test
    public void 그룹_생성() throws Exception {

      Group group = createGroup();

      Group findGroup = groupService.findGroup(group);
      assertNotNull(findGroup);
    }

    @Test
    public void 그룹_수정() throws Exception {

      Group group = createGroup();
      group.setName("userGroup1");
        
        
      Group updatedGroup = groupService.update(group);
        
        
      assertEquals(group, updatedGroup);
    }

    @Test(expected = IllegalStateException.class)
    public void 그룹생성_중복명칭_예외() throws Exception {
        Group group1 = new Group();
        group1.setName("g1");
        Group group2 = new Group();
        group2.setName("g1");
        

        groupService.create(group1);
        groupService.create(group2);


        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 사용자_그룹_연결지정() throws Exception {

      Group group = createGroup();
      User user = createUser();
        
      GroupUser groupUser = groupUserService.create(group, user);
        
        
      assertNotNull(groupUser);
    }

    @Test
    public void 여러사용자를_한그룹에_연결() throws Exception {

      Group group = createGroup();
      
      List<User> users = new ArrayList<User>();
      users.add(createUser());
      users.add(createUser());
      users.add(createUser());
              
      groupUserService.addUsersToGroup(group, users);
      
      List<GroupUser> groupUsers = groupUserService.findAllByGroup(group);
      
      assertEquals(users.size(), groupUsers.size());
    }

    @Test
    public void 여러그룹을_한사용자에게_연결() throws Exception {

      User user = createUser();
      
      List<Group> groups = new ArrayList<Group>();
      groups.add(createGroup());
      groups.add(createGroup());
      groups.add(createGroup());
              
      groupUserService.addGroupsToUser(user, groups);
      
      List<GroupUser> groupUsers = groupUserService.findAllByUser(user);
      
      assertEquals(groups.size(), groupUsers.size());
    }

    @Test
    public void 그룹에_연결된_모든_사용자_삭제() throws Exception {

      Group group = createGroup();
      
      List<User> users = new ArrayList<User>();
      users.add(createUser());
      users.add(createUser());
      users.add(createUser());
              
      groupUserService.addUsersToGroup(group, users);
      List<GroupUser> groupUsers = groupUserService.findAllByGroup(group);
      assertEquals(users.size(), groupUsers.size());
      
      groupUserService.deleteAllByGroup(group);
      groupUsers = groupUserService.findAllByGroup(group);
      assertEquals(0, groupUsers.size());
      
    }

    @Test
    public void 사용자에_연결된_모든_그룹_삭제() throws Exception {

      User user = createUser();
      
      List<Group> groups = new ArrayList<Group>();
      groups.add(createGroup());
      groups.add(createGroup());
      groups.add(createGroup());
              
      groupUserService.addGroupsToUser(user, groups);      
      List<GroupUser> groupUsers = groupUserService.findAllByUser(user);
      assertEquals(groups.size(), groupUsers.size());
      
      groupUserService.deleteAllByUser(user);
      groupUsers = groupUserService.findAllByUser(user);
      assertEquals(0, groupUsers.size());
      
    }

}