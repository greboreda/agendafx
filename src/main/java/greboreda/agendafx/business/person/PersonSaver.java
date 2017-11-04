package greboreda.agendafx.business.person;


import greboreda.agendafx.business.person.exceptions.SavePersonError;
import greboreda.agendafx.business.person.exceptions.SavePersonException;
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

	public void savePerson(PersonToSave personToSave) throws SavePersonException {
		validatePersonData(personToSave);
		validatePersonNotExists(personToSave);
		final PersonVO personVO = PersonMapper.mapToVO(personToSave);
		personDAO.save(personVO);
	}

	private void validatePersonNotExists(PersonToSave personToSave) throws SavePersonException {
		final PersonVO person = personDAO.findPersonByCompleteName(personToSave.firstName, personToSave.lastName);
		if(person != null) {
			final String errorMessage = "Person with that complete name already exists " + personToSave;
			throw new SavePersonException(SavePersonError.PERSON_ALREADY_EXISTS, errorMessage);
		}
	}

	private void validatePersonData(PersonToSave person) {
		Validate.notNull(person, "person cannot be null");
		Validate.notBlank(person.firstName, "person first name must not be blank");
		Validate.notBlank(person.lastName, "person last name must not be blank");
	}

}
