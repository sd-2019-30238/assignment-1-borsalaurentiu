package observer;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailObserver implements Observer {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public void setMailServerProperties() {
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", "587");
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true"); //TLS
//		emailProperties.put("mail.smtp.user", "tguvihbyuoin");
//		emailProperties.put("mail.password", "xcvuibhun");
	}

	public void createEmailMessage(ArrayList<String> emailTo) 
			throws AddressException, MessagingException {
		String emailSubject = "Your Books Everywhere";
		String emailBody = "New book available! Look for it!";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (String email: emailTo) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setText(emailBody);
	}

	public void sendMail(ArrayList<String> emailTo) {
		setMailServerProperties();
		try {
			createEmailMessage(emailTo);
		} catch (AddressException e1) {
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		
//		String emailHost = "smtp.gmail.com";
//		String fromUser = "jomleqqwgwag";
//		String fromUserEmailPassword = "wqgegwae";
//
//		Transport transport = null;
//		try {
//			transport = mailSession.getTransport("smtp");
//		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
//		}
//		try {
//			transport.connect(emailHost, fromUser, fromUserEmailPassword);
//			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
//			transport.close();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

		System.out.println("Email sent successfully to: " + emailTo.toString());
	}

}