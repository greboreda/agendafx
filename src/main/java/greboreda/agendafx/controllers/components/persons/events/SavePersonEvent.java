package greboreda.agendafx.controllers.components.persons.events;

import greboreda.agendafx.domain.person.PersonToSave;
import javafx.event.Event;
import javafx.event.EventType;

public class SavePersonEvent extends Event {

	private static final EventType<SavePersonEvent> eventType = new EventType<>("saveperson");

	private PersonToSave personToSave;

	public SavePersonEvent(PersonToSave personToSave) {
		super(eventType);
		this.personToSave = personToSave;
	}

	public PersonToSave getpersonToSave() {
		return personToSave;
	}

}
