package greboreda.agendafx.business;

import greboreda.agendafx.business.person.PersonSaver;
import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.domain.person.PersonToSave;
import greboreda.agendafx.persistence.dao.PersonDAO;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
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
	public void when_saving_person_first_name_should_not_be_blank() {
		final PersonToSave personToSave = PersonToSave.create()
				.withFirstName(" ")
				.withLastName("Doe");
		assertThrows(IllegalArgumentException.class, () -> personSaver.savePerson(personToSave));
	}

	@Test
	public void when_saving_person_last_name_should_not_be_blank() {
		final PersonToSave personToSave = PersonToSave.create()
				.withFirstName("John")
				.withLastName("  ");
		assertThrows(IllegalArgumentException.class, () -> personSaver.savePerson(personToSave));
	}

	@Test
	public void should_persist_person_with_same_data() {
		final PersonToSave personToSave = PersonToSave.create()
				.withFirstName("John")
				.withLastName("Doe");
		personSaver.savePerson(personToSave);
		final ArgumentCaptor<PersonVO> captor = ArgumentCaptor.forClass(PersonVO.class);
		verify(personDAO).save(captor.capture());
		final PersonVO personVO = captor.getValue();
		assertAll(
				() -> assertThat(personVO.getId(), is(nullValue())),
				() -> assertThat(personVO.getFirstName(), is("John")),
				() -> assertThat(personVO.getLastName(), is("Doe"))
		);
	}

}