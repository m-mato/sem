package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Matej Majdis
 */
@Repository
public class InvitationDAOImpl implements InvitationDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Invitation invitation) {

		if (invitation == null) {
			throw new IllegalArgumentException("Invitation is null");
		}
		entityManager.persist(invitation);
	}

	@Override
	public Invitation findById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("id is null");
		}
		return entityManager.find(Invitation.class, id);
	}

	@Override
	public List<Invitation> findByEvent(Event event) {

		if (event == null) {
			throw new IllegalArgumentException("event is null");
		}
		try {
			return entityManager.createQuery("select i from Invitation i where event = :event",
					Invitation.class).setParameter("event", event).getResultList();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public List<Invitation> findByInvitee(Sportsman invitee) {

		if (invitee == null) {
			throw new IllegalArgumentException("invitee is null");
		}
		try {
			return entityManager.createQuery("select i from Invitation i where invitee = :invitee",
					Invitation.class).setParameter("invitee", invitee).getResultList();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public Invitation findByEventAndInvitee(Event event, Sportsman invitee) {

		if (event == null) {
			throw new IllegalArgumentException("event is null");
		}
		if (invitee == null) {
			throw new IllegalArgumentException("invitee is null");
		}
		List<Invitation> invitations;
		try {
			invitations = entityManager.createQuery("select i from Invitation i where event = :event and invitee = :invitee",
					Invitation.class)
					.setParameter("event", event)
					.setParameter("invitee", invitee)
					.getResultList();
		} catch (NoResultException nrf) {
			return null;
		}

		if(invitations == null || invitations.isEmpty()) {
			return null;
		}

		if(invitations.size() > 1)  {
			throw new IllegalStateException("Multiple Invitations found for same Event and Sportsman");
		}

		return invitations.get(0);
	}

	@Override
	public void update(Invitation invitation) {

		if (invitation == null) {
			throw new IllegalArgumentException("Invitation is null");
		}
		entityManager.merge(invitation);
	}

	@Override
	public void delete(Invitation invitation) {

		if (invitation == null) {
			throw new IllegalArgumentException("Invitation is null");
		}
		entityManager.remove(invitation);
	}

	@Override
	public List<Invitation> findAll() {

		Query query = entityManager.createQuery("SELECT i FROM Invitation i");
		return query.getResultList();
	}
}
