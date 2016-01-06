package best.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import best.prog.domain.Code;

/**
 * RoleRepository.
 * @author Sangmin Park
 *
 */
@Repository("CodeRepository")
public interface CodeRepository extends JpaRepository<Code, String> {

  Code findByCode(String code);

}
