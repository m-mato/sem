package cz.muni.pa165.sem.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matej Majdis
 */
@Entity
@Table(name = "sportsman")
public class Sportsman {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name")
	private String name;

	@NotBlank
	@Column(name = "surname")
	private String surname;

	@NotNull
	@Column(name = "birth_date")
	private Calendar birthDate;

	@NotBlank
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "password")
	private String password;

	@ManyToMany(mappedBy = "participants")
	private Set<Event> events = new HashSet<>();

	public Long getId() {
		return id;
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

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Sportsman)) return false;

		Sportsman sportsman = (Sportsman) o;

		if (!getId().equals(sportsman.getId())) {
			return false;
		}
		if (getName() != null ? !getName().equals(sportsman.getName()) : sportsman.getName() != null) {
			return false;
		}
		if (getSurname() != null ? !getSurname().equals(sportsman.getSurname()) : sportsman.getSurname() != null) {
			return false;
		}
		if (getBirthDate() != null ? !getBirthDate().equals(sportsman.getBirthDate()) : sportsman.getBirthDate() != null) {
			return false;
		}
		if (getEmail() != null ? !getEmail().equals(sportsman.getEmail()) : sportsman.getEmail() != null) {
			return false;
		}
		return getPassword() != null ? getPassword().equals(sportsman.getPassword()) : sportsman.getPassword() == null;

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
		result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		return result;
	}
}
