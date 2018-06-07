package exam;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

public class MyDialog  extends JDialog{
	
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	
	private void compInit() {
		this.add(button1);
		this.add(button2);
		this.add(button3);
	}
	
	public MyDialog(Exam_01 self) {
		this.setSize(400, 300);
		this.setLocationRelativeTo(self);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  // HIDE_ON_CLOSE : 숨기는것 (마지막 화면 기억함)  DISPOSE : 지움 
		this.compInit();
		this.setLayout(new FlowLayout());
		
		this.setVisible(true);
	}
}
