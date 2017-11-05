package greboreda.agendafx.business.phone;

import greboreda.agendafx.business.person.PersonFinder;
import greboreda.agendafx.business.phone.exceptions.SavePhoneError;
import greboreda.agendafx.business.phone.exceptions.SavePhoneException;
import greboreda.agendafx.domain.phone.PhoneOwning;
import greboreda.agendafx.domain.phone.PhoneToSave;
import greboreda.agendafx.persistence.dao.PhoneDAO;
import greboreda.agendafx.persistence.vo.PersonVO;
import greboreda.agendafx.persistence.vo.PhoneVO;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhoneSaverTest {

	private final PersonFinder personFinder = mock(PersonFinder.class);
	private final PhoneOwningChecker phoneOwningChecker = mock(PhoneOwningChecker.class);
	private final PhoneDAO phoneDAO = mock(PhoneDAO.class);

	private PhoneSaver phoneSaver = new PhoneSaver(personFinder, phoneOwningChecker, phoneDAO);

	private static final PhoneToSave A_PHONE_TO_SAVE = PhoneToSave.create()
			.forPersonWithId(1)
			.withPrefix("34")
			.withNumber("666666666");


	@Test
	public void when_saving_phone_it_should_not_me_null() {
		assertThrows(NullPointerException.class, () -> phoneSaver.savePhone(null));
	}

	@Test
	public void should_exist_person_when_saving_phone() {
		when(personFinder.existsPersonWithId(A_PHONE_TO_SAVE.personId))
				.thenReturn(false);

		final SavePhoneException thrown = assertThrows(SavePhoneException.class,
				() -> phoneSaver.savePhone(A_PHONE_TO_SAVE));

		assertThat(thrown.getSavePhoneError(), is(SavePhoneError.PERSON_NOT_EXISTS));
	}

	@Test
	public void should_not_be_owned_by_given_person_when_saving_phone() {

		when(personFinder.existsPersonWithId(A_PHONE_TO_SAVE.personId))
				.thenReturn(true);

		when(phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE))
				.thenReturn(PhoneOwning.create()
						.ownedByGivenPerson(true)
						.ownedByMorePersons(false));

		final SavePhoneException thrown = assertThrows(SavePhoneException.class,
				() -> phoneSaver.savePhone(A_PHONE_TO_SAVE));

		assertThat(thrown.getSavePhoneError(), is(SavePhoneError.PHONE_ALREADY_OWNED_FOR_GIVEN_PERSON));
	}

	@Test
	public void should_not_be_owned_by_another_person_when_saving_phone() {

		when(personFinder.existsPersonWithId(A_PHONE_TO_SAVE.personId))
				.thenReturn(true);

		when(phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE))
				.thenReturn(PhoneOwning.create()
						.ownedByGivenPerson(false)
						.ownedByMorePersons(true));

		final SavePhoneException thrown = assertThrows(SavePhoneException.class,
				() -> phoneSaver.savePhone(A_PHONE_TO_SAVE));

		assertThat(thrown.getSavePhoneError(), is(SavePhoneError.PHONE_ALREADY_OWNED_FOR_MORE_PERSONS));
	}

	@Test
	public void should_save_phone_when_is_not_owned_for_any_person() throws Exception {

		when(personFinder.existsPersonWithId(A_PHONE_TO_SAVE.personId))
				.thenReturn(true);

		when(phoneOwningChecker.checkOwningToSave(A_PHONE_TO_SAVE))
				.thenReturn(PhoneOwning.create()
						.ownedByGivenPerson(false)
						.ownedByMorePersons(false));

		phoneSaver.savePhone(A_PHONE_TO_SAVE);

		final ArgumentCaptor<PhoneVO> captor = ArgumentCaptor.forClass(PhoneVO.class);
		verify(phoneDAO).save(captor.capture());
		final PhoneVO savedPhone = captor.getValue();
		assertAll(
				() -> assertThat(savedPhone.getNumber(), is(A_PHONE_TO_SAVE.number)),
				() -> assertThat(savedPhone.getPrefix(), is(A_PHONE_TO_SAVE.prefix)),
				() -> assertThat(savedPhone.getOwners().stream()
							.map(PersonVO::getId)
							.collect(toList()), contains(A_PHONE_TO_SAVE.personId))
		);
	}

}