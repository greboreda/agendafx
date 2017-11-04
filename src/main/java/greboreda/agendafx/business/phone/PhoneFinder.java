package greboreda.agendafx.business.phone;

import greboreda.agendafx.domain.phone.Phone;
import greboreda.agendafx.persistence.dao.PhoneDAO;
import greboreda.agendafx.persistence.mappers.PhoneMapper;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Named
public class PhoneFinder {

	private final PhoneDAO phoneDAO;

	@Inject
	public PhoneFinder(PhoneDAO phoneDAO) {
		this.phoneDAO = phoneDAO;
	}

	public List<Phone> findPhonesByPersonId(Integer personId) {
		Validate.notNull(personId, "personId cannot be null");
		return phoneDAO.findPhonesByPersonId(personId).stream()
				.map(PhoneMapper::map)
				.collect(toList());
	}

}
