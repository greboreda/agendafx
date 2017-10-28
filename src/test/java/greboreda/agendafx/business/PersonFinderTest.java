package greboreda.agendafx.business;

import greboreda.agendafx.domain.Person;
import greboreda.agendafx.persistence.dao.PersonDAO;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonFinderTest {

	private PersonDAO personDAO;

	private PersonFinder personFinder;

	@Before
	public void setUp() {
		personDAO = mock(PersonDAO.class);
		personFinder = new PersonFinder(personDAO);
	}

	@Test
	public void should_return_all_persisted_persons_when_find_all_persons() {

		when(personDAO.findAll()).thenReturn(Arrays.asList(
				createPersonVOWithId(1),
				createPersonVOWithId(2)
		));

		final List<Person> allPersons = personFinder.findAllPersons();

		assertThat(allPersons.size(), is(2));
		assertThat(allPersons.stream()
				.map(Person::getId)
				.collect(toList()), containsInAnyOrder(1,2));
	}

	private PersonVO createPersonVOWithId(Integer id) {
		final PersonVO vo = new PersonVO();
		vo.setId(id);
		return vo;
	}
}