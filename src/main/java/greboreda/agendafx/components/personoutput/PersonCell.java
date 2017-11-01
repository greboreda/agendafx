package greboreda.agendafx.components.personoutput;

import greboreda.agendafx.domain.Person;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;


public class PersonCell extends ListCell<Person> {

	private final EventHandler<SelectPersonEvent> onSelectPersonEventHandler;

	PersonCell(EventHandler<SelectPersonEvent> onSelectPersonEventHandler) {
		this.onSelectPersonEventHandler = onSelectPersonEventHandler;
	}

	@Override
	protected void updateItem(Person person, boolean empty) {
		if(!empty) {
			this.setText(formatPersonName(person));
		}
		setOnMouseClicked(e -> onSelect(person));
	}

	private void onSelect(Person person) {
		final SelectPersonEvent selectPersonEvent = new SelectPersonEvent(person.getId());
		onSelectPersonEventHandler.handle(selectPersonEvent);
	}

	private static String formatPersonName(Person person) {
		return String.format("%s %s", person.getFirstName(), person.getLastName());
	}

}
