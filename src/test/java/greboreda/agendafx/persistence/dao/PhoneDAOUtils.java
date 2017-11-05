package greboreda.agendafx.persistence.dao;

import greboreda.agendafx.persistence.vo.PersonVO;
import greboreda.agendafx.persistence.vo.PhoneVO;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;

class PhoneDAOUtils {

	static PhoneVO aPhone(Integer personId, String prefix, String number) {
		final PhoneVO phoneVO = new PhoneVO();
		final PersonVO personVO = new PersonVO();
		personVO.setId(personId);
		phoneVO.setOwners(Collections.singleton(personVO));
		phoneVO.setNumber(number);
		phoneVO.setPrefix(prefix);
		return phoneVO;
	}

	static PhoneVO aPhone(String prefix, String number) {
		final PhoneVO phoneVO = new PhoneVO();
		phoneVO.setNumber(number);
		phoneVO.setPrefix(prefix);
		return phoneVO;
	}

	static PhoneVO randPhoneVO() {
		return aPhone(randPref(), randNum());
	}

	static String randNum() {
		return RandomStringUtils.randomNumeric(9);
	}

	static String randPref() {
		return RandomStringUtils.randomNumeric(2);
	}


}
