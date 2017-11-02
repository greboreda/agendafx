package greboreda.agendafx.persistence.mappers;

import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.domain.person.PersonToSave;
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

	public static PersonVO mapToVO(PersonToSave personToSave) {
		if(personToSave == null) {
			return null;
		}
		final PersonVO personVO = new PersonVO();
		personVO.setFirstName(personToSave.firstName);
		personVO.setLastName(personToSave.lastName);
		return personVO;
	}

}
