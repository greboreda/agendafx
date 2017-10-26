package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.PersonFinder;
import greboreda.agendafx.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@FXMLController
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final PersonFinder personFinder;

	@Inject
	public MainController(PersonFinder personFinder) {
		this.personFinder = personFinder;
	}

	@FXML
	private Button loadPersonsButton;

	@FXML
	private TableView<Person> personsTable;


	@FXML
	public void onActionOnLoadPersonsButton(final Event event) {
		logger.info("Let's search persons!");
		final ObservableList<Person> persons = FXCollections.observableArrayList(personFinder.findAllPersons());
		personsTable.setItems(persons);
	}

}
