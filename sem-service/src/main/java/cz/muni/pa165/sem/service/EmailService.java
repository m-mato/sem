package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Invitation;

import javax.mail.Message;

/**
 * @author Matej Majdis
 */
public interface EmailService {

	boolean sendMessage(String subject, String body, String recipients);

	boolean sendMessage(String subject, String body, String recipients, String from, String personal);

	boolean sendMessage(Message message);

	boolean sendInvitationMessage(Invitation invitation);
}
