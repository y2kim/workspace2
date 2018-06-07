package quiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Quiz_01 extends JFrame {
	
	public Quiz_01() {
		super("");
		
	}
	
	public static void main(String[] args) {
		// member.txt. 파일 내에서 아이디만 뽑아서 프로그램을 작성  jack, mike , jane 
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				
			}
		});
		File file = new File("Member.txt");
		try (BufferedReader bf = new BufferedReader(new FileReader(file))){
			String str = null;
			//	while((line = br.readLine())!= null) {
			while((str =bf.readLine())!=null) {
				if(str.startsWith("id=")) {
					System.out.print(str.split("=")[1]);
//					String[] sa = str.split("id=");
//					System.out.println(sa[1]);
					System.out.print(" - ");
				}
				//  jack - 1234
				if(str.startsWith("pw=")) {
					System.out.println(str.split("=")[1]);
				}
			}
					
		} catch (Exception e) {
			
		}
	}
}
