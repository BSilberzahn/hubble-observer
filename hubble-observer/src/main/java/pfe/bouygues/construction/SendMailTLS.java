package pfe.bouygues.construction;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMailTLS {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	public static void run(String[] destinataire, String objet, String texte) {

		final String username = "projet.ecom.l3@gmail.com";
		final String from = "HubbleReminder@gmail.com";
		final String password = "projetecom";

		Properties props = new Properties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");

//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");

		//		Session session = Session.getInstance(props,
		//				new javax.mail.Authenticator() {
		//			protected PasswordAuthentication getPasswordAuthentication() {
		//				return new PasswordAuthentication(username, password);
		//			}
		//		});
		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));

//		Session session = Session.getInstance(props,
//				  new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username, password);
//					}
//				  });
		try {

//			Address[] addr = new Address[destinataire.length];
//			for (int i=0;i<destinataire.length;i++) {
//				addr[i]=InternetAddress.parse(destinataire[i])[0];
//			}
			Address fromAddress = new InternetAddress(from);
			Address toAddress = new InternetAddress("benjamin.silberzahn@gmail.com");
			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(username));
			message.setFrom(fromAddress);
	        message.setRecipient(Message.RecipientType.TO, toAddress);
//			message.setRecipients(Message.RecipientType.TO,
//					toAddress);//InternetAddress.parse(destinataire)
			message.setSubject(objet);
			message.setText(texte);

			/*
			//ou corps txt ou oice jointe
			// creation et ajout de la piece jointe
			MimeBodyPart m2=new MimeBodyPart();
			FileDataSource source = new FileDataSource("c:/fichier.txt");
			m2.setDataHandler(new DataHandler(source));
			m2.setFileName(source.getName());
			Multipart mp = new MimeMultipart();

		      mp.addBodyPart(m2);
		      message.setContent(mp);
			 */





			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static void generateAndSendEmail() throws AddressException, MessagingException {
		 
		//Step1		
				System.out.println("\n 1st ===> setup Mail Server Properties..");
				mailServerProperties = System.getProperties();
		        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
				mailServerProperties.put("mail.smtp.port", "587");
				mailServerProperties.put("mail.smtp.auth", "true");
				mailServerProperties.put("mail.smtp.starttls.enable", "true");
				mailServerProperties.put("mail.debug", "true");
				System.out.println("Mail Server Properties have been setup successfully..");
		 
		//Step2		
				System.out.println("\n\n 2nd ===> get Mail Session..");
//				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				getMailSession = Session.getInstance(mailServerProperties, new GMailAuthenticator("projet.ecom.l3", "projetecom1"));
				generateMailMessage = new MimeMessage(getMailSession);
				generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("benjamin.silberzahn@gmail.com"));
				generateMailMessage.setSubject("Greetings from Crunchify..");
				String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
				generateMailMessage.setContent(emailBody, "text/html");
				System.out.println("Mail Session has been created successfully..");
		 
		//Step3		
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
//				Transport transport = getMailSession.getTransport("smtp");
				
				// Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
//				transport.connect("smtp.gmail.com", "projet.ecom.l3@gmail.com", "projetecom1");
//				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
//				transport.close();
				
				Transport.send(generateMailMessage);
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




