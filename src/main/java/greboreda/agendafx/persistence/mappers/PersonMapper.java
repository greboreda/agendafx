package greboreda.agendafx.persistence.mappers;

import greboreda.agendafx.domain.Person;
import greboreda.agendafx.persistence.vo.PersonVO;

public class PersonMapper {

	public static Person mapToBO(PersonVO personVO) {
		if(personVO == null) {
			return null;
		}
		return Person.create()
				.withId(personVO.getId())
				.withFirstName(personVO.getFirstName())
				.withLastName(personVO.getLastName())
				.build();
	}

	public static PersonVO mapToVO(Person person) {
		if(person == null) {
			return null;
		}
		final PersonVO personVO = new PersonVO();
		personVO.setId(person.getId());
		personVO.setFirstName(person.getFirstName());
		personVO.setLastName(person.getLastName());
		return personVO;
	}
}
