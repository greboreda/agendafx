package greboreda.agendafx.controllers.components.persons.events;

import javafx.event.Event;

import static greboreda.agendafx.controllers.components.persons.events.PersonEventTypes.SELECT_PERSON;

public class SelectPersonEvent extends Event {

	private final Integer personId;

	public SelectPersonEvent(Integer personId) {
		super(SELECT_PERSON);
		this.personId = personId;
	}

	public Integer getPersonId() {
		return personId;
	}
}
