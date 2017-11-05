package greboreda.agendafx.controllers.main;

import greboreda.agendafx.business.person.exceptions.SavePersonError;
import greboreda.agendafx.business.phone.exceptions.SavePhoneError;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

class MainControllerErrorShower {

	private final ResourceBundle bundle;

	private static final Map<SavePersonError, String> savePersonErrorKeys = new HashMap<>();
	private static final Map<SavePhoneError, String> savePhoneErrorKeys = new HashMap<>();
	static {
		savePersonErrorKeys.put(SavePersonError.PERSON_ALREADY_EXISTS, "error.person.already.exist");

		savePhoneErrorKeys.put(SavePhoneError.PHONE_ALREADY_OWNED_FOR_GIVEN_PERSON, "error.phone.already.owned.by.that.person");
		savePhoneErrorKeys.put(SavePhoneError.PHONE_ALREADY_OWNED_FOR_MORE_PERSONS, "error.phone.already.owned.by.another.person");
	}
	private static final String DEFAULT_ERROR_KEY = "error.unexpected";


	MainControllerErrorShower(ResourceBundle resourceBundle) {
		Validate.notNull(resourceBundle, "resourceBundle cannot be null");
		this.bundle = resourceBundle;
	}

	void showSavePersonError(SavePersonError savePersonError) {
		final String key = savePersonErrorKeys.getOrDefault(savePersonError, DEFAULT_ERROR_KEY);
		showAlert(key);
	}
	void showSavePhoneError(SavePhoneError savePhoneError) {
		final String key = savePhoneErrorKeys.getOrDefault(savePhoneError, DEFAULT_ERROR_KEY);
		showAlert(key);
	}

	private void showAlert(String messageKey) {
		final String message = bundle.getString(messageKey);
		final ButtonType ok = new ButtonType(bundle.getString("error.alert.ok"), ButtonData.OK_DONE);
		final Alert alert = new Alert(AlertType.ERROR, message, ok);
		alert.setTitle(bundle.getString("error.alert.title"));
		alert.showAndWait();
	}

}
