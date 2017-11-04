package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.controllers.components.ViewLoader;
import greboreda.agendafx.domain.phone.PhoneToSave;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PhoneInput extends FlowPane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PhoneInput.class);

	private static final String PHONE_INPUT_FXML = "phoneinput.fxml";

	@FXML
	private TextField phonePrefixInput;
	@FXML
	private TextField phoneNumberInput;
	@FXML
	private Button saveButton;

	private EventHandler<SavePhoneEvent> onSavePhoneHandler;
	private Integer personIdToSavePhone;

	public PhoneInput() {
		ViewLoader.load(this, PHONE_INPUT_FXML);
		saveButton.setDisable(true);
		saveButton.setOnMouseClicked(this::onSavePhone);
	}

	private void onSavePhone(Event event) {
		final PhoneToSave phoneToSave = retrievePhoneToSave();
		final SavePhoneEvent savePhoneEvent = new SavePhoneEvent(phoneToSave);
		onSavePhoneHandler.handle(savePhoneEvent);
	}

	private PhoneToSave retrievePhoneToSave() {
		final String phonePrefix = phonePrefixInput.getText();
		final String phoneNumber = phoneNumberInput.getText();
		return PhoneToSave.create()
				.forPersonWithId(personIdToSavePhone)
				.withPrefix(phonePrefix)
				.withNumber(phoneNumber);
	}

	public void setOnSavePhone(EventHandler<SavePhoneEvent> savePhoneEventHandler) {
		this.onSavePhoneHandler = savePhoneEventHandler;
	}

	public EventHandler<SavePhoneEvent> getOnSavePhone() {
		return this.onSavePhoneHandler;
	}

	public Integer getPersonIdToSavePhone() {
		return personIdToSavePhone;
	}

	public void setPersonIdToSavePhone(Integer personIdToSavePhone) {
		this.personIdToSavePhone = personIdToSavePhone;
		saveButton.setDisable(personIdToSavePhone == null);
	}
}
