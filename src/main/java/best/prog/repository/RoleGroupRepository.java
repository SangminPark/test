package best.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import best.prog.domain.RoleGroup;

/**
 * UserRoleRepository.
 * @author Sangmin Park
 *
 */
@Repository("UserRoleRepository")
public interface RoleGroupRepository extends JpaRepository<RoleGroup, String> {

}
