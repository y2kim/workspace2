package exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Exam_04 extends JFrame {
	
	public Exam_04() {
		super();
		this.setSize(400, 300);
		this.setLocationRelativeTo(rootPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.compInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		File output = new File("output.txt");
		BufferedWriter bufw = new BufferedWriter(new FileWriter(output,true));  // ,true 추가되면 uphand가 되며 내용이 추가됨
		bufw.write("Hello");   // 덮어쓰기 
		bufw.flush();
		bufw.close();
		System.out.println("파일 출력 완료");
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				
			}
		});
	}

}
