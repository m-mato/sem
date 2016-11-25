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
	@Column(name = "email", unique = true)
	private String email;

	@NotBlank
	@Column(name = "password")
	private String password;

	@ManyToMany(mappedBy = "participants")
	private Set<Event> events = new HashSet<>();

	@OneToMany(mappedBy = "invitee")
	private Set<Invitation> invitations = new HashSet<>();

	public Long getId() {
		return id;
	}

	/**
	 * Needed for testing purposes.
	 */
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

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<Invitation> invitations) {
		this.invitations = invitations;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Sportsman)) return false;

		Sportsman sportsman = (Sportsman) o;

		if (getId() != null ? !getId().equals(sportsman.getId()) : sportsman.getId() != null) return false;
		if (!getName().equals(sportsman.getName())) return false;
		if (!getSurname().equals(sportsman.getSurname())) return false;
		if (!getBirthDate().equals(sportsman.getBirthDate())) return false;
		if (!getEmail().equals(sportsman.getEmail())) return false;
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
		return result;
	}

	@Override
	public String toString() {
		return "Sportsman{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", birthDate=" + birthDate +
				", email='" + email + '\'' +
				'}';
	}
}
