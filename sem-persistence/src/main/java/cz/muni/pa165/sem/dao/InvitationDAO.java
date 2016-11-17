package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface InvitationDAO {

	void create(Invitation invitation);

	Invitation findById(Long id);

	List<Invitation> findByEvent(Event event);

	List<Invitation> findByInvitee(Sportsman invitee);

	Invitation findByEventAndInvitee(Event event, Sportsman invitee);

	void update(Invitation invitation);

	void delete(Invitation invitation);

	List<Invitation> findAll();
}
