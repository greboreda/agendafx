package greboreda.agendafx.components.personoutput;

import greboreda.agendafx.components.ComponentInitializer;
import greboreda.agendafx.domain.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PersonsOutput extends VBox {

	private static final Logger logger = LoggerFactory.getLogger(PersonsOutput.class);

	private static final String PERSONS_OUTPUT_FXML = "personsoutput.fxml";

	@FXML
	private ListView<Person> personsView;
	@FXML
	private TextField searchPersonInput;

	private final ObjectProperty<EventHandler<SelectPersonEvent>> onSelectPerson = new SimpleObjectProperty<>();
	private final ObjectProperty<EventHandler<SearchPersonsEvent>> onSearchPersons = new SimpleObjectProperty<>();

	private final ObservableList<Person> persons = FXCollections.observableArrayList();

	public PersonsOutput() {
		ComponentInitializer.init(this, PERSONS_OUTPUT_FXML);
		initPersonsView();
		searchPersonInput.textProperty().addListener((observable, oldValue, newValue) -> {
			final SearchPersonsEvent event = new SearchPersonsEvent(newValue);
			onSearchPersons.get().handle(event);
		});
	}

	private void initPersonsView() {
		personsView.setCellFactory(param -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person person, boolean empty) {
				super.updateItem(person, empty);
				if(empty || person == null) {
					setText(null);
				} else {
					setText(String.format("%s %s", person.getFirstName(), person.getLastName()));
				}
			}
		});

		personsView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
			final Person person = observableValue.getValue();
			if(person != null) {
				onSelectPerson.get().handle(new SelectPersonEvent(person.getId()));
			}
		});

		personsView.setItems(persons);
	}

	public void refresh(List<Person> persons) {
		this.persons.clear();
		this.persons.addAll(persons);
	}

	public EventHandler<SelectPersonEvent> getOnSelectPerson() {
		return onSelectPerson.get();
	}

	public void setOnSelectPerson(EventHandler<SelectPersonEvent> selectPersonEventEventHandler) {
		onSelectPerson.set(selectPersonEventEventHandler);
	}

	public EventHandler<SearchPersonsEvent> getOnSearchPersons() {
		return onSearchPersons.get();
	}

	public void setOnSearchPersons(EventHandler<SearchPersonsEvent> searchPersonsEventEventHandler) {
		onSearchPersons.set(searchPersonsEventEventHandler);
	}

}
