package greboreda.agendafx.controllers.components.persons;

import greboreda.agendafx.controllers.components.ViewLoader;
import greboreda.agendafx.controllers.components.persons.events.SearchPersonsEvent;
import greboreda.agendafx.controllers.components.persons.events.SelectPersonEvent;
import greboreda.agendafx.domain.person.Person;
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

	private EventHandler<SelectPersonEvent> onSelectPersonHandler;
	private EventHandler<SearchPersonsEvent> onSearchPersonsHandler;

	private final ObservableList<Person> persons = FXCollections.observableArrayList();

	public PersonsOutput() {
		ViewLoader.load(this, PERSONS_OUTPUT_FXML);
		initPersonsView();
		searchPersonInput.textProperty().addListener((observable, oldValue, newValue) -> {
			final SearchPersonsEvent event = new SearchPersonsEvent(newValue);
			onSearchPersonsHandler.handle(event);
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
				logger.debug("selected person: " + person);
				onSelectPersonHandler.handle(new SelectPersonEvent(person.getId()));
			}
		});

		personsView.setItems(persons);
	}

	public void refresh(List<Person> persons) {
		this.persons.clear();
		this.persons.addAll(persons);
	}

	public EventHandler<SelectPersonEvent> getOnSelectPerson() {
		return onSelectPersonHandler;
	}

	public void setOnSelectPerson(EventHandler<SelectPersonEvent> selectPersonEventEventHandler) {
		onSelectPersonHandler = selectPersonEventEventHandler;
	}

	public EventHandler<SearchPersonsEvent> getOnSearchPersons() {
		return onSearchPersonsHandler;
	}

	public void setOnSearchPersons(EventHandler<SearchPersonsEvent> searchPersonsEventHandler) {
		onSearchPersonsHandler = searchPersonsEventHandler;
	}

}
