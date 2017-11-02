package greboreda.agendafx.persistence.mappers;

import greboreda.agendafx.domain.phone.Phone;
import greboreda.agendafx.persistence.vo.PhoneVO;

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

}
