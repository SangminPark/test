package best.prog.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

  @EntityGraph(value = "Code.findWithSubCode" , type = EntityGraphType.FETCH)
  @Query("select c from Code c where c.uid = :uid")
  Code findOneWithSubCode(@Param("uid") String uid);

}
