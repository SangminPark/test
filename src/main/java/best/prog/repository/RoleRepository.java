package best.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import best.prog.domain.Role;

/**
 * RoleRepository.
 * @author Sangmin Park
 *
 */
@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Role, String> {

  Role findByName(String name);

}
