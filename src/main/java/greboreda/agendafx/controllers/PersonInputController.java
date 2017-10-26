package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.PersonCreator;
import greboreda.agendafx.domain.Person;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;


@FXMLController
public class PersonInputController {

	@Inject
	private PersonsOutputController personsOutputController;
	@Inject
	private PersonCreator personCreator;

	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;

	public void onSavePerson(Event event) {
		final Person person = Person.create()
				.withId(null)
				.withFirstName(firstNameInput.getText())
				.withLastName(lastNameInput.getText())
				.build();
		personCreator.createPerson(person);

		personsOutputController.refreshPersonsTable();
	}
}
