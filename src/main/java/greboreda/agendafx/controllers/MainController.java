package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.PersonSaver;
import greboreda.agendafx.business.PersonFinder;
import greboreda.agendafx.components.personinput.PersonInput;
import greboreda.agendafx.components.personinput.PersonToCreate;
import greboreda.agendafx.components.personinput.SavePersonEvent;
import greboreda.agendafx.components.personoutput.PersonsOutput;
import greboreda.agendafx.domain.Person;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@FXMLController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final PersonSaver personSaver;
	private final PersonFinder personFinder;

	@Inject
	public MainController(PersonSaver personSaver, PersonFinder personFinder) {
		this.personSaver = personSaver;
		this.personFinder = personFinder;
	}

	@FXML
	protected PersonInput personInput;
	@FXML
	protected PersonsOutput personsOutput;


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

	private void refreshPersonsOutput() {
		logger.info("Refreshing persons output!");
		personsOutput.refresh(personFinder.findAllPersons());
	}

}
