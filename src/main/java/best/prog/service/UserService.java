package best.prog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.User;
import best.prog.repository.UserRepository;


@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * 회원 생성
     */
    public User create(User user) {

        validateDuplicateUser(user); 
        userRepository.save(user);
        return user;
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findByUserId(user.getUserId());
        if (findUser != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    /**
     * 수정
     * @param user
     * @return
     */
    public User update(User user) {
    	User findUser = userRepository.findOne(user.getUid());
    	findUser.setName(user.getName());
    	findUser.setEmail(user.getEmail());
    	findUser.setTel(user.getTel());
    	if(user.getPasswd() != null || !"".equals(user.getPasswd().trim())) {
    		findUser.setPasswd(user.getPasswd());
    	}
    	userRepository.saveAndFlush(findUser);
    	return findUser;
    }

    /**
     * 삭제
     * @param user
     * @return
     */
    public void delete(User user) {    	
    	userRepository.delete(user);
    }

    /**
     * 정보 조회
     * @param user
     * @return
     */
    public User findUser(User user) {
        return userRepository.findOne(user.getUid());
    }

    /**
     * 전체 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

}
