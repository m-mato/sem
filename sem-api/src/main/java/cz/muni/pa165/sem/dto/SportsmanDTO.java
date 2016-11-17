package cz.muni.pa165.sem.dto;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matej Majdis
 */
public class SportsmanDTO {

	private Long id;

	private String name;

	private String surname;

	private Calendar birthDate;

	private String email;

	private String password;

	private Set<EventDTO> events = new HashSet<>();

	private Set<InvitationDTO> invitations = new HashSet<>();

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

	public Set<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(Set<EventDTO> events) {
		this.events = events;
	}

	public Set<InvitationDTO> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<InvitationDTO> invitations) {
		this.invitations = invitations;
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
		return "SportsmanDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", birthDate=" + birthDate +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", events=" + events +
				", invitations=" + invitations +
				'}';
	}
}
