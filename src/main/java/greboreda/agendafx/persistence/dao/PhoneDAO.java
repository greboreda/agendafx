package greboreda.agendafx.persistence.dao;

import greboreda.agendafx.persistence.vo.PhoneVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneDAO extends CrudRepository<PhoneVO,Integer> {

	@Query(	" select phone from PhoneVO as phone" +
			" join fetch phone.owners as persons " +
			" where persons.id = :personId ")
	List<PhoneVO> findPhonesByPersonId(@Param("personId") Integer personId);


	@Query(	" select phone from PhoneVO as phone " +
			" where phone.prefix = :phonePrefix " +
			" and phone.number = :phoneNumber ")
	PhoneVO findPhoneByPrefixAndNumber(
			@Param("phonePrefix") String phonePrefix,
			@Param("phoneNumber") String phoneNumber);
}
