package greboreda.agendafx.controllers.components.phones.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SavePhoneEvent extends Event {

	public static final EventType<Event> PHONE_EVENT = new EventType<>("PHONE_EVENT");
	public static final EventType<SavePhoneEvent> SAVE_PHONE = new EventType<>(PHONE_EVENT, "SAVE_PHONE");

	private final String phonePrefix;
	private final String phoneNumber;

	private SavePhoneEvent(String phonePrefix, String phoneNumber) {
		super(SAVE_PHONE);
		this.phonePrefix = phonePrefix;
		this.phoneNumber = phoneNumber;
	}

	public String getPhonePrefix() {
		return phonePrefix;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public static SavePhoneEventBuilder create() {
		return new SavePhoneEventBuilder();
	}

	public static class SavePhoneEventBuilder {
		@FunctionalInterface
		public interface AddPhoneNumber {
			SavePhoneEvent withPhoneNumber(String phoneNumber);
		}
		private SavePhoneEventBuilder() {

		}
		public AddPhoneNumber withPhonePrefix(String phonePrefix) {
			return phoneNumber -> new SavePhoneEvent(phonePrefix, phoneNumber);
		}

	}
}
