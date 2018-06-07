package quiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class Client extends JFrame {
	
	private JLabel labelId = new JLabel("아이디");
	private JTextField fieldId = new JTextField();
	
	private JLabel labelPw = new JLabel("로그인");
	private JTextField fieldPw = new JTextField();
	
	private JPanel panelLogin = new JPanel(new GridBagLayout());
	//---center			
	private JButton buttonLogin = new JButton("로그인");
	//-- south
	

	
	private void compInit() {
		GridBagConstraints gbc =new GridBagConstraints();
		
		fieldId.setPreferredSize(new Dimension(100, 27));
		fieldPw.setPreferredSize(new Dimension(100, 27));
		
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx =0; gbc.gridy =0;
		this.panelLogin.add(labelId, gbc);
		gbc.gridx =1; gbc.gridy =0;
		this.panelLogin.add(fieldId, gbc);
		gbc.gridx =0; gbc.gridy =1;
		this.panelLogin.add(labelPw, gbc);
		gbc.gridx =1; gbc.gridy =1;
		this.panelLogin.add(fieldPw, gbc);
		
		this.add(panelLogin);
		this.add(buttonLogin , BorderLayout.SOUTH);
	}
	
	
	private void EventInit() {
		this.buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try (	Socket sock = new Socket("127.0.0.1", 8080);
						DataInputStream dis =new DataInputStream(sock.getInputStream());
						DataOutputStream dos = new DataOutputStream(sock.getOutputStream());){
					String id = fieldId.getText();
					String pwd = fieldPw.getText();
					dos.writeUTF(id);
					dos.writeUTF(pwd);
					dos.flush();
					int descheck = dis.readInt();
					if(descheck ==1) {
						JOptionPane.showMessageDialog(rootPane, "성공");
					}else {
						JOptionPane.showMessageDialog(rootPane, "실패");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
	}
	
	public Client() {
		super();
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.EventInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new Client();
			}
		});
	}
}
