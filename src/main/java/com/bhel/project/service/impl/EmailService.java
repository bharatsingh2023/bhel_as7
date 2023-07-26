package com.bhel.project.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	static final Logger logger = Logger.getLogger(EmailService.class);

	public boolean sendEmail(String subject, String message, String to) {

		boolean f = false;

		String from = "rashmi.mishra@beas.co.in";

		String host = "mail.beas.co.in";

		Properties properties = System.getProperties();
		logger.info("PROPERTY" + properties);

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.enable", "false");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, "beas$beas123");

			}
		});

		session.setDebug(true);

		MimeMessage m = new MimeMessage(session);

		try {

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);

			m.setContent(message, "text/html");

			Transport.send(m);

			logger.info("sent success....");
			f = true;

		} catch (Exception e) {
			logger.error("SendToMail error", e);
		}
		return f;

	}
}
