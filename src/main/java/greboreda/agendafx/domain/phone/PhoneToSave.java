package greboreda.agendafx.domain.phone;

public class PhoneToSave {

	public final Integer personId;
	public final String prefix;
	public final String number;

	private PhoneToSave(Integer personId, String prefix, String number) {
		this.personId = personId;
		this.prefix = prefix;
		this.number = number;
	}

	public static PhoneToSaveBuilder create() {
		return new PhoneToSaveBuilder();
	}

	public static class PhoneToSaveBuilder {
		@FunctionalInterface
		public interface AddPrefix {
			AddNumber withPrefix(String prefix);
		}
		@FunctionalInterface
		public interface AddNumber{
			PhoneToSave withNumber(String number);
		}
		private PhoneToSaveBuilder() {

		}
		public AddPrefix forPersonWithId(Integer personId) {
			return prefix -> number -> new PhoneToSave(personId, prefix, number);
		}
	}
}
