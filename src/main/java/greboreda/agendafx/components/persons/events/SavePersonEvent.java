package greboreda.agendafx.components.persons.events;

import greboreda.agendafx.components.persons.dto.PersonToCreate;
import javafx.event.Event;
import javafx.event.EventType;

public class SavePersonEvent extends Event {

	private static final EventType<SavePersonEvent> eventType = new EventType<>("saveperson");

	private PersonToCreate personToCreate;

	public SavePersonEvent(PersonToCreate personToCreate) {
		super(eventType);
		this.personToCreate = personToCreate;
	}

	public PersonToCreate getPersonToCreate() {
		return personToCreate;
	}

}
