package greboreda.agendafx.controllers.main;

import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.person.PersonSaver;
import greboreda.agendafx.business.phone.PhoneFinder;
import greboreda.agendafx.business.phone.PhoneSaver;
import greboreda.agendafx.business.phone.exceptions.SavePhoneError;
import greboreda.agendafx.business.phone.exceptions.SavePhoneException;
import greboreda.agendafx.controllers.components.persons.PersonInput;
import greboreda.agendafx.controllers.components.persons.PersonsOutput;
import greboreda.agendafx.controllers.components.persons.events.SavePersonEvent;
import greboreda.agendafx.controllers.components.phones.PhoneInput;
import greboreda.agendafx.controllers.components.phones.PhonesOutput;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.domain.person.PersonToSave;
import greboreda.agendafx.domain.phone.Phone;
import greboreda.agendafx.domain.phone.PhoneToSave;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainControllerTest {

	private PersonInput personInput = mock(PersonInput.class);
	private PersonsOutput personsOutput = mock(PersonsOutput.class);

	private PhonesOutput phonesOutput = mock(PhonesOutput.class);
	private PhoneInput phoneInput = mock(PhoneInput.class);

	private PersonFinder personFinder = mock(PersonFinder.class);
	private PersonSaver personSaver = mock(PersonSaver.class);
	private PhoneFinder phoneFinder = mock(PhoneFinder.class);
	private PhoneSaver phoneSaver = mock(PhoneSaver.class);

	private MainController mainController;

	private static final PersonToSave A_PERSON_TO_SAVE = PersonToSave.create()
			.withFirstName("John")
			.withLastName("Doe");

	private static final PhoneToSave A_PHONE_TO_SAVE = PhoneToSave.create()
			.forPersonWithId(1)
			.withPrefix("34")
			.withNumber("666666666");

	@Before
	public void setUp() {
		mainController = new MainController(personSaver, personFinder, phoneFinder, phoneSaver);
		mainController.personsOutput = personsOutput;
		mainController.personInput = personInput;
		mainController.phonesOutput = phonesOutput;
		mainController.phoneInput = phoneInput;
		mainController.errorShower = mock(MainControllerErrorShower.class);
	}

	@Test
	public void should_find_all_persons_when_initialize() {
		mainController.initialize(null, mock(ResourceBundle.class));
		verify(personFinder).findAllPersons();
	}

	@Test
	public void should_refresh_output_when_initialize() {
		final List<Person> allPersons = Collections.emptyList();
		when(personFinder.findAllPersons()).thenReturn(allPersons);

		mainController.initialize(null, mock(ResourceBundle.class));

		verify(personsOutput).refresh(allPersons);
	}

	@Test
	public void should_save_person_when_handle_event() throws Exception {

		final SavePersonEvent event = new SavePersonEvent(A_PERSON_TO_SAVE);

		mainController.onSavePerson(event);

		final ArgumentCaptor<PersonToSave> captor = ArgumentCaptor.forClass(PersonToSave.class);
		verify(personSaver).savePerson(captor.capture());
		assertThat(captor.getValue(), is(A_PERSON_TO_SAVE));
	}

	@Test
	public void should_refresh_output_when_saves_a_person() {

		final SavePersonEvent event = new SavePersonEvent(A_PERSON_TO_SAVE);
		final List<Person> allPersons = Collections.emptyList();
		when(personFinder.findAllPersons()).thenReturn(allPersons);

		mainController.onSavePerson(event);

		verify(personsOutput).refresh(allPersons);
	}

	@Test
	public void void_should_save_phone_when_handle_event() throws Exception {

		mainController.selectedPersonId = 1;

		final SavePhoneEvent event = SavePhoneEvent.create()
				.withPhonePrefix("123")
				.withPhoneNumber("456");

		mainController.onSavePhone(event);

		final ArgumentCaptor<PhoneToSave> captor = ArgumentCaptor.forClass(PhoneToSave.class);
		verify(phoneSaver).savePhone(captor.capture());
		final PhoneToSave personToSave = captor.getValue();
		assertThat(personToSave.personId, is(1));
		assertThat(personToSave.prefix, is("123"));
		assertThat(personToSave.number, is("456"));
	}

	@Test
	public void void_should_refresh_phone_output_when_saves_a_phone() throws Exception {

		final SavePhoneEvent event = SavePhoneEvent.create()
				.withPhonePrefix("123")
				.withPhoneNumber("456");
		final List<Phone> phones = Collections.emptyList();
		when(phoneFinder.findPhonesByPersonId(anyInt()))
				.thenReturn(phones);

		mainController.onSavePhone(event);

		verify(phonesOutput).refresh(phones);
	}

	@Test
	public void void_should_show_error_when_save_fails() throws Exception {

		final SavePhoneException savePhoneException = new SavePhoneException(SavePhoneError.PERSON_NOT_EXISTS, "");

		doThrow(savePhoneException).when(phoneSaver).savePhone(any(PhoneToSave.class));

		final SavePhoneEvent event = SavePhoneEvent.create()
				.withPhonePrefix("123")
				.withPhoneNumber("456");

		mainController.onSavePhone(event);

		verify(mainController.errorShower).showSavePhoneError(SavePhoneError.PERSON_NOT_EXISTS);
	}

}