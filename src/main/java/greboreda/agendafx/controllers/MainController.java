package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.person.PersonSaver;
import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.phone.PhoneFinder;
import greboreda.agendafx.components.persons.PersonInput;
import greboreda.agendafx.components.persons.dto.PersonToCreate;
import greboreda.agendafx.components.persons.events.SavePersonEvent;
import greboreda.agendafx.components.persons.PersonsOutput;
import greboreda.agendafx.components.persons.events.SearchPersonsEvent;
import greboreda.agendafx.components.persons.events.SelectPersonEvent;
import greboreda.agendafx.components.phones.PhonesOutput;
import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.domain.phone.Phone;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.joining;

@FXMLController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final PersonSaver personSaver;
	private final PersonFinder personFinder;
	private final PhoneFinder phoneFinder;

	@Inject
	public MainController(PersonSaver personSaver, PersonFinder personFinder, PhoneFinder phoneFinder) {
		this.personSaver = personSaver;
		this.personFinder = personFinder;
		this.phoneFinder = phoneFinder;
	}

	@FXML
	protected PersonInput personInput;
	@FXML
	protected PersonsOutput personsOutput;
	@FXML
	protected PhonesOutput phonesOutput;


	@FXML
	public void initialize() {
		logger.info("Initializing");
		refreshPersonsOutput(personFinder.findAllPersons());
	}

	public void onSavePerson(SavePersonEvent savePersonEvent) {
		final PersonToCreate personToCreate = savePersonEvent.getPersonToCreate();
		final Person person = Person.create()
				.withId(null)
				.withFirstName(personToCreate.firstName)
				.withLastName(personToCreate.lastName)
				.build();
		logger.debug("Lets save person: " + person);
		personSaver.savePerson(person);
		refreshPersonsOutput(personFinder.findAllPersons());
	}

	public void onSelectPerson(SelectPersonEvent selectPersonEvent) {
		refreshPhonesOutput(selectPersonEvent.getPersonId());
	}

	public void onSearchPersons(SearchPersonsEvent searchPersonsEvent) {
		final String search = searchPersonsEvent.getSearch();
		logger.debug("Lets search persons like " + search);
		final List<Person> persons = personFinder.findPersonsByFreeSearch(search);
		refreshPersonsOutput(persons);
	}

	private void refreshPhonesOutput(Integer personId) {
		final List<Phone> phones = phoneFinder.findPhonesByPersonId(personId);
		logger.debug(String.format("Refreshing phones for person with id %s", personId));
		phonesOutput.refresh(phones);
	}

	private void refreshPersonsOutput(List<Person> persons) {
		logger.debug("Refreshing persons output!");
		personsOutput.refresh(persons);
		phonesOutput.clear();
	}

}
