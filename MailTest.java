import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDemo {
	static String from = "from@gmail.com";
	
	public static void main(String a[]){

		String to = "to@gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,"from-gmail-password");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("From Java Application");
			message.setText("Dear Mail Crawler," +
					"\n\n This is auto genereated mail from java program!");

			Transport.send(message);

			System.out.println("Mail Sent Successfully..");

		} catch (MessagingException e) {
			System.out.println("Mail sending failure.. "+e);
			//throw new RuntimeException(e);
		}
	}
}


