package greboreda.agendafx.controllers;

import greboreda.agendafx.controllers.components.persons.PersonInput;
import greboreda.agendafx.controllers.components.persons.PersonsOutput;
import greboreda.agendafx.controllers.components.phones.PhoneInput;
import greboreda.agendafx.controllers.components.phones.PhonesOutput;
import greboreda.agendafx.controllers.main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewLoader {

	private static final Map<Class,String> fxmlFiles = new HashMap<>();
	static{
		fxmlFiles.put(MainController.class, "main.fxml");
		fxmlFiles.put(PersonInput.class, "personinput.fxml");
		fxmlFiles.put(PersonsOutput.class, "personsoutput.fxml");
		fxmlFiles.put(PhoneInput.class, "phoneinput.fxml");
		fxmlFiles.put(PhonesOutput.class, "phonesoutput.fxml");

	}

	public static Parent load(Object component) {
		Validate.notNull(component, "component cannot be null");
		final String resourceName =  getFxmlByClass(component.getClass());
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

	private static String getFxmlByClass(Class clazz) {
		if(!fxmlFiles.containsKey(clazz)) {
			throw new IllegalArgumentException("Not fxml file defined for class " + clazz.getSimpleName());
		}
		return "/fxml/" + fxmlFiles.get(clazz);

	}

}
