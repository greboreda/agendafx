package greboreda.agendafx.business.person;


import greboreda.agendafx.domain.person.Person;
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

	public void savePerson(Person person) {
		validatePerson(person);
		final PersonVO personVO = PersonMapper.mapToVO(person);
		personDAO.save(personVO);
	}

	private void validatePerson(Person person) {
		Validate.notNull(person, "person cannot be null");
		if(person.getId() != null) {
			throw new IllegalArgumentException("person to save cannot have id");
		}
		Validate.notBlank(person.getFirstName(), "person first name must not be blank");
		Validate.notBlank(person.getLastName(), "person last name must not be blank");
	}

}
