package greboreda.agendafx.business.phone;

import greboreda.agendafx.domain.phone.PhoneOwning;
import greboreda.agendafx.domain.phone.PhoneToSave;
import greboreda.agendafx.persistence.dao.PhoneDAO;
import greboreda.agendafx.persistence.vo.PersonVO;
import greboreda.agendafx.persistence.vo.PhoneVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneOwningCheckerTest {

	private PhoneDAO phoneDAO;

	private PhoneOwningChecker phoneOwningChecker;

	private static final PhoneToSave A_PHONE_TO_SAVE = PhoneToSave.create()
			.forPersonWithId(1)
			.withPrefix("34")
			.withNumber("666666666");

	@Before
	public void setUp() {
		phoneDAO = mock(PhoneDAO.class);
		phoneOwningChecker = new PhoneOwningChecker(phoneDAO);
	}

	@Test
	public void when_checking_phone_to_save_it_should_not_be_null() {
		assertThrows(NullPointerException.class, () -> phoneOwningChecker.checkOwningToSave(null));
	}

	@Test
	public void should_not_have_owners_when_phone_not_found() {

		when(phoneDAO.findPhoneByPrefixAndNumber(A_PHONE_TO_SAVE.prefix, A_PHONE_TO_SAVE.number))
				.thenReturn(null);

		final PhoneOwning phoneOwning = phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE);

		assertAll(
				() -> assertThat(phoneOwning.isOwnedByMorePersons(), is(false)),
				() -> assertThat(phoneOwning.isOwnedByGivenPerson(), is(false))
		);
	}

	@Test
	public void should_be_owned_by_given_person_when_found_it_for_that_person() {

		when(phoneDAO.findPhoneByPrefixAndNumber(A_PHONE_TO_SAVE.prefix, A_PHONE_TO_SAVE.number))
				.thenReturn(getPhoneOwnedBy(A_PHONE_TO_SAVE.personId));

		final PhoneOwning phoneOwning = phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE);

		assertAll(
				() -> assertThat(phoneOwning.isOwnedByMorePersons(), is(false)),
				() -> assertThat(phoneOwning.isOwnedByGivenPerson(), is(true))
		);
	}

	@Test
	public void should_be_owned_by_another_person_when_found_it_for_another_person() {

		final PhoneToSave aPhoneToSave = PhoneToSave.create()
				.forPersonWithId(1)
				.withPrefix("34")
				.withNumber("666666666");

		when(phoneDAO.findPhoneByPrefixAndNumber(aPhoneToSave.prefix, aPhoneToSave.number))
				.thenReturn(getPhoneOwnedBy(2));

		final PhoneOwning phoneOwning = phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE);

		assertAll(
				() -> assertThat(phoneOwning.isOwnedByMorePersons(), is(true)),
				() -> assertThat(phoneOwning.isOwnedByGivenPerson(), is(false))
		);
	}

	@Test
	public void should_be_owned_by_given_person_and_another_person_when_found_it_for_both() {

		final PhoneToSave aPhoneToSave = PhoneToSave.create()
				.forPersonWithId(1)
				.withPrefix("34")
				.withNumber("666666666");

		when(phoneDAO.findPhoneByPrefixAndNumber(aPhoneToSave.prefix, aPhoneToSave.number))
				.thenReturn(getPhoneOwnedBy(1,2));

		final PhoneOwning phoneOwning = phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE);

		assertAll(
				() -> assertThat(phoneOwning.isOwnedByMorePersons(), is(true)),
				() -> assertThat(phoneOwning.isOwnedByGivenPerson(), is(true))
		);
	}


	private PhoneVO getPhoneOwnedBy(Integer...personIds) {
		final Set<PersonVO> owners = Arrays.stream(personIds)
				.map(id -> {
					final PersonVO vo = new PersonVO();
					vo.setId(id);
					return vo;
				}).collect(toSet());
		final PhoneVO phoneVO = new PhoneVO();
		phoneVO.setOwners(owners);
		return  phoneVO;
	}
}