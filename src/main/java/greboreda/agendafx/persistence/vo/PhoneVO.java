package greboreda.agendafx.persistence.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(name = "PHONE", uniqueConstraints = {
		@UniqueConstraint(
				name = "UNIQUE_COMPLETE_PHONE",
				columnNames = {"NUMBER", "PREFIX"}
		)
})
public class PhoneVO {

	@Id
	@SequenceGenerator(name = "PHONE_SEQ", sequenceName = "PHONE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONE_SEQ")
	private Integer id;
	@NotBlank
	private String number;
	@NotBlank
	private String prefix;
	@ManyToMany
	@JoinTable(name = "PERSONPHONE",
			joinColumns = {@JoinColumn(name = "PERSONID")},
			inverseJoinColumns = {@JoinColumn(name = "PHONEID")}
	)
	private Set<PersonVO> owners;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Set<PersonVO> getOwners() {
		return owners;
	}

	public void setOwners(Set<PersonVO> owners) {
		this.owners = owners;
	}
}
