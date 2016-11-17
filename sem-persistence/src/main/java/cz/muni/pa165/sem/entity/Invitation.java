package cz.muni.pa165.sem.entity;

import cz.muni.pa165.sem.utils.InvitationState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Matej Majdis
 */
@Entity
@Table(name = "invitation")
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "event")
	private Event event;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "invitee")
	private Sportsman invitee;

	@NotNull
	@Column(name = "state")
	private InvitationState state;

	public Invitation() {
	}

	public Invitation(Invitation invitation) {
		this.id = invitation.getId();
		this.event = invitation.getEvent();
		this.invitee = invitation.getInvitee();
		this.state = invitation.getState();
	}

	public Long getId() {
		return id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Sportsman getInvitee() {
		return invitee;
	}

	public void setInvitee(Sportsman invitee) {
		this.invitee = invitee;
	}

	public InvitationState getState() {
		return state;
	}

	public void setState(InvitationState state) {
		this.state = state;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Invitation)) return false;

		Invitation that = (Invitation) o;

		if (!getEvent().equals(that.getEvent())) return false;
		if (!getInvitee().equals(that.getInvitee())) return false;
		return getState() == that.getState();

	}

	@Override
	public int hashCode() {
		int result = getEvent().hashCode();
		result = 31 * result + getInvitee().hashCode();
		result = 31 * result + getState().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Invitation{" +
				"id=" + id +
				", event=" + event +
				", invitee=" + invitee +
				", state=" + state +
				'}';
	}
}
