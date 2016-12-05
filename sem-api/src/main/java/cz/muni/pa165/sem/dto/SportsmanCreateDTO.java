package cz.muni.pa165.sem.dto;

import java.util.Calendar;

/**
 * @author Matej Majdis
 */
public class SportsmanCreateDTO {

	private String name;

	private String surname;

	private Calendar birthDate;

	private String email;

	private String password;

	private Boolean isManager;

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

		if (!getName().equals(sportsman.getName())) return false;
		if (!getSurname().equals(sportsman.getSurname())) return false;
		if (!getBirthDate().equals(sportsman.getBirthDate())) return false;
		if (!getEmail().equals(sportsman.getEmail())) return false;
		if (!getIsManager().equals(sportsman.getIsManager())) return false;
		return getPassword().equals(sportsman.getPassword());

	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
		result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		result = 31 * result + (getIsManager() != null ? getIsManager().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SportsmanCreateDTO{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", birthDate=" + birthDate +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", is manager='" + isManager + '\'' +
				'}';
	}
}
