package cz.muni.pa165.sem.service.facade;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.facade.InvitationFacade;
import cz.muni.pa165.sem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kamil Triscik
 */
@Service
@Transactional
public class InvitationFacadeImpl implements InvitationFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private InvitationService invitationService;

    @Override
    public InvitationDTO invite(long eventId, long inviteeId) {
        Invitation invitation = invitationService.invite(eventId, inviteeId);
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO invite(EventDTO event, SportsmanDTO invitee) {
        Invitation invitation = invitationService.invite(
                beanMappingService.mapTo(event, Event.class),
                beanMappingService.mapTo(invitee, Sportsman.class));
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO invite(InvitationCreateDTO invitationCreate) {
        Invitation invitation = invitationService.invite(
                beanMappingService.mapTo(invitationCreate.getEvent(), Event.class),
                beanMappingService.mapTo(invitationCreate.getInvitee(), Sportsman.class));
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO accept(InvitationUpdateDTO invitationUpdate) {
        Invitation invitation = invitationService.accept(beanMappingService.mapTo(invitationUpdate, Invitation.class));
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO simpleAccept(InvitationUpdateDTO invitationUpdate) {
        Invitation invitation = invitationService.accept(beanMappingService.mapTo(invitationUpdate, Invitation.class));
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO decline(InvitationUpdateDTO invitationUpdate) {
        Invitation invitation = invitationService.decline(beanMappingService.mapTo(invitationUpdate, Invitation.class));
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO findById(Long id) {
        Invitation invitation = invitationService.findById(id);
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public InvitationDTO findByEventAndInvitee(EventDTO event, SportsmanDTO invitee) {
        Invitation invitation = invitationService.findByEventAndInvitee(
                beanMappingService.mapTo(event, Event.class),
                beanMappingService.mapTo(invitee, Sportsman.class));
        return beanMappingService.mapTo(invitation, InvitationDTO.class);
    }

    @Override
    public List<InvitationDTO> findByEvent(EventDTO event) {
        List<Invitation> invitations = invitationService.findByEvent(beanMappingService.mapTo(event, Event.class));
        return beanMappingService.mapTo(invitations, InvitationDTO.class);
    }

    @Override
    public List<InvitationDTO> findByInvitee(SportsmanDTO invitee) {
        List<Invitation> invitations = invitationService.findByInvitee(beanMappingService.mapTo(invitee, Sportsman.class));
        return beanMappingService.mapTo(invitations, InvitationDTO.class);

    }

    @Override
    public List<InvitationDTO> findAll() {
        List<Invitation> invitations = invitationService.findAll();
        return beanMappingService.mapTo(invitations, InvitationDTO.class);
    }
}
