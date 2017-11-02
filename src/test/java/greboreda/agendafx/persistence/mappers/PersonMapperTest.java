package greboreda.agendafx.persistence.mappers;

import greboreda.agendafx.domain.person.Person;
import greboreda.agendafx.persistence.vo.PersonVO;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PersonMapperTest {

	@Test
	public void should_map_all_person_fields() {

		final Person person = Person.create()
				.withId(123)
				.withFirstName("John")
				.withLastName("Doe")
				.build();

		final PersonVO personVO = PersonMapper.mapToVO(person);

		assertAll(
				() -> assertThat(personVO.getId(), is(person.getId())),
				() -> assertThat(personVO.getFirstName(), is(person.getFirstName())),
				() -> assertThat(personVO.getLastName(), is(person.getLastName()))
		);
	}

}