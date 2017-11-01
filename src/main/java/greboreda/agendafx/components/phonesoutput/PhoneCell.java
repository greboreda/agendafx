package greboreda.agendafx.components.phonesoutput;

import greboreda.agendafx.domain.Phone;
import javafx.scene.control.ListCell;

public class PhoneCell extends ListCell<Phone> {

	@Override
	public void updateItem(Phone phone, boolean empty) {
		if(!empty) {
			setText(formatPhone(phone));
		}
	}

	private String formatPhone(Phone phone) {
		return String.format("(%s) %s", phone.getPrefix(), phone.getNumber());
	}

}
