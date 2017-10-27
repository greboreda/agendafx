package greboreda.agendafx.business;

import greboreda.agendafx.domain.Person;
import greboreda.agendafx.persistence.dao.PersonDAO;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersonSaverTest {

	private PersonDAO personDAO;

	private PersonSaver personSaver;

	@Before
	public void setUp() {
		personDAO = mock(PersonDAO.class);
		personSaver = new PersonSaver(personDAO);
	}

	@Test
	public void when_saving_person_should_not_be_null() {
		assertThrows(NullPointerException.class, () -> personSaver.savePerson(null));
	}

	@Test
	public void when_saving_person_id_should_not_be_null() {
		final Person person = Person.create()
				.withId(123)
				.withFirstName("John")
				.withLastName("Doe")
				.build();
		assertThrows(IllegalArgumentException.class, () -> personSaver.savePerson(person));
	}

	@Test
	public void when_saving_person_first_name_should_not_be_blank() {
		final Person person = Person.create()
				.withId(null)
				.withFirstName(" ")
				.withLastName("Doe")
				.build();
		assertThrows(IllegalArgumentException.class, () -> personSaver.savePerson(person));
	}

	@Test
	public void when_saving_person_last_name_should_not_be_blank() {
		final Person person = Person.create()
				.withId(null)
				.withFirstName("John")
				.withLastName("  ")
				.build();
		assertThrows(IllegalArgumentException.class, () -> personSaver.savePerson(person));
	}

	@Test
	public void should_persist_person_with_same_data() {
		personSaver.savePerson(Person.create()
				.withId(null)
				.withFirstName("John")
				.withLastName("Doe")
				.build());
		final ArgumentCaptor<PersonVO> captor = ArgumentCaptor.forClass(PersonVO.class);
		verify(personDAO).save(captor.capture());
		final PersonVO personVO = captor.getValue();
		assertThat(personVO.getId(), is(nullValue()));
		assertThat(personVO.getFirstName(), is("John"));
		assertThat(personVO.getLastName(), is("Doe"));
	}

}