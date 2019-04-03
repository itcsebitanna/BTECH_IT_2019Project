/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeSPHR;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

                static String username = "securesharing.sesphr@gmail.com";
		static String password = "Prosecurecord10!";
                static String host = "74.125.24.108";
                static String str,to,sub,msg;
               
	   
             public static void main(String[] args) {

		  encrypt(to,sub,msg);

		
              
	}
              public static String encrypt(String to,String sub,String msg){
                Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host",host );
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("securesharing.sesphr@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(sub);
			message.setText(msg);

			Transport.send(message);

			System.out.println("Done");
                        str="Mail sent successfully";
                        return str;

		} catch (MessagingException e) {
		       str="Mail not sent!";
                       System.out.println(str);
                       return str;
		}
              
              }
             

}