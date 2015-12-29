package best.prog.repository;

import java.util.List;

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

  List<User> findByUserId(String userId);

}
