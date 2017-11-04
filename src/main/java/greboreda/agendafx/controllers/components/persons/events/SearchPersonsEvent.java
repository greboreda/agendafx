package greboreda.agendafx.controllers.components.persons.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SearchPersonsEvent extends Event {

	private static final EventType<SearchPersonsEvent> eventType = new EventType<SearchPersonsEvent>("searchperson");
	private final String search;

	public SearchPersonsEvent(String search) {
		super(eventType);
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
