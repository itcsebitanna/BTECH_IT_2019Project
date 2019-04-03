/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeSPHR;

/**
 *
 * @author User
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class sms2
{
String d_email = "securesharing.sesphr@gmail.com",
d_password = "Prosecurecord10!",
d_host = "smtp.gmail.com",
d_port = "465",
m_to = "rashokkumar@gmail.com",
m_subject = "Testing",
m_text = "Hey, this is the testing email.";

public sms2()
{
Properties props = new Properties();
props.put("mail.smtp.user", d_email);
props.put("mail.smtp.host", d_host);
props.put("mail.smtp.port", d_port);
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.auth", "true");
//props.put("mail.smtp.debug", "true");
props.put("mail.smtp.socketFactory.port", d_port);
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");

SecurityManager security = System.getSecurityManager();

try
{
Authenticator auth = new SMTPAuthenticator();
Session session = Session.getInstance(props, auth);
//session.setDebug(true);

MimeMessage msg = new MimeMessage(session);
msg.setText(m_text);
msg.setSubject(m_subject);
msg.setFrom(new InternetAddress(d_email));
msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
Transport.send(msg);
System.out.println("Done!");
}
catch (Exception mex)
{
mex.printStackTrace();
}
}

public static void main(String[] args)
{
sms2 blah = new sms2();
}

private class SMTPAuthenticator extends javax.mail.Authenticator
{
public PasswordAuthentication getPasswordAuthentication()
{
return new PasswordAuthentication(d_email, d_password);
}
}
}