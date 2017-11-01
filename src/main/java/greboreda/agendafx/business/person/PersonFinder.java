package greboreda.agendafx.business.person;

import greboreda.agendafx.domain.Person;
import greboreda.agendafx.persistence.dao.PersonDAO;
import greboreda.agendafx.persistence.mappers.PersonMapper;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
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
		final Iterable<PersonVO> all = personDAO.findAll();
		final Stream<PersonVO> personVOStream = StreamSupport.stream(all.spliterator(), false);
		return personVOStream.map(PersonMapper::mapToBO)
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
}
