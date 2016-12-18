package cz.muni.pa165.sem.dto;

import cz.muni.pa165.sem.utils.InvitationState;

import javax.validation.constraints.NotNull;

/**
 * @author Kamil Triscik
 */
public class InvitationCreateDTO {

    @NotNull
    private EventDTO event;

    @NotNull
    private SportsmanDTO invitee;

    @NotNull
    private InvitationState state;

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public SportsmanDTO getInvitee() {
        return invitee;
    }

    public void setInvitee(SportsmanDTO invitee) {
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
        return "InvitationDTO{" +
                "event=" + event +
                ", invitee=" + invitee +
                ", state=" + state +
                '}';
    }
    
}
