package greboreda.agendafx.components.personoutput;

import greboreda.agendafx.components.ComponentFactory;
import greboreda.agendafx.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class PersonsOutput extends FlowPane {

	private static final String PERSONS_OUTPUT_FXML = "/greboreda/agendafx/components/personsoutput.fxml";

	@FXML
	private TableView<Person> personsTable;

	private final ObservableList<Person> showablePersons = FXCollections.observableArrayList();

	public PersonsOutput() {
		ComponentFactory.initializeComponent(this, PERSONS_OUTPUT_FXML);
		personsTable.setItems(showablePersons);
	}

	public void refresh(List<Person> persons) {
		showablePersons.clear();
		showablePersons.addAll(persons);
	}

}
