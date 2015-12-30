package best.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import best.prog.domain.User;

/**
 * UserRepository.
 * @author Sangmin Park
 *
 */
@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, String> {

  User findByUserId(String userId);

}
