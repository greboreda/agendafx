package greboreda.agendafx.controllers.components.persons.events;

import javafx.event.Event;
import javafx.event.EventType;

public class PersonEventTypes {

	public static final EventType<Event> PERSON_EVENT = new EventType<>("PERSON_EVENT");

	public static final EventType<SavePersonEvent> SAVE_PERSON = new EventType<>(PERSON_EVENT, "SAVE_PERSON");
	public static final EventType<SearchPersonsEvent> SEARCH_PERSON = new EventType<SearchPersonsEvent>(PERSON_EVENT, "SEARCH_PERSON");
	public static final EventType<SelectPersonEvent> SELECT_PERSON = new EventType<>(PERSON_EVENT, "SELECT_PERSON");

}
