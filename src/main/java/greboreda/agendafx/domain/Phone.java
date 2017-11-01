package greboreda.agendafx.domain;

public class Phone {

	private final Integer id;
	private final String number;
	private final String prefix;

	private Phone(Integer id, String number, String prefix) {
		this.id = id;
		this.number = number;
		this.prefix = prefix;
	}

	public Integer getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getPrefix() {
		return prefix;
	}

	public static PhoneBuilder create() {
		return new PhoneBuilder();
	}

	public static class PhoneBuilder {
		@FunctionalInterface
		public interface AddNumber {
			AddPrefix withNumber(String number);
		}
		@FunctionalInterface
		public interface AddPrefix {
			Builder withPrefix(String prefix);
		}
		@FunctionalInterface
		public interface Builder {
			Phone build();
		}
		private PhoneBuilder() {

		}
		public AddNumber withId(Integer id) {
			return number -> prefix -> () -> new Phone(id, number, prefix);
		}
	}
}
