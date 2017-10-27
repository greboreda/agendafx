package greboreda.agendafx.components;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public interface CustomComponent {

	String getFxmlPath();

	default void init() {
		final URL resource = getClass().getResource(getFxmlPath());
		final FXMLLoader fxmlLoader = new FXMLLoader(resource);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
