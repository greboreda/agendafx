package greboreda.agendafx.persistence.mappers;

import greboreda.agendafx.domain.phone.Phone;
import greboreda.agendafx.domain.phone.PhoneToSave;
import greboreda.agendafx.persistence.vo.PersonVO;
import greboreda.agendafx.persistence.vo.PhoneVO;

import java.util.Collections;

public class PhoneMapper {

	public static Phone map(PhoneVO phoneVO) {
		if(phoneVO == null) {
			return null;
		}
		return Phone.create()
				.withId(phoneVO.getId())
				.withNumber(phoneVO.getNumber())
				.withPrefix(phoneVO.getPrefix())
				.build();
	}

	public static PhoneVO mapToPhoneVOToSave(PhoneToSave phoneToSave) {
		if(phoneToSave == null) {
			return null;
		}
		final PersonVO personVO = new PersonVO();
		personVO.setId(phoneToSave.personId);

		final PhoneVO phoneVO = new PhoneVO();
		phoneVO.setNumber(phoneToSave.number);
		phoneVO.setPrefix(phoneToSave.prefix);
		phoneVO.setOwners(Collections.singleton(personVO));
		return phoneVO;
	}

}
