package best.prog.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

  @EntityGraph(value = "Group.findWithUser" , type = EntityGraphType.FETCH)
  @Query("select g from Group g where g.uid = :uid")
  Group findOneByWithUser(@Param("uid") String uid);
  
}
