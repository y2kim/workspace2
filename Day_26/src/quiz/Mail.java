package quiz;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Mail {
	  Mail self = this;
	   private String user;
	   private String password;
	   private String subject;
	   private String content;
	   
	   public Mail(String user, String password, String subject, String content) {
	      this.user = "heyjen94";
	      this.password = "Ksocool0212!";
	      this.subject = subject;
	      this.content = content;
	   }

	   public void send() {
	      String host = "smtp.naver.com";
	      String to = "hyejin0212@gmail.com";


	      // Get the session object
	      Properties props = new Properties();
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.auth", "true");

	      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(self.user, self.password);
	         }
	      });

	      // Compose the message
	      try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(user));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Subject
	         message.setSubject(subject);

	         // Text
	         message.setText(content);

	         // send the message
	         Transport.send(message);
	         
	      } catch (MessagingException e) {
	    	  JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
	         e.printStackTrace();
	      }
	   }

}
