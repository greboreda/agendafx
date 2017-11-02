package greboreda.agendafx.domain.phone;

public class PhoneOwning {
	
	private final Boolean ownedByGivenPerson;
	private final Boolean ownedByAnotherPersons;

	private PhoneOwning(Boolean ownedByGivenPerson, Boolean ownedByAnotherPersons) {
		this.ownedByGivenPerson = ownedByGivenPerson;
		this.ownedByAnotherPersons = ownedByAnotherPersons;
	}

	public Boolean isOwnedByGivenPerson() {
		return ownedByGivenPerson;
	}

	public Boolean isOwnedByAnotherPersons() {
		return ownedByAnotherPersons;
	}

	public static class PhoneOwningBuilder {
		@FunctionalInterface
		public interface AddOwnedByAnotherPersons {
			PhoneOwning ownedByAnotherPersons(Boolean ownedByAnotherPersons);
		}
		public AddOwnedByAnotherPersons ownedByGivenPerson(Boolean ownedByGivenPerson) {
			return ownedByAnotherPersons -> new PhoneOwning(ownedByGivenPerson, ownedByAnotherPersons);
		}
	}

	public static PhoneOwningBuilder create() {
		return new PhoneOwningBuilder();
	}
}
