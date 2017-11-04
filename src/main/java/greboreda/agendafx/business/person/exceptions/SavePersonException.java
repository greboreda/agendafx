package greboreda.agendafx.business.person.exceptions;

public class SavePersonException extends Exception {

	private final SavePersonError savePersonError;

	public SavePersonException(SavePersonError savePersonError, String message) {
		super(message);
		this.savePersonError = savePersonError;
	}

	public SavePersonError getSavePersonError() {
		return savePersonError;
	}
}
