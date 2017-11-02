package greboreda.agendafx.business.phone;

import greboreda.agendafx.domain.phone.PhoneOwning;
import greboreda.agendafx.domain.phone.PhoneToSave;
import greboreda.agendafx.persistence.dao.PhoneDAO;
import greboreda.agendafx.persistence.vo.PersonVO;
import greboreda.agendafx.persistence.vo.PhoneVO;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@Named
public class PhoneOwningChecker {

	private final PhoneDAO phoneDAO;

	@Inject
	public PhoneOwningChecker(PhoneDAO phoneDAO) {
		this.phoneDAO = phoneDAO;
	}

	public PhoneOwning checkOwningToSave(PhoneToSave phoneToSave) {

		Validate.notNull(phoneToSave);

		final PhoneVO phoneVO = phoneDAO.findPhoneByPrefixAndNumber(phoneToSave.prefix, phoneToSave.number);
		if(phoneVO == null) {
			return PhoneOwning.create()
					.ownedByGivenPerson(false)
					.ownedByAnotherPersons(false);
		}

		final Set<PersonVO> owners = phoneVO.getOwners();
		final boolean personAlreadyOwnsPhone = checkIfPersonAlreadyOwnsPhone(phoneToSave, owners);
		final boolean anotherPersonOwnsPhone = checkIfAnotherPersonOwnsPhone(phoneToSave, owners);

		return PhoneOwning.create()
				.ownedByGivenPerson(personAlreadyOwnsPhone)
				.ownedByAnotherPersons(anotherPersonOwnsPhone);
	}

	private boolean checkIfAnotherPersonOwnsPhone(PhoneToSave phoneToSave, Set<PersonVO> owners) {
		return owners.stream()
				.map(PersonVO::getId)
				.filter(id -> !id.equals(phoneToSave.personId))
				.iterator()
				.hasNext();
	}

	private boolean checkIfPersonAlreadyOwnsPhone(PhoneToSave phoneToSave, Set<PersonVO> owners) {
		return owners.stream()
				.map(PersonVO::getId)
				.anyMatch(id -> id.equals(phoneToSave.personId));
	}

}
