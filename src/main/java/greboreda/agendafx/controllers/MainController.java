package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FXMLController
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	@FXML
	private Button mainButton;


	@FXML
	public void onActionMainButton(final Event event) {
		logger.info("click!");
	}

}
