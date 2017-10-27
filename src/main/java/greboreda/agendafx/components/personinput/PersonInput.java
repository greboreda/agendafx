package greboreda.agendafx.components.personinput;

import greboreda.agendafx.components.CustomComponent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;


public class PersonInput extends FlowPane implements CustomComponent {

	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;
	@FXML
	private Button saveButton;

	protected ObjectProperty<EventHandler<SavePersonEvent>> onSavePerson = new SimpleObjectProperty<>();

	public PersonInput() {
		init();
		saveButton.setOnMouseClicked(this::onSave);
	}

	private void onSave(Event event) {
		final PersonToCreate personToCreate = retrievePersonToCreate();
		final SavePersonEvent savePersonEvent = new SavePersonEvent(personToCreate);
		onSavePerson.get().handle(savePersonEvent);
	}

	private PersonToCreate retrievePersonToCreate() {
		return new PersonToCreate(firstNameInput.getText(), lastNameInput.getText());
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

	@Override
	public String getFxmlPath() {
		return "/greboreda/agendafx/components/personinput.fxml";
	}
}
