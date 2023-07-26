
package com.bhel.project.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailSend {
	static final Logger logger = Logger.getLogger(MailSend.class);
	public void sendMail() {

		String to = "rashmi.beas@gmail.com";
		String subject = "Test subject";
		String message = "Test message";

		boolean f = false;

		String from = "rashmi.mishra@beas.co.in";

		String host = "mail.beas.co.in";
		try {

			Properties properties = System.getProperties();
			logger.info("PROPERTY" + properties);

			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(properties, new Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(from, "beas$beas123");

				}
			});

			session.setDebug(true);

			MimeMessage m = new MimeMessage(session);

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);

			m.setText(message);

			Transport.send(m);
			logger.info("sent success....");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
