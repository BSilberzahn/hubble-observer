package pfe.bouygues.construction;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	private static String USER_NAME = "projet.ecom.l3";  // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "projetecom1"; // GMail password


	public static void main(String[] args) {

		String RECIPIENT = "benjamin.silberzahn@gmail.com";

		String[] to = { RECIPIENT }; // list of recipient email addresses

		sendFromGMail(to);
	}

	public static void sendFromGMail(String[] to) {
		String from = USER_NAME;
		String pass = PASSWORD;
		String subject = "HubbleReminder - Calendrier";
		String body = "Bonjour,\n\n\nVous venez de renseigner les dates de vos jalons de projet sur notre site HubbleReminder. Félicitations !\n\nVous pouvez trouver en pièce jointe de ce mail automatique votre calendrier de projet. Pour l’ajouter à vos autres calendriers Outlook, c’est très simple : il vous suffit de double-cliquer dessus, puis de cliquer sur la case « oui » de la boîte de dialogue apparaissant à l’écran.\n\nVotre nouveau calendrier est désormais consultable dans vos calendriers, sous l’onglet « Calendrier » dans le bandeau de gauche.\n\nVous trouverez en pièce jointe des évènements un lien vers les fiches de contrôles à faire pour les jalons en question.\n\n\nBons contrôles avec HubbleReminder !\n\n\n\nL’équipe HubbleReminder";


		Properties props = System.getProperties();
		String host = "smtp.gmail.com";

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.user", "HubbleReminder@gmail.com");
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");


		//    Session session = Session.getDefaultInstance(props);
		Session session = Session.getInstance(props, new GMailAuthenticator(USER_NAME, PASSWORD));
		MimeMessage message = new MimeMessage(session);

		try {


			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i = 0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}



			message.setSubject(subject);
			
			MimeBodyPart bodyContent = new MimeBodyPart();
			bodyContent.setText(body);



			MimeBodyPart m2=new MimeBodyPart();
			FileDataSource source = new FileDataSource("fiches/calendar.ics");
			m2.setDataHandler(new DataHandler(source));
			m2.setFileName(source.getName());
			Multipart mp = new MimeMultipart();

			mp.addBodyPart(m2);
			mp.addBodyPart(bodyContent);


			message.setContent(mp);





			Transport transport = session.getTransport("smtp");


			transport.connect(host, from, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
	}
} 
class GMailAuthenticator extends Authenticator {
	String user;
	String pw;
	public GMailAuthenticator (String username, String password)
	{
		super();
		this.user = username;
		this.pw = password;
	}
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(user, pw);
	}
}
