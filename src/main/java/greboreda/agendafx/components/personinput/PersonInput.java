package greboreda.agendafx.components.personinput;

import greboreda.agendafx.components.ComponentInitializer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

	private ObjectProperty<EventHandler<SavePersonEvent>> onSavePerson = new SimpleObjectProperty<>();

	public PersonInput() {
		ComponentInitializer.init(this, PERSON_INPUT_FXML);
		saveButton.setOnMouseClicked(this::onSave);
	}

	private void onSave(Event event) {
		final PersonToCreate personToCreate = retrievePersonToCreate();
		final SavePersonEvent savePersonEvent = new SavePersonEvent(personToCreate);
		onSavePerson.get().handle(savePersonEvent);
	}

	private PersonToCreate retrievePersonToCreate() {
		final String firstName = firstNameInput.getText();
		final String lastName = lastNameInput.getText();
		return new PersonToCreate(firstName, lastName);
	}

	public final EventHandler<SavePersonEvent> getOnSavePerson() {
		return onSavePerson.get();
	}

	public final void setOnSavePerson(EventHandler<SavePersonEvent> savePersonEventHandler) {
		this.onSavePerson.set(savePersonEventHandler);
	}

}
