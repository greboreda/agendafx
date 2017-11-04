package greboreda.agendafx.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class ViewLoader {

	public static Parent load(Object component, String fxmlFileName) {
		final String resourceName = "/fxml/" + fxmlFileName;
		final URL resource = ViewLoader.class.getResource(resourceName);
		final FXMLLoader fxmlLoader = new FXMLLoader(resource);
		fxmlLoader.setRoot(component);
		fxmlLoader.setController(component);
		try {
			return fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
