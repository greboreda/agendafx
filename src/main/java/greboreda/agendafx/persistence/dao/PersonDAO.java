package greboreda.agendafx.persistence.dao;

import greboreda.agendafx.persistence.vo.PersonVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends CrudRepository<PersonVO, Integer>{
}
