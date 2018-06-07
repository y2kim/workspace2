package exam;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Exam_05 extends JFrame {
	
	private JTextArea textarea = new JTextArea();
	private JScrollPane paneContents = new JScrollPane(textarea);
	
	private JButton buttonSave = new JButton("Save");
	private JButton buttonOpen = new JButton("Open");
	
	private void compInit() {
		this.textarea.setLineWrap(true);
		this.add(paneContents);
		this.add(buttonSave,BorderLayout.SOUTH);
		
	}
	
	private void eventInit() {
		this.buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = textarea.getText();
				try (BufferedWriter bw = new  BufferedWriter(new FileWriter("outputs.txt",true));){ 
					// ,true 추가하면 uphand 상태
					bw.write(str);// 
					bw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public Exam_05() {
		super("File Dialog");
		this.setSize(400, 300);
		this.setLocationRelativeTo(rootPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
//		File output = new File("output.txt");
//		BufferedWriter bufw = new BufferedWriter(new FileWriter(output,true));  // ,true 추가되면 uphand가 되며 내용이 추가됨
//		bufw.write("Hello");   // 덮어쓰기 
//		bufw.flush();
//		bufw.close();
//		System.out.println("파일 출력 완료");
//		
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new Exam_05();
			}
		});
	}

}
