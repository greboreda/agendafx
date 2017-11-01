package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.person.PersonSaver;
import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.phone.PhoneFinder;
import greboreda.agendafx.components.personinput.PersonInput;
import greboreda.agendafx.components.personinput.PersonToCreate;
import greboreda.agendafx.components.personinput.SavePersonEvent;
import greboreda.agendafx.components.personoutput.PersonsOutput;
import greboreda.agendafx.components.personoutput.SelectPersonEvent;
import greboreda.agendafx.components.phonesoutput.PhonesOutput;
import greboreda.agendafx.domain.Person;
import greboreda.agendafx.domain.Phone;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

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
		refreshPersonsOutput();
	}

	public void onSavePerson(SavePersonEvent savePersonEvent) {
		final PersonToCreate personToCreate = savePersonEvent.getPersonToCreate();
		final Person person = Person.create()
				.withId(null)
				.withFirstName(personToCreate.firstName)
				.withLastName(personToCreate.lastName)
				.build();
		logger.info("Lets save person: " + person);
		personSaver.savePerson(person);
		refreshPersonsOutput();
	}

	public void onSelectPerson(SelectPersonEvent selectPersonEvent) {
		refreshPhonesOutput(selectPersonEvent.getPersonId());
	}

	private void refreshPhonesOutput(Integer personId) {
		logger.info("Refreshing phones output!");
		final List<Phone> phones = phoneFinder.findPhonesByPersonId(personId);
		phonesOutput.refresh(phones);
	}

	private void refreshPersonsOutput() {
		logger.info("Refreshing persons output!");
		personsOutput.refresh(personFinder.findAllPersons());
		phonesOutput.clear();
	}

}
