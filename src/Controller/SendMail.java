package Controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import Model.DowntimeModel;

import java.util.Date;
import java.util.Properties;

public class SendMail {
	private static Session SendingMail() {
		// Sender's email ID needs to be mentioned
		String fromEmail = Base.smtpuser;
		String password = Base.smtppassword;

		// Get system properties
		Properties props = new Properties();

		// Setup mail server
		props.put("mail.smtp.host", Base.smtphostname); // SMTP Host
		props.put("mail.smtp.port", Base.smtpport); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", Base.smtpusetls); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		// Get the default Session object.
		return Session.getInstance(props, auth);

	}

	public static void SendEmail(DowntimeModel downtime) {
		// Get the default Session object.
		Session session = SendingMail();
		
		String subject = "Ново непродуктивно време No " + String.valueOf(downtime.getNumber()) + " - " + BaseMethods.ActionName(downtime);
		String body = "Въведено е ново непродуктивно време No " + String.valueOf(downtime.getNumber()) + " - " + BaseMethods.ActionName(downtime) + "." + "\r\n" + 
				"Цех: " + downtime.getWorkshop() + "." + "\r\n" +
				"Участък/машина: " + downtime.getFieldMachine() + "." + "\r\n" +
				"Описание: " + downtime.getDescription() + "." + "\r\n" + 
				"Въведено от: " + downtime.getAuthor();

		for (String email : Base.recipientsList) {

			try {
				MimeMessage msg = new MimeMessage(session);
				// set message headers
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encoding", "8bit");

				// msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

				// msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

				msg.setSubject(subject, "UTF-8");

				msg.setText(body, "UTF-8");

				msg.setSentDate(new Date());

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
				// System.out.println("Message is ready");
				Transport.send(msg);

				// System.out.println("EMail Sent Successfully!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
