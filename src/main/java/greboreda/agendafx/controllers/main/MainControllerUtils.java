package greboreda.agendafx.controllers.main;

import greboreda.agendafx.business.person.exceptions.SavePersonError;
import greboreda.agendafx.business.phone.exceptions.SavePhoneError;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

class MainControllerUtils {

	private static final Map<SavePersonError, String> savePersonErrorKeys = new HashMap<>();
	static {
		savePersonErrorKeys.put(SavePersonError.PERSON_ALREADY_EXISTS, "error.person.already.exist");
	}
	private static final String DEFAULT_ERROR_KEY = "error.unexpected";

	static void manageSavePersonError(SavePersonError savePersonError, ResourceBundle bundle) {
		final String key = savePersonErrorKeys.getOrDefault(savePersonError, DEFAULT_ERROR_KEY);
		showAlert(key, bundle);
	}

	private static final Map<SavePhoneError, String> savePhoneErrorKeys = new HashMap<>();
	static{
		savePhoneErrorKeys.put(SavePhoneError.PHONE_ALREADY_EXISTS_FOR_THAT_PERSON, "error.phone.already.owned.by.that.person");
		savePhoneErrorKeys.put(SavePhoneError.PHONE_ALREAY_EXISTS_FOR_ANOTHER_PERSON, "error.phone.already.owned.by.another.person");
	}
	static void manageSavePhoneError(SavePhoneError savePhoneError, ResourceBundle bundle) {
		final String key = savePhoneErrorKeys.getOrDefault(savePhoneError, DEFAULT_ERROR_KEY);
		showAlert(key, bundle);
	}

	private static void showAlert(String messageKey, ResourceBundle bundle) {
		final String message = bundle.getString(messageKey);
		final ButtonType ok = new ButtonType(bundle.getString("error.alert.ok"), ButtonData.OK_DONE);
		final Alert alert = new Alert(AlertType.ERROR, message, ok);
		alert.setTitle(bundle.getString("error.alert.title"));
		alert.showAndWait();
	}

}
