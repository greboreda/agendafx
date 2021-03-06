package greboreda.agendafx.domain.person;

public class Person {

	private final Integer id;
	private final String firstName;
	private final String lastName;

	private Person(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public static PersonBuilder create() {
		return new PersonBuilder();
	}

	public static class PersonBuilder {
		@FunctionalInterface
		public interface AddFirstName {
			AddLastName withFirstName(String firstName);
		}
		@FunctionalInterface
		public interface AddLastName {
			Builder withLastName(String lastName);
		}
		@FunctionalInterface
		public interface Builder {
			Person build();
		}
		private PersonBuilder() {

		}
		public AddFirstName withId(Integer id) {
			return firstName ->lastName -> () -> new Person(id, firstName, lastName);
		}
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
