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
    public void 회원_생성() throws Exception {

        User user = new User();
        user.setUserId("bestmenbal");
        user.setName("박상민");
        user.setPasswd("1234");


        User createdUser = userService.create(user);


        assertEquals(user, createdUser);
    }

    @Test
    public void 회원_수정() throws Exception {

        User user = new User();
        user.setUserId("bestmenbal");
        user.setName("박상민");
        user.setPasswd("1234");
        User createdUser = userService.create(user);
        createdUser.setEmail("bestmenbal22@gmail.com");


        User updatedUser = userService.update(createdUser);


        assertEquals(createdUser, updatedUser);
    }

    @Test(expected = IllegalStateException.class)
    public void 회원_생성_아이디중복_예외() throws Exception {

        User user1 = new User();
        user1.setUserId("bestmenbal");
        user1.setName("박상민");
        user1.setPasswd("1234");
        User user2 = new User();
        user2.setUserId("bestmenbal");
        user2.setName("박상민");
        user2.setPasswd("1234");


        userService.create(user1);
        userService.create(user2);


        fail("예외가 발생해야 한다.");
    }


}