package greboreda.agendafx.business;


import greboreda.agendafx.domain.Person;
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
		Validate.notNull(person, "person cannot be null");
		final PersonVO personVO = PersonMapper.mapToVO(person);
		personDAO.save(personVO);
	}

}
