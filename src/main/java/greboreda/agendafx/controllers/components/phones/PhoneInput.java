package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
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

	public PhoneInput() {
		ViewLoader.load(this);
	}

	@FXML
	public void initialize() {
		disableSaving();
		saveButton.setOnMouseClicked(this::onSavePhone);
	}

	void onSavePhone(MouseEvent event) {
		final SavePhoneEvent savePhoneEvent = SavePhoneEvent.create()
				.withPhonePrefix(phonePrefixInput.getText())
				.withPhoneNumber(phoneNumberInput.getText());
		this.fireEvent(savePhoneEvent);
	}

	public void enableSaving() {
		this.saveButton.setDisable(false);
	}

	public void disableSaving() {
		this.saveButton.setDisable(true);
	}

}
