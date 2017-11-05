package greboreda.agendafx.controllers.components.menu;

import greboreda.agendafx.controllers.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class AppMenu extends MenuBar implements Initializable {

	@FXML
	MenuItem quit;

	public AppMenu() {
		ViewLoader.load(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		quit.setOnAction(a -> Platform.exit());
	}
}
