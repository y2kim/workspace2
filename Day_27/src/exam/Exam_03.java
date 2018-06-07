package exam;

import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Exam_03 extends JFrame {
	private Exam_03 self =this;
	private JButton buttonSelect = new JButton("파일 선택");
	private JLabel labelPath = new JLabel("PAth");
	private JButton buttonSend= new JButton("보내기");
	File target;
	Socket sock;
	DataOutputStream dos;
	FileInputStream fis;
	DataInputStream dis;
	private void compInit() {
		this.setLayout(new FlowLayout());
		this.add(buttonSelect);
		this.add(labelPath);
		this.add(buttonSend);
	}
	private void eventInit()  {
		
		this.buttonSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(self);
				fd.setVisible(true);
				
				target = new File(fd.getDirectory()+fd.getFile());
				labelPath.setText(target.getPath());

			}
		});		
				
		this.buttonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sock = new Socket("192.168.20.3", 50000);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fis = new FileInputStream(target);
					dis = new DataInputStream(fis);
					byte[] fileContents = new byte[(int)target.length()];
					dis.readFully(fileContents);
					dos = new DataOutputStream(sock.getOutputStream());
					dos.writeInt((int)target.length());
					dos.flush();
					dos.write(fileContents);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
	}
	
	public Exam_03() {
		super();
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new Exam_03();
			}
		});
	}

}
