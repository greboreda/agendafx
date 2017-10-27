package greboreda.agendafx.components.personinput;

import javafx.event.Event;
import javafx.event.EventType;

public class SavePersonEvent extends Event {

	private PersonToCreate personToCreate;

	SavePersonEvent(PersonToCreate personToCreate) {
		super(new EventType<SavePersonEvent>());
		this.personToCreate = personToCreate;
	}

	public PersonToCreate getPersonToCreate() {
		return personToCreate;
	}

}
