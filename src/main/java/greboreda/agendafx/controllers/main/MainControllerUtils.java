package greboreda.agendafx.controllers.main;

import greboreda.agendafx.business.person.exceptions.SavePersonError;
import greboreda.agendafx.business.phone.exceptions.SavePhoneError;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class MainControllerUtils {

	static void manageSavePersonError(SavePersonError savePersonError) {
		String message;
		switch (savePersonError) {
			case PERSON_ALREADY_EXISTS:
				message = "person already exists";
				break;
			default:
				message = "unexpected error";
		}
		final Alert alert = new Alert(AlertType.ERROR, message);
		alert.showAndWait();
	}

	static void manageSavePhoneError(SavePhoneError savePhoneError) {
		String message;
		switch (savePhoneError) {
			case PHONE_ALREADY_EXISTS_FOR_THAT_PERSON:
				message = "phone already exists for that person";
				break;
			case PHONE_ALREAY_EXISTS_FOR_ANOTHER_PERSON:
				message = "phone already exists for another person";
				break;
			default:
				message = "unexpected error";
		}
		final Alert alert = new Alert(AlertType.ERROR, message);
		alert.showAndWait();
	}


}
