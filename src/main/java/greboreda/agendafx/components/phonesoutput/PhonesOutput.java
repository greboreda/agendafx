package greboreda.agendafx.components.phonesoutput;

import greboreda.agendafx.components.ComponentInitializer;
import greboreda.agendafx.domain.Phone;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.List;


public class PhonesOutput extends ListView<Phone> {

	private static final String PHONES_OUTPUT_FXML = "phonesoutput.fxml";

	public PhonesOutput() {
		super(FXCollections.observableArrayList());
		ComponentInitializer.init(this, PHONES_OUTPUT_FXML);
		this.setCellFactory(param -> new ListCell<Phone>() {
			@Override
			public void updateItem(Phone phone, boolean empty) {
				if (empty || phone == null) {
					setText(null);
				} else {
					setText(String.format("(%s) %s", phone.getPrefix(), phone.getNumber()));
				}
			}
		});
	}

	public void refresh(List<Phone> phones) {
		clear();
		super.getItems().addAll(phones);
	}

	public void clear() {
		super.getItems().clear();
	}
}
