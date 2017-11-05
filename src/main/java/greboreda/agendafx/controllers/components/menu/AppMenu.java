package greboreda.agendafx.controllers.components.menu;

import greboreda.agendafx.controllers.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class AppMenu extends MenuBar implements Initializable {

	@FXML
	private MenuItem quit;
	@FXML
	private MenuItem about;

	public AppMenu() {
		ViewLoader.load(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		quit.setOnAction(a -> Platform.exit());
		about.setOnAction(a -> {
			final Alert info = new Alert(AlertType.INFORMATION, resources.getString("app.about"));
			info.setTitle(resources.getString("generic.about"));
			info.setHeaderText(resources.getString("generic.dialog.info"));
			info.showAndWait();
		});
	}
}
