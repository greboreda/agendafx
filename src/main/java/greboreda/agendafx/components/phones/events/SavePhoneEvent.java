package greboreda.agendafx.components.phones.events;

import greboreda.agendafx.domain.phone.PhoneToSave;
import javafx.event.Event;
import javafx.event.EventType;

public class SavePhoneEvent extends Event {

	private static final EventType<SavePhoneEvent> eventType = new EventType<>("savephone");
	private final PhoneToSave phoneToSave;

	public SavePhoneEvent(PhoneToSave phoneToSave) {
		super(eventType);
		this.phoneToSave = phoneToSave;
	}

	public PhoneToSave getPhoneToSave() {
		return phoneToSave;
	}
}
