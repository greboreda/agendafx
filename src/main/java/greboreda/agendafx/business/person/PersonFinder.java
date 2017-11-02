package greboreda.agendafx.business.person;

import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.persistence.dao.PersonDAO;
import greboreda.agendafx.persistence.mappers.PersonMapper;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Named
public class PersonFinder {

	private final PersonDAO personDAO;

	@Inject
	public PersonFinder(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public List<Person> findAllPersons() {
		return personDAO.findAllPersonsSorted().stream()
				.map(PersonMapper::mapToBO)
				.collect(toList());
	}

	public List<Person> findPersonsByFreeSearch(String search) {
		if(StringUtils.isBlank(search)) {
			return findAllPersons();
		}
		final List<PersonVO> persons = personDAO.findPersonsByFirstNameOrLastNameLike(search);
		return persons.stream()
				.map(PersonMapper::mapToBO)
				.collect(toList());
	}

	public Optional<Person> findPersonById(Integer personId) {
		Validate.notNull(personId, "personId cannot be null");
		final PersonVO personVO = personDAO.findOne(personId);
		if(personVO == null) {
			return Optional.empty();
		}
		final Person person = PersonMapper.mapToBO(personVO);
		return Optional.of(person);
	}

	public Boolean existsPersonWithId(Integer personId) {
		Validate.notNull(personId, "personId cannot be null");
		return personDAO.exists(personId);
	}

}
