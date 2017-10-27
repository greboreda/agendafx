package greboreda.agendafx.components.personoutput;

import greboreda.agendafx.domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class PersonsOutput extends FlowPane {

	private static final String PERSONS_OUTPUT_FXML = "/greboreda/agendafx/components/personsoutput.fxml";

	@FXML
	private TableView<Person> personsTable;

	private final ObservableList<Person> showablePersons = FXCollections.observableArrayList();

	public PersonsOutput() {
		init();
		personsTable.setItems(showablePersons);
	}

	public void refresh(List<Person> persons) {
		showablePersons.clear();
		showablePersons.addAll(persons);
	}

	private void init() {
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PERSONS_OUTPUT_FXML));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
