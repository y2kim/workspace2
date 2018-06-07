package exam;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Exam_01 extends JFrame{
	private JButton buttonpop = new JButton("Pop!");
	private Exam_01 self = this;
	
	private void compInit() {
		this.add(buttonpop);
		this.buttonpop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				JDialog dialog = new JDialog();
//				dialog.setSize(300, 200);
//				dialog.setLocationRelativeTo(self);
//				dialog.setLayout(new FlowLayout());
//				JButton button1 = new JButton("1");
//				JButton button2 = new JButton("2");
//				JButton button3 = new JButton("3");
//				dialog.add(button1);
//				dialog.add(button2);
//				dialog.add(button3);
//				dialog.setVisible(true);
				MyDialog md = new MyDialog(self);
			}
		});
	}
	
	public Exam_01() {
		super();
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.compInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new Exam_01();
			}
		});
	}
}
