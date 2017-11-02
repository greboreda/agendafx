package greboreda.agendafx.controllers;

import de.felixroske.jfxsupport.FXMLController;
import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.person.PersonSaver;
import greboreda.agendafx.business.phone.PhoneFinder;
import greboreda.agendafx.business.phone.PhoneSaver;
import greboreda.agendafx.business.phone.exceptions.SavePhoneException;
import greboreda.agendafx.components.persons.PersonInput;
import greboreda.agendafx.components.persons.PersonsOutput;
import greboreda.agendafx.components.persons.events.SavePersonEvent;
import greboreda.agendafx.components.persons.events.SearchPersonsEvent;
import greboreda.agendafx.components.persons.events.SelectPersonEvent;
import greboreda.agendafx.components.phones.PhoneInput;
import greboreda.agendafx.components.phones.PhonesOutput;
import greboreda.agendafx.components.phones.events.SavePhoneEvent;
import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.domain.person.PersonToSave;
import greboreda.agendafx.domain.phone.Phone;
import greboreda.agendafx.domain.phone.PhoneToSave;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@FXMLController
public class MainController {

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
	protected PersonInput personInput;
	@FXML
	protected PersonsOutput personsOutput;
	@FXML
	protected PhonesOutput phonesOutput;
	@FXML
	protected PhoneInput phoneInput;

	@FXML
	public void initialize() {
		logger.info("Initializing");
		refreshPersonsOutput(personFinder.findAllPersons());
	}

	public void onSavePerson(SavePersonEvent savePersonEvent) {
		final PersonToSave personToSave = savePersonEvent.getpersonToSave();
		logger.debug("Lets save person: " + personToSave);
		personSaver.savePerson(personToSave);
		refreshPersonsOutput(personFinder.findAllPersons());
	}

	public void onSelectPerson(SelectPersonEvent selectPersonEvent) {
		final Integer personId = selectPersonEvent.getPersonId();
		phoneInput.setPersonIdToSavePhone(personId);
		refreshPhonesOutput(personId);
	}

	public void onSearchPersons(SearchPersonsEvent searchPersonsEvent) {
		final String search = searchPersonsEvent.getSearch();
		logger.debug("Lets search persons like " + search);
		final List<Person> persons = personFinder.findPersonsByFreeSearch(search);
		refreshPersonsOutput(persons);
	}

	public void onSavePhone(SavePhoneEvent savePhoneEvent) {
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
			throw new RuntimeException(e.getMessage());
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
