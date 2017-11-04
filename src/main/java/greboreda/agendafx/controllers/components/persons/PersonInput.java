package greboreda.agendafx.controllers.components.persons;

import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.domain.person.PersonToSave;
import greboreda.agendafx.controllers.components.persons.events.SavePersonEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;


public class PersonInput extends FlowPane {

	private static final String PERSON_INPUT_FXML = "personinput.fxml";

	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;
	@FXML
	private Button saveButton;

	private EventHandler<SavePersonEvent> onSavePersonHandler;

	public PersonInput() {
		ViewLoader.load(this, PERSON_INPUT_FXML);
		saveButton.setOnMouseClicked(this::onSave);
	}

	private void onSave(Event event) {
		final PersonToSave personToSave = retrievePersonToSave();
		final SavePersonEvent savePersonEvent = new SavePersonEvent(personToSave);
		onSavePersonHandler.handle(savePersonEvent);
	}

	private PersonToSave retrievePersonToSave() {
		final String firstName = firstNameInput.getText();
		final String lastName = lastNameInput.getText();
		return PersonToSave.create()
				.withFirstName(firstName)
				.withLastName(lastName);
	}

	public final EventHandler<SavePersonEvent> getOnSavePerson() {
		return onSavePersonHandler;
	}

	public final void setOnSavePerson(EventHandler<SavePersonEvent> savePersonEventHandler) {
		this.onSavePersonHandler = savePersonEventHandler;
	}

}
