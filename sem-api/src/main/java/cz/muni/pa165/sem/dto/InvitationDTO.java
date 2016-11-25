package cz.muni.pa165.sem.dto;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.utils.InvitationState;

/**
 * @author Matej Majdis
 */
public class InvitationDTO {

	private Long id;

	private Event event;

	private Sportsman invitee;

	private InvitationState state;

	public Long getId() {
		return id;
	}

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof InvitationDTO)) return false;

		InvitationDTO that = (InvitationDTO) o;

		if (!getId().equals(that.getId())) return false;
		if (!getEvent().equals(that.getEvent())) return false;
		if (!getInvitee().equals(that.getInvitee())) return false;
		return getState() == that.getState();

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getEvent().hashCode();
		result = 31 * result + getInvitee().hashCode();
		result = 31 * result + getState().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "InvitationDTO{" +
				"id=" + id +
				", event=" + event +
				", invitee=" + invitee +
				", state=" + state +
				'}';
	}
}
