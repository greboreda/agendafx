package greboreda.agendafx.persistence.dao;

import greboreda.agendafx.persistence.vo.PersonVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDAO extends CrudRepository<PersonVO, Integer>{

	@Query("select p from PersonVO as p order by p.firstName, p.lastName")
	List<PersonVO> findAllPersonsSorted();

	@Query(	" select p from PersonVO as p " +
			" where lower(p.firstName) like concat('%', lower(:pattern), '%') " +
			" or lower(p.lastName) like concat('%', lower(:pattern), '%') " +
			" order by p.firstName, p.lastName"
	)
	List<PersonVO> findPersonsByFirstNameOrLastNameLike(@Param("pattern") String pattern);

}
