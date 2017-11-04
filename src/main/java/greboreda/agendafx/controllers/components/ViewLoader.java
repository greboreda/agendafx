package greboreda.agendafx.controllers.components;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class ViewLoader {

	public static void load(Object component, String path) {
		final URL resource = ViewLoader.class.getResource(path);
		final FXMLLoader fxmlLoader = new FXMLLoader(resource);
		fxmlLoader.setRoot(component);
		fxmlLoader.setController(component);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
