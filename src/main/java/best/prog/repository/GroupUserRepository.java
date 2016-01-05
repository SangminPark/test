package best.prog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import best.prog.domain.Group;
import best.prog.domain.GroupUser;
import best.prog.domain.User;

/**
 * UserRoleRepository.
 * @author Sangmin Park
 *
 */
@Repository("GroupUserRepository")
public interface GroupUserRepository extends JpaRepository<GroupUser, String> {

  void deleteAllByGroup(Group group);

  void deleteAllByUser(User user);

  List<GroupUser> findAllByGroup(Group group);

  List<GroupUser> findAllByUser(User user);

}
