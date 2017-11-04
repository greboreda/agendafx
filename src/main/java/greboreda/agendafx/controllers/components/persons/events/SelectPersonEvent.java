package greboreda.agendafx.controllers.components.persons.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SelectPersonEvent extends Event {

	private static final EventType<SelectPersonEvent> eventType = new EventType<>("selectperson");
	private final Integer personId;

	public SelectPersonEvent(Integer personId) {
		super(eventType);
		this.personId = personId;
	}

	public Integer getPersonId() {
		return personId;
	}
}
