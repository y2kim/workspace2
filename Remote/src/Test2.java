

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

public class Test2 extends JFrame {
	
	private JTextField num1 = new JTextField();
	private JTextField num2 = new JTextField();
	private JButton sumBtn = new JButton("+");
	private JLabel answer = new JLabel("\t\r                         "+"Result : 0");
	
	private void eventBtn(){
		this.sumBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int no1 = Integer.parseInt(num1.getText());
				int no2 = Integer.parseInt(num2.getText());
				int sum = no1 + no2;
				//String result = sum.toString();
				answer.setText("\t\r                      "+"Result : "+sum);
			}
		});
	}
	
	public Test2() {
		super("계산기");
		this.setSize(225, 200);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4,1));
		this.add(num1);
		this.add(num2);
		this.add(sumBtn);
		this.add(answer);
		this.eventBtn();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Test2();
	}
}
