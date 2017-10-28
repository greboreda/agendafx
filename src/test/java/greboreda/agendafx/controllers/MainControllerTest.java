package greboreda.agendafx.controllers;

import greboreda.agendafx.business.PersonFinder;
import greboreda.agendafx.business.PersonSaver;
import greboreda.agendafx.components.personinput.PersonInput;
import greboreda.agendafx.components.personinput.PersonToCreate;
import greboreda.agendafx.components.personinput.SavePersonEvent;
import greboreda.agendafx.components.personoutput.PersonsOutput;
import greboreda.agendafx.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainControllerTest {

	@Mock
	private PersonInput personInput;
	@Mock
	private PersonsOutput personsOutput;
	@Mock
	private PersonFinder personFinder;
	@Mock
	private PersonSaver personSaver;

	private MainController mainController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mainController = new MainController(personSaver, personFinder);
		mainController.personsOutput = personsOutput;
		mainController.personInput = personInput;
	}

	@Test
	public void should_find_all_persons_when_initialize() {
		mainController.initialize();
		verify(personFinder).findAllPersons();
	}

	@Test
	public void should_refresh_output_when_initialize() {
		final List<Person> allPersons = Collections.emptyList();
		when(personFinder.findAllPersons()).thenReturn(allPersons);

		mainController.initialize();

		verify(personsOutput).refresh(allPersons);
	}

	@Test
	public void should_save_person_when_handle_event() {

		final SavePersonEvent event = new SavePersonEvent(new PersonToCreate("John", "Doe"));

		mainController.onSavePerson(event);

		final ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
		verify(personSaver).savePerson(captor.capture());
		final Person savedPerson = captor.getValue();
		assertAll(
				() -> assertThat(savedPerson.getId(), is(nullValue())),
				() -> assertThat(savedPerson.getFirstName(), is("John")),
				() -> assertThat(savedPerson.getLastName(), is("Doe"))
		);
	}

	@Test
	public void should_refresh_output_when_saving_person() {

		final SavePersonEvent event = new SavePersonEvent(new PersonToCreate("John", "Doe"));
		final List<Person> allPersons = Collections.emptyList();
		when(personFinder.findAllPersons()).thenReturn(allPersons);

		mainController.onSavePerson(event);

		verify(personsOutput).refresh(allPersons);
	}

}