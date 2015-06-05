package com.pb.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	
	public static void sendMail(String recipients, String badgeLink, String text) {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "s.tyagi@ost.orcid.org";//
		final String password = "Tyag!1011";
		try {
			Session session = Session.getDefaultInstance(props,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress("info@paperbadger.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipients, false));
			msg.setSubject("PaperBadger- Your Badge is ready!");
			msg.setText(text);
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
