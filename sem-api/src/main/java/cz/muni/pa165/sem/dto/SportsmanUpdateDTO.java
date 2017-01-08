package cz.muni.pa165.sem.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Calendar;

/**
 * @author Matej Majdis
 */
public class SportsmanUpdateDTO {

	@NotNull
	private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String surname;

	@NotNull
	@Past
	private Calendar birthDate;

	@NotNull
	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotBlank
	private String password;

	@NotNull
	private Boolean isManager;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean manager) {
		isManager = manager;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SportsmanDTO)) return false;

		SportsmanDTO sportsman = (SportsmanDTO) o;

		if (getId() != null ? !getId().equals(sportsman.getId()) : sportsman.getId() != null) return false;
		if (!getName().equals(sportsman.getName())) return false;
		if (!getSurname().equals(sportsman.getSurname())) return false;
		if (!getBirthDate().equals(sportsman.getBirthDate())) return false;
		if (!getEmail().equals(sportsman.getEmail())) return false;
		if (!getIsManager().equals(sportsman.getIsManager())) return false;
		return getPassword().equals(sportsman.getPassword());

	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + getName().hashCode();
		result = 31 * result + getSurname().hashCode();
		result = 31 * result + getBirthDate().hashCode();
		result = 31 * result + getEmail().hashCode();
		result = 31 * result + getPassword().hashCode();
		result = 31 * result + getIsManager().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "SportsmanUpdateDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", birthDate=" + birthDate +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", is manager='" + isManager + '\'' +
				'}';
	}
}
