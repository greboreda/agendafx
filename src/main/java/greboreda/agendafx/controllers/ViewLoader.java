package greboreda.agendafx.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ViewLoader {

	public static Parent load(Object component, String fxmlFileName) {
		final String resourceName = "/fxml/" + fxmlFileName;
		final URL resource = ViewLoader.class.getResource(resourceName);
		final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/messages", Locale.ENGLISH);
		final FXMLLoader fxmlLoader = new FXMLLoader(resource);
		fxmlLoader.setRoot(component);
		fxmlLoader.setController(component);
		fxmlLoader.setResources(resourceBundle);
		try {
			return fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
