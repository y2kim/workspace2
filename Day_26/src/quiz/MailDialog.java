package quiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MailDialog extends JDialog {
	private MailDialog self = this;

	   private JLabel userID = new JLabel("네이버 아이디 : ");    // 보내는 사람 이메일
	   private JTextField idField = new JTextField();
	   private JLabel userPW = new JLabel("비밀번호 : ");    // 보내는 사람 이메일의 비번
	   private JTextField pwField = new JTextField();
	   private JPanel panelInfo = new JPanel(new FlowLayout());

	   private JLabel subLabel = new JLabel("   제목 : ");
	   private JTextField subField = new JTextField();
	   private JPanel panelSubject = new JPanel(new BorderLayout());



	   private JTextArea area = new JTextArea();
	   private JScrollPane paneArea = new JScrollPane(area);

	   private JButton btn = new JButton("전송");   

	   private JPanel panelback = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	   private void compInit() {
	      this.panelInfo.add(userID);
	      this.panelInfo.add(idField);
	      this.panelInfo.add(userPW);
	      this.panelInfo.add(pwField);

	      this.idField.setPreferredSize(new Dimension(100,20));
	      this.pwField.setPreferredSize(new Dimension(100,20));

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
	                   Mail m = new Mail(idField.getText(), pwField.getText(), subField.getText(), area.getText());
	                m.send();
	                self.dispose();
	                }
	         }
	      });
	   }

	   public MailDialog(MainProgram parent) {
	      this.setSize(500,300);
	      this.setLocationRelativeTo(null);
	      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	      this.setLayout(new BorderLayout(10,10));
	      compInit();
	      eventInit();
	      this.setVisible(true);
	   }

}
