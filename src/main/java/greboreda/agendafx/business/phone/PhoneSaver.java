package greboreda.agendafx.business.phone;

import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.phone.exceptions.SavePhoneError;
import greboreda.agendafx.business.phone.exceptions.SavePhoneException;
import greboreda.agendafx.domain.phone.PhoneOwning;
import greboreda.agendafx.domain.phone.PhoneToSave;
import greboreda.agendafx.persistence.dao.PhoneDAO;
import greboreda.agendafx.persistence.mappers.PhoneMapper;
import greboreda.agendafx.persistence.vo.PersonVO;
import greboreda.agendafx.persistence.vo.PhoneVO;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;

@Named
public class PhoneSaver {

	private final PersonFinder personFinder;
	private final PhoneOwningChecker phoneOwningChecker;
	private final PhoneDAO phoneDAO;

	@Inject
	public PhoneSaver(PersonFinder personFinder, PhoneOwningChecker phoneOwningChecker, PhoneDAO phoneDAO) {
		this.personFinder = personFinder;
		this.phoneOwningChecker = phoneOwningChecker;
		this.phoneDAO = phoneDAO;
	}

	public void savePhone(PhoneToSave phoneToSave) throws SavePhoneException {

		Validate.notNull(phoneToSave, "phoneToSave cannot be null");
		validatePersonExists(phoneToSave.personId);
		validatePhoneOwning(phoneToSave);

		final PhoneVO phoneVO = PhoneMapper.mapToPhoneVOToSave(phoneToSave);
		phoneDAO.save(phoneVO);
	}

	private void validatePhoneOwning(PhoneToSave phoneToSave) throws SavePhoneException {
		final PhoneOwning phoneOwning = phoneOwningChecker.checkOwningToSave(phoneToSave);
		if(phoneOwning.isOwnedByGivenPerson()) {
			final String errorMessage = "person already owns that phone";
			throw new SavePhoneException(SavePhoneError.PHONE_ALREADY_EXISTS_FOR_THAT_PERSON, errorMessage);
		} else if (phoneOwning.isOwnedByAnotherPersons()) {
			final String errorMessage = "phone already exists for another person(s)";
			throw new SavePhoneException(SavePhoneError.PHONE_ALREAY_EXISTS_FOR_ANOTHER_PERSON, errorMessage);
		}
	}

	private void validatePersonExists(Integer personId) throws SavePhoneException {
		final Boolean exists = personFinder.existsPersonWithId(personId);
		if(!exists) {
			final String errorMessage = "person with id " + personId + " not found";
			throw new SavePhoneException(SavePhoneError.PERSON_NOT_EXISTS, errorMessage);
		}
	}

}
