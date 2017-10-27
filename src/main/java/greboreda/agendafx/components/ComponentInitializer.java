package greboreda.agendafx.components;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class ComponentInitializer {

	public static void init(Object component, String path) {
		final URL resource = ComponentInitializer.class.getResource(path);
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
