package exam;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Exam_02 extends JFrame {
	Exam_02 self =this;
	
	private JButton buttonFile =new JButton("FIle Dialog");
	private JTextArea areaContents =new JTextArea();
	private JScrollPane paneContents = new JScrollPane(areaContents);
	
	private void compInit() {
		this.add(paneContents);
		this.add(buttonFile , BorderLayout.SOUTH);
		this.areaContents.setLineWrap(true);
		
		this.buttonFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(self); //<- AWT        // swing : fileChooser
				fd.setMode(FileDialog.LOAD);
				fd.setVisible(true);
				
				File file = new File(fd.getDirectory() + fd.getFile());
				
				try(BufferedReader br = new BufferedReader(new FileReader(file));) {
					String line = null;
					while((line = br.readLine())!= null) {
						areaContents.append(line + "\n");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
//				try {
//					file.createNewFile();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
				//System.out.println("지정경로 : "+fd.getDirectory());
				//System.out.println("지정 이름 : "+fd.getFile());
								
			}
		});
	}
	
	public Exam_02() {
		super();
		this.setSize(400, 300);
		this.setLocationRelativeTo(rootPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setLayout(new FlowLayout());
		this.compInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new Exam_02();
			}
		});
	}

}
