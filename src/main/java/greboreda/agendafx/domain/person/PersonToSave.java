package greboreda.agendafx.domain.person;

public class PersonToSave {

	public final String firstName;
	public final String lastName;

	private PersonToSave(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static PersonToSaveBuilder create() {
		return new PersonToSaveBuilder();
	}

	public static class PersonToSaveBuilder {
		@FunctionalInterface
		public interface  AddLastName {
			PersonToSave withLastName(String lastName);
		}
		public AddLastName withFirstName(String firstName) {
			return lastName -> new PersonToSave(firstName, lastName);
		}
	}

	@Override
	public String toString() {
		return "PersonToSave{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
