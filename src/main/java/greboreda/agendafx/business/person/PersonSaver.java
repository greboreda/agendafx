package greboreda.agendafx.business.person;


import greboreda.agendafx.domain.person.PersonToSave;
import greboreda.agendafx.persistence.dao.PersonDAO;
import greboreda.agendafx.persistence.mappers.PersonMapper;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PersonSaver {

	private final PersonDAO personDAO;

	@Inject
	public PersonSaver(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public void savePerson(PersonToSave personToSave) {
		validatePerson(personToSave);
		//TODO validate person not exists with same complete name
		final PersonVO personVO = PersonMapper.mapToVO(personToSave);
		personDAO.save(personVO);
	}

	private void validatePerson(PersonToSave person) {
		Validate.notNull(person, "person cannot be null");
		Validate.notBlank(person.firstName, "person first name must not be blank");
		Validate.notBlank(person.lastName, "person last name must not be blank");
	}

}
