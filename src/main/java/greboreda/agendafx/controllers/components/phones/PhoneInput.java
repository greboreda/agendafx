package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import greboreda.agendafx.domain.phone.PhoneToSave;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PhoneInput extends GridPane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PhoneInput.class);

	@FXML
	TextField phonePrefixInput;
	@FXML
	TextField phoneNumberInput;
	@FXML
	Button saveButton;

	private EventHandler<SavePhoneEvent> onSavePhoneHandler;
	private Integer personIdToSavePhone;

	public PhoneInput() {
		ViewLoader.load(this);
	}

	@FXML
	public void initialize() {
		saveButton.setDisable(true);
		saveButton.setOnMouseClicked(this::onSavePhone);
	}

	void onSavePhone(MouseEvent event) {
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
