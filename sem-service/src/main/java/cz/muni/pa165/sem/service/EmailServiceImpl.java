package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.configuration.EmailConfiguration;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.utils.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Matej Majdis
 */
@Service
public class EmailServiceImpl implements EmailService{

	private Session session;

	@Autowired
	private EmailConfiguration emailConfiguration;

	@PostConstruct
	private void init() {

		Properties props = new Properties();
		props.put("mail.smtp.host", emailConfiguration.getHost());
		props.put("mail.smtp.socketFactory.port", emailConfiguration.getSocketFactoryPort());
		props.put("mail.smtp.socketFactory.class", emailConfiguration.getSocketFactoryClass());
		props.put("mail.smtp.auth", emailConfiguration.getAuth());
		props.put("mail.smtp.port", emailConfiguration.getPort());

		session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailConfiguration.getUsername(), emailConfiguration.getPassword());
					}
				}
		);
	}

	@Override
	public boolean sendMessage(String subject, String body, String recipients) {
		return sendMessage(subject, body, recipients, emailConfiguration.getAddress(), emailConfiguration.getPersonal());
	}

	@Override
	public boolean sendMessage(String subject, String body, String recipients, String from, String personal) {

		if (subject == null || subject.isEmpty() || recipients == null || recipients.isEmpty() || from == null) {
			return false;
		}

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from, personal));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.setSubject(subject);
			message.setText(body);

			return send(message);
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean sendMessage(Message message) {
		try {
			return send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean sendInvitationMessage(Invitation invitation) {
		final String subject = "SEM Invitation";
		final String body = MessageGenerator.generateInvitationBody(invitation);

		return sendMessage(subject, body, invitation.getInvitee().getEmail());
	}

	private boolean send(Message message) throws MessagingException {
		if (message == null) {
			return false;
		}
		Transport.send(message);

		return true;
	}
}
