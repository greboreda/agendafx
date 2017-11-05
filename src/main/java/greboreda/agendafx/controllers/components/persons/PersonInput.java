package greboreda.agendafx.controllers.components.persons;

import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.persons.events.SavePersonEvent;
import greboreda.agendafx.domain.person.PersonToSave;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


public class PersonInput extends FlowPane implements Initializable {

	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;
	@FXML
	private Button saveButton;

	private EventHandler<SavePersonEvent> onSavePersonHandler;
	private ResourceBundle resources;

	public PersonInput() {
		ViewLoader.load(this);
		saveButton.setOnMouseClicked(this::onSave);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resources = resources;
	}

	private void onSave(MouseEvent event) {
		final PersonToSave personToSave = retrievePersonToSave();
		final boolean validData = isNotBlank(personToSave.firstName) && isNotBlank(personToSave.lastName);
		if(!validData) {
			showError();
			return;
		}
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

	private void showError() {
		final Alert error = new Alert(AlertType.ERROR);
		error.setTitle(resources.getString("generic.dialog.info"));
		error.setHeaderText(resources.getString("error.alert.title"));
		error.setContentText(resources.getString("error.person.blank"));
		error.showAndWait();
	}

	public final EventHandler<SavePersonEvent> getOnSavePerson() {
		return onSavePersonHandler;
	}

	public final void setOnSavePerson(EventHandler<SavePersonEvent> savePersonEventHandler) {
		this.onSavePersonHandler = savePersonEventHandler;
	}
}
