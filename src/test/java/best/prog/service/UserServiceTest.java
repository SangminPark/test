package best.prog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.User;
import best.prog.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-appConfig.xml")
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {

        //Given
        User user = new User();
        user.setUserId("bestmenbal");
        user.setName("박상민");
        user.setPasswd("1234");
        user.setCreateUser(user);
        //When
        String saveId = userService.create(user);

        //Then
        assertEquals(user, userRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {

        //Given
        User user1 = new User();
        user1.setUserId("bestmenbal");
        user1.setName("박상민");
        user1.setPasswd("1234");
        user1.setCreateUser(user1);
        
        User user2 = new User();
        user2.setUserId("bestmenbal");
        user2.setName("박상민");
        user2.setPasswd("1234");
        user2.setCreateUser(user2);
        
        //When
        userService.create(user1);
        userService.create(user2); //예외가 발생해야 한다.

        //Then
        fail("예외가 발생해야 한다.");
    }


}