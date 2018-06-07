package Games;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MailDialog extends JDialog {
	class Mail {
		  Mail self = this;
		   private String user;
		   private String password;
		   private String subject;
		   private String content;
		   
		   public Mail(String subject, String content) {
		      this.user = "heyjen94";
		      this.password = "Ksocool0212";
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
		    	  JOptionPane.showMessageDialog(null, "로그인 실패");
		         e.printStackTrace();
		      }
		   }
	}
	
	private MailDialog self = this;

	private JLabel subLabel = new JLabel("   제목 : ");
	private JTextField subField = new JTextField();
	private JPanel panelSubject = new JPanel(new BorderLayout());



	private JTextArea area = new JTextArea();
	private JScrollPane paneArea = new JScrollPane(area);

	private JButton btn = new JButton("전송");   

	private JPanel panelback = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private void compInit() {


		this.panelSubject.add(subLabel, BorderLayout.WEST);
		this.panelSubject.add(subField);

		this.add(panelSubject,BorderLayout.NORTH);

		this.area.setLineWrap(true);
		this.add(paneArea);

		this.panelback.add(btn);
		this.add(panelback, BorderLayout.SOUTH);

	}

	private void eventInit() {
		this.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = JOptionPane.showConfirmDialog(null,"전송하시겠습니까?","",JOptionPane.YES_NO_OPTION);
				if(sel == 0) { 
					Mail m = new Mail(subField.getText(), area.getText());
					JOptionPane.showMessageDialog(null, "전송 완료");
					m.send();
					self.dispose();
				}
			}
		});
	}

	public MailDialog() {
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10,10));
		compInit();
		eventInit();
		this.setVisible(true);
	}
	
	

}
