package pfe.bouygues.construction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

public class Email {

private static String USER_NAME = "projet.ecom.l3";  // GMail user name (just the part before "@gmail.com")
private static String PASSWORD = "projetecom1"; // GMail password


public static void main(String[] args) {
	
	String RECIPIENT = "benjamin.silberzahn@gmail.com";

    String[] to = { RECIPIENT }; // list of recipient email addresses

//    sendFromGMail(to, "");
}

public static void sendFromGMail(String[] to, ArrayList<File> pathList) {
    String from = USER_NAME;
	String pass = PASSWORD;
	String subject = "HubbleReminder - Calendrier";
    String body = "";
	
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
        message.setText(body);


        
        MimeBodyPart m2=new MimeBodyPart();
		FileDataSource source = new FileDataSource("fiches/calendar.ics");
		m2.setDataHandler(new DataHandler(source));
		m2.setFileName(source.getName());
		Multipart mp = new MimeMultipart();

	    mp.addBodyPart(m2);
	    
	    
	    for (File file : pathList) {
	    	System.out.println(file.getPath());
//	    	MimeBodyPart m3=new MimeBodyPart();
//			FileDataSource source2 = new FileDataSource(file.getPath());
//			m3.setDataHandler(new DataHandler(source2));
//			m3.setFileName(source2.getName());
	    	
	    	FileDataSource source2 = new FileDataSource(file.getPath());
	    	
	    	MimeBodyPart attachment= new MimeBodyPart();
	        ByteArrayDataSource ds = null;
			try {
				ds = new ByteArrayDataSource(new FileInputStream(file.getPath()), "application/pdf");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        attachment.setDataHandler(new DataHandler(ds));
	        attachment.setFileName(file.getPath());
		    mp.addBodyPart(attachment);
		}
	    
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
