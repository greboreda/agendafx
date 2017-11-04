package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.domain.phone.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PhonesOutput extends VBox {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PhonesOutput.class);

	@FXML
	private ListView<Phone> phonesView;

	private final ObservableList<Phone> phones = FXCollections.observableArrayList();

	public PhonesOutput() {
		ViewLoader.load(this);
		phonesView.setCellFactory(param -> new ListCell<Phone>() {
			@Override
			public void updateItem(Phone phone, boolean empty) {
				super.updateItem(phone, empty);
				if (empty || phone == null) {
					setText(null);
				} else {
					final String text = String.format("(%s) %s", phone.getPrefix(), phone.getNumber());
					setText(text);
				}
			}
		});
		phonesView.setItems(phones);
	}

	public void refresh(List<Phone> phones) {
		clear();
		this.phones.addAll(phones);
	}

	public void clear() {
		phones.clear();
	}

}
