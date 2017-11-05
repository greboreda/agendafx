package greboreda.agendafx.controllers;

import greboreda.agendafx.controllers.components.menu.AppMenu;
import greboreda.agendafx.controllers.components.persons.PersonInput;
import greboreda.agendafx.controllers.components.persons.PersonsOutput;
import greboreda.agendafx.controllers.components.phones.PhoneInput;
import greboreda.agendafx.controllers.components.phones.PhonesOutput;
import greboreda.agendafx.controllers.main.MainController;
import greboreda.agendafx.util.ResourceBundleRetriever;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ViewLoader {

	private static final Map<Class<? extends Node>,String> fxmlFiles = new HashMap<>();
	static{
		fxmlFiles.put(MainController.class, "/fxml/main.fxml");
		fxmlFiles.put(PersonInput.class, "/fxml/personinput.fxml");
		fxmlFiles.put(PersonsOutput.class, "/fxml/personsoutput.fxml");
		fxmlFiles.put(PhoneInput.class, "/fxml/phoneinput.fxml");
		fxmlFiles.put(PhonesOutput.class, "/fxml/phonesoutput.fxml");
		fxmlFiles.put(AppMenu.class, "/fxml/appmenu.fxml");

	}

	public static Parent load(Node component) {
		Validate.notNull(component, "component cannot be null");
		final String resourceName =  getFxmlByClass(component.getClass());
		final URL resource = ViewLoader.class.getResource(resourceName);
		final FXMLLoader fxmlLoader = new FXMLLoader(resource);
		fxmlLoader.setRoot(component);
		fxmlLoader.setController(component);
		fxmlLoader.setResources(ResourceBundleRetriever.get());
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
		return fxmlFiles.get(clazz);

	}

}
