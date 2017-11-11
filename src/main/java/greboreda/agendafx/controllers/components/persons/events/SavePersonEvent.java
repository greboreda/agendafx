package greboreda.agendafx.controllers.components.persons.events;

import greboreda.agendafx.domain.person.PersonToSave;
import javafx.event.Event;

import static greboreda.agendafx.controllers.components.persons.events.PersonEventTypes.SAVE_PERSON;

public class SavePersonEvent extends Event {

	private PersonToSave personToSave;

	public SavePersonEvent(PersonToSave personToSave) {
		super(SAVE_PERSON);
		this.personToSave = personToSave;
	}

	public PersonToSave getPersonToSave() {
		return personToSave;
	}

}
