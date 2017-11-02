package greboreda.agendafx.business.phone.exceptions;

public class SavePhoneException extends Exception {

	private final SavePhoneError savePhoneError;

	public SavePhoneException(SavePhoneError savePhoneError, String message) {
		super(message);
		this.savePhoneError = savePhoneError;
	}

	public SavePhoneError getSavePhoneError() {
		return savePhoneError;
	}
}
