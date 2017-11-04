package greboreda.agendafx.controllers.main;

import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.person.PersonSaver;
import greboreda.agendafx.business.person.exceptions.SavePersonException;
import greboreda.agendafx.business.phone.PhoneFinder;
import greboreda.agendafx.business.phone.PhoneSaver;
import greboreda.agendafx.business.phone.exceptions.SavePhoneException;
import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.persons.PersonInput;
import greboreda.agendafx.controllers.components.persons.PersonsOutput;
import greboreda.agendafx.controllers.components.persons.events.SavePersonEvent;
import greboreda.agendafx.controllers.components.persons.events.SearchPersonsEvent;
import greboreda.agendafx.controllers.components.persons.events.SelectPersonEvent;
import greboreda.agendafx.controllers.components.phones.PhoneInput;
import greboreda.agendafx.controllers.components.phones.PhonesOutput;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.domain.person.PersonToSave;
import greboreda.agendafx.domain.phone.Phone;
import greboreda.agendafx.domain.phone.PhoneToSave;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static greboreda.agendafx.controllers.main.MainControllerUtils.manageSavePersonError;
import static greboreda.agendafx.controllers.main.MainControllerUtils.manageSavePhoneError;

@Component
public class MainController extends VBox implements Initializable {

	private static final String MAIN_FXML = "main.fxml";

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final PersonSaver personSaver;
	private final PersonFinder personFinder;
	private final PhoneFinder phoneFinder;
	private final PhoneSaver phoneSaver;

	@Inject
	public MainController(PersonSaver personSaver, PersonFinder personFinder, PhoneFinder phoneFinder, PhoneSaver phoneSaver) {
		this.personSaver = personSaver;
		this.personFinder = personFinder;
		this.phoneFinder = phoneFinder;
		this.phoneSaver = phoneSaver;
	}

	@FXML
	PersonInput personInput;
	@FXML
	PersonsOutput personsOutput;
	@FXML
	PhonesOutput phonesOutput;
	@FXML
	PhoneInput phoneInput;

	private ResourceBundle resourceBundle;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.info("Initializing");
		this.resourceBundle = resources;
		personsOutput.setOnSearchPersons(this::onSearchPersons);
		personsOutput.setOnSelectPerson(this::onSelectPerson);
		phoneInput.setOnSavePhone(this::onSavePhone);
		personInput.setOnSavePerson(this::onSavePerson);
		refreshPersonsOutput(personFinder.findAllPersons());
	}

	public Parent build() throws IOException {
		return ViewLoader.load(this, MAIN_FXML);
	}

	void onSavePerson(SavePersonEvent savePersonEvent) {
		final PersonToSave personToSave = savePersonEvent.getpersonToSave();
		logger.debug("Lets save person: " + personToSave);
		try {
			personSaver.savePerson(personToSave);
		} catch (SavePersonException e) {
			manageSavePersonError(e.getSavePersonError(), resourceBundle);
		}
		refreshPersonsOutput(personFinder.findAllPersons());
	}

	private void onSelectPerson(SelectPersonEvent selectPersonEvent) {
		final Integer personId = selectPersonEvent.getPersonId();
		phoneInput.setPersonIdToSavePhone(personId);
		refreshPhonesOutput(personId);
	}

	private void onSearchPersons(SearchPersonsEvent searchPersonsEvent) {
		final String search = searchPersonsEvent.getSearch();
		logger.debug("Lets search persons like " + search);
		final List<Person> persons = personFinder.findPersonsByFreeSearch(search);
		refreshPersonsOutput(persons);
	}

	void onSavePhone(SavePhoneEvent savePhoneEvent) {
		final PhoneToSave phoneToSave = savePhoneEvent.getPhoneToSave();
		logger.debug(String.format("Lets save phone: (%s)%s for person %s",
				phoneToSave.prefix,
				phoneToSave.number,
				phoneToSave.personId
		));
		try {
			phoneSaver.savePhone(phoneToSave);
			final List<Phone> phones = phoneFinder.findPhonesByPersonId(phoneToSave.personId);
			phonesOutput.refresh(phones);
		} catch (SavePhoneException e) {
			manageSavePhoneError(e.getSavePhoneError(), resourceBundle);
		}
	}

	private void refreshPhonesOutput(Integer personId) {
		final List<Phone> phones = phoneFinder.findPhonesByPersonId(personId);
		logger.debug(String.format("Refreshing phones for person with id %s", personId));
		phonesOutput.refresh(phones);
	}

	private void refreshPersonsOutput(List<Person> persons) {
		logger.debug("Refreshing persons output!");
		personsOutput.refresh(persons);
		phonesOutput.clear();
	}
}
