package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.PersonCreator;
import greboreda.agendafx.business.PersonFinder;
import greboreda.agendafx.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@FXMLController
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final PersonFinder personFinder;
	private final PersonCreator personCreator;

	@Inject
	public MainController(PersonFinder personFinder, PersonCreator personCreator) {
		this.personFinder = personFinder;
		this.personCreator = personCreator;
	}

	@FXML
	private TableView<Person> personsTable;

	private final ObservableList<Person> persons = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		logger.info("Initializing view");
		personsTable.setItems(persons);
		refreshPersonsTable();
	}

	@FXML
	private TextField firstNameInput;

	@FXML
	private TextField lastNameInput;
	@FXML
	public void onCreatePerson(final Event event) {

		final String firstName = firstNameInput.getText();
		final String lastName = lastNameInput.getText();

		final Person person = Person.create()
				.withId(null)
				.withFirstName(firstName)
				.withLastName(lastName)
				.build();
		personCreator.createPerson(person);

		logger.info("Created new Person: " + person);

		refreshPersonsTable();

		firstNameInput.clear();
		lastNameInput.clear();
	}

	private void refreshPersonsTable() {
		persons.clear();
		persons.addAll(personFinder.findAllPersons());
	}
}
