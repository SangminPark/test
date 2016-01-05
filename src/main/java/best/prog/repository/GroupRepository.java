package best.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import best.prog.domain.Group;

/**
 * RoleRepository.
 * @author Sangmin Park
 *
 */
@Repository("GroupRepository")
public interface GroupRepository extends JpaRepository<Group, String> {

  Group findByName(String name);

}
