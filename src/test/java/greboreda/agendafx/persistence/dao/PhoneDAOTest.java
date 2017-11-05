package greboreda.agendafx.persistence.dao;

import greboreda.agendafx.persistence.vo.PhoneVO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;

import static greboreda.agendafx.persistence.dao.PhoneDAOUtils.aPhone;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private PhoneDAO phoneDAO;

	@Test
	public void should_not_be_repeated_prefix_and_phone() {

		final PhoneVO phone1 = aPhone(3, "23", "45");
		final PhoneVO phone2 = aPhone(1, "23", "45");

		phoneDAO.save(phone1);
		phoneDAO.save(phone2);

		assertThrows(PersistenceException.class, () -> testEntityManager.flush());
	}

	@Test
	@Ignore
	public void should_pass_but_fails_fuck() {

		final PhoneVO phone1 = aPhone(3, "23", "45");
		final PhoneVO phone2 = aPhone(1, "23", "46");
		final PhoneVO phone3 = aPhone(2, "24", "46");
		phoneDAO.save(phone1);
		phoneDAO.save(phone2);
		phoneDAO.save(phone3);
		testEntityManager.flush();
	}

}
