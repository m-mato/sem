package cz.muni.pa165.sem.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
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

	@NotNull
	@Column(name = "is_manager")
	private Boolean isManager;

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

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean manager) {
		isManager = manager;
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
	public boolean equals(Object object) {
		if ((object == null) || (!(object instanceof Sportsman))) {
			return false;
		}

		if (this == object) {
			return true;
		}

		final Sportsman sportsman = (Sportsman) object;

		if (!Objects.equals(this.getName(), sportsman.getName())) return false;
		if (!Objects.equals(this.getSurname(), sportsman.getSurname())) return false;
		if (!Objects.equals(this.getBirthDate(), sportsman.getBirthDate())) return false;
		if (!Objects.equals(this.getEmail(), sportsman.getEmail())) return false;
		if (!Objects.equals(this.getIsManager(), sportsman.getIsManager())) return false;
		return Objects.equals(this.getPassword(), sportsman.getPassword());
	}

	@Override
	public int hashCode() {
		final int prime = 53;
		int hashCode = 7;

		hashCode = prime * hashCode + ((this.name == null) ? 0 : this.name.hashCode());
		hashCode = prime * hashCode + ((this.surname == null) ? 0 : this.surname.hashCode());
		hashCode = prime * hashCode + ((this.birthDate == null) ? 0 : this.birthDate.hashCode());
		hashCode = prime * hashCode + ((this.email == null) ? 0 : this.email.hashCode());
		hashCode = prime * hashCode + ((this.password == null) ? 0 : this.password.hashCode());
		hashCode = prime * hashCode + ((this.isManager == null) ? 0 : this.isManager.hashCode());

		return hashCode;
	}

	@Override
	public String toString() {
		return "Sportsman:" +
				"{" + ", " +
				"id:" + this.id + ", " +
				"name:" + this.name + ", " +
				"surname:" + this.surname + ", " +
				"birthDate:" + this.birthDate + ", " +
				"is manager:" + this.isManager + ", " +
				"email:" + this.email +
				"}";
	}
}
