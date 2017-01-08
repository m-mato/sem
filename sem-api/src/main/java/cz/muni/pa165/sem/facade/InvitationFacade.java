package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.*;

import java.util.List;

/**
 * @author Kamil Triscik
 */
public interface InvitationFacade {

    InvitationDTO invite(long eventId, long inviteeId);

    InvitationDTO invite(EventDTO event, SportsmanDTO invitee);

    InvitationDTO invite(InvitationCreateDTO invitationCreate);

    InvitationDTO accept(InvitationUpdateDTO invitationUpdate);

    InvitationDTO simpleAccept(InvitationUpdateDTO invitationUpdate);

    InvitationDTO decline(InvitationUpdateDTO invitationUpdate);

    InvitationDTO findById(Long id);

    InvitationDTO findByEventAndInvitee(EventDTO event, SportsmanDTO invitee);

    List<InvitationDTO> findByEvent(EventDTO event);

    List<InvitationDTO> findByInvitee(SportsmanDTO invitee);

    List<InvitationDTO> findAll();

}
