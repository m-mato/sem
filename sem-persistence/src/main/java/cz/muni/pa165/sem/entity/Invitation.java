package cz.muni.pa165.sem.entity;

import cz.muni.pa165.sem.utils.InvitationState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

	/**
	 * Needed for testing purposes.
	 */
	public void setId(Long id) {
		this.id = id;
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
	public boolean equals(Object object){
		if ((object == null) || (!(object instanceof Invitation))) {
			return false;
		}

		if (this == object) {
			return true;
		}

		final Invitation that = (Invitation) object;

		if (!Objects.equals(this.getEvent(), that.getEvent())) return false;
		if (!Objects.equals(this.getInvitee(), that.getInvitee())) return false;
		return Objects.equals(this.getState(), that.getState());

	}

	@Override
	public int hashCode() {
		final int prime = 53;
		int hashCode = 7;

		hashCode = prime * hashCode + ((this.event == null) ? 0 : this.event.hashCode());
		hashCode = prime * hashCode + ((this.invitee == null) ? 0 : this.invitee.hashCode());
		hashCode = prime * hashCode + ((this.state == null) ? 0 : this.state.hashCode());

		return hashCode;
	}

	@Override
	public String toString() {
		return "Invitation:" +
					"{" +
						"id:" + this.id + ", " +
						"event:" + this.event + ", " +
						"invitee:" + this.invitee + ", " +
						"state:" + this.state +
					"}";
	}
}
