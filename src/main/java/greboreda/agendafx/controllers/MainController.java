package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.PersonCreator;
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

	@Inject
	private PersonCreator personCreator;
	@Inject
	private PersonFinder personFinder;

	@FXML
	private PersonInput personInput;
	@FXML
	private PersonsOutput personsOutput;

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
		personCreator.createPerson(person);
		refreshPersonsOutput();
	}

	private void refreshPersonsOutput() {
		logger.info("Refreshing persons output!");
		personsOutput.refresh(personFinder.findAllPersons());
	}

}
