package greboreda.agendafx.controllers.components.persons.events;

import javafx.event.Event;

import static greboreda.agendafx.controllers.components.persons.events.PersonEventTypes.SEARCH_PERSON;

public class SearchPersonsEvent extends Event {

	private final String search;

	public SearchPersonsEvent(String search) {
		super(SEARCH_PERSON);
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
