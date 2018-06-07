package exam;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Exam_06 extends JFrame{
	
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
	
	File file;
	BufferedReader bf;
	
	private void EventInit() {
		this.buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = fieldId.getText();
				String pwd = fieldPw.getText();
				
				file =new File("members.txt");
				try{
					bf = new BufferedReader(new FileReader(file));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String read = null;
				boolean idcek = false;
				try {
					/*발 버전   list 복잡
					 * Hash<String,String>
					 * 
					 * while(true){
					 * String 1,2 = br.read()
					 * 
					 * }
					 * accou put(1,2)
					 * accou contanid (id){ accouc.get(id).equale(pw)}
					 * */
					while((read=bf.readLine())!=null) {
							if(read.split("=")[1].split(":")[0].equals(fieldId.getText())&&
									read.split(":")[1].split("=")[1].equals(fieldPw.getText())) {
								idcek = true;
								break;
							}else {
								idcek = false;
							}
					}
					//read.split("=")[1].equals(fieldId.getText())
					if(idcek == true) {
						JOptionPane.showMessageDialog(rootPane, "존재하는 아이디 입니다");
					}else {
						JOptionPane.showMessageDialog(rootPane, "존재하지 않는 아이디 입니다");
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public Exam_06() {
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
				new Exam_06();
			}
		});
	}

}
