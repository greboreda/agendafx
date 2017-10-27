package greboreda.agendafx.components.personinput;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.IOException;


public class PersonInput extends FlowPane {

	private static final String PERSON_INPUT_FXML = "/greboreda/agendafx/components/personinput.fxml";

	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;
	@FXML
	private Button saveButton;

	protected ObjectProperty<EventHandler<SavePersonEvent>> onSavePerson = new SimpleObjectProperty<>();

	public PersonInput() {
		init();
		saveButton.setOnMouseClicked(event -> onSave(event));
	}

	private void onSave(Event event) {
		final PersonToCreate personToCreate = retrievePersonToCreate();
		final SavePersonEvent savePersonEvent = new SavePersonEvent(personToCreate);
		onSavePerson.get().handle(savePersonEvent);
	}

	private PersonToCreate retrievePersonToCreate() {
		return new PersonToCreate(firstNameInput.getText(), lastNameInput.getText());
	}

	private void init() {
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PERSON_INPUT_FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public final void setOnSavePerson(EventHandler<SavePersonEvent> actionEvent) {
		this.onSavePerson.set(actionEvent);
	}

	public final ObjectProperty<EventHandler<SavePersonEvent>> onSavePersonProperty() {
		return onSavePerson;
	}

	public final EventHandler<SavePersonEvent> getOnSavePerson() {
		return onSavePerson.get();
	}

}
