package greboreda.agendafx.domain.phone;

public class PhoneOwning {
	
	private final Boolean ownedByGivenPerson;
	private final Boolean ownedByMorePersons;

	private PhoneOwning(Boolean ownedByGivenPerson, Boolean ownedByMorePersons) {
		this.ownedByGivenPerson = ownedByGivenPerson;
		this.ownedByMorePersons = ownedByMorePersons;
	}

	public Boolean isOwnedByGivenPerson() {
		return ownedByGivenPerson;
	}

	public Boolean isOwnedByMorePersons() {
		return ownedByMorePersons;
	}

	public static class PhoneOwningBuilder {
		@FunctionalInterface
		public interface AddOwnedByMorePersons {
			PhoneOwning ownedByMorePersons(Boolean ownedByAnotherPersons);
		}
		public AddOwnedByMorePersons ownedByGivenPerson(Boolean ownedByGivenPerson) {
			return ownedByAnotherPersons -> new PhoneOwning(ownedByGivenPerson, ownedByAnotherPersons);
		}
	}

	public static PhoneOwningBuilder create() {
		return new PhoneOwningBuilder();
	}
}
