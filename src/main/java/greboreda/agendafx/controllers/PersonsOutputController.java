package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.PersonFinder;
import greboreda.agendafx.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@FXMLController
public class PersonsOutputController {

	private static final Logger logger = LoggerFactory.getLogger(PersonsOutputController.class);

	private final PersonFinder personFinder;

	@Inject
	public PersonsOutputController(PersonFinder personFinder) {
		this.personFinder = personFinder;
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

	public void refreshPersonsTable() {
		persons.clear();
		persons.addAll(personFinder.findAllPersons());
	}


}
