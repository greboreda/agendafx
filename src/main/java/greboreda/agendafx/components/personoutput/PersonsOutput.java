package greboreda.agendafx.components.personoutput;

import greboreda.agendafx.components.ComponentInitializer;
import greboreda.agendafx.domain.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

import java.util.List;

public class PersonsOutput extends ListView<Person> {

	private static final String PERSONS_OUTPUT_FXML = "personsoutput.fxml";

	private final ObjectProperty<EventHandler<SelectPersonEvent>> onSelectPerson = new SimpleObjectProperty<>();

	public PersonsOutput() {
		super(FXCollections.observableArrayList());
		ComponentInitializer.init(this, PERSONS_OUTPUT_FXML);
		this.setCellFactory(param -> new PersonCell(onSelectPerson.get()));
	}

	public void refresh(List<Person> persons) {
		super.getItems().clear();
		super.getItems().addAll(persons);
	}

	public EventHandler<SelectPersonEvent> getOnSelectPerson() {
		return onSelectPerson.get();
	}

	public void setOnSelectPersonProperty(EventHandler<SelectPersonEvent> selectPersonEventEventHandler) {
		onSelectPerson.set(selectPersonEventEventHandler);
	}

	public void setOnSelectPerson(EventHandler<SelectPersonEvent> onSelectPerson) {
		this.onSelectPerson.set(onSelectPerson);
	}
}
