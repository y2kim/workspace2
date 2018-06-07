package quiz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainProgram extends JFrame{
	
	public MainProgram MPmoniter = this;
	StudentMember smb = new StudentMember();
	
	private JButton button1 = new JButton("신규 정보 입력");
	private JButton button2 = new JButton("학생 정보 출력");
	private JButton button3 = new JButton("학생 정보 삭제");
	private JButton button4 = new JButton("학생 정보 수정");
	private JButton button5 = new JButton("  문의 하기  ");
	private JButton button6 = new JButton("프로그램 종료");
	
	private void setXY(GridBagConstraints gbc ,int x ,int y) {
		gbc.gridx = x;
		gbc.gridy = y;
	}
	
	private void buttonInit() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,0,5,0);
		this.button1.setPreferredSize(new Dimension(150, 45));
		setXY(gbc, 0, 0);
		this.add(button1,gbc);
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Student std = new Student(1001, "kim", 50, 50, 50);
//				Student std2 = new Student(1002, "jack", 22, 22, 22);
//				smb.HashAddStudent(1001, std);
//				smb.HashAddStudent(1002, std2);
				new InputDialog(MPmoniter);
			}
		});
		
		this.button2.setPreferredSize(new Dimension(150, 45));
		setXY(gbc, 0, 1);
		this.add(button2,gbc);
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PrintMember();
			}
		});
		
		this.button3.setPreferredSize(new Dimension(150, 45));
		setXY(gbc, 0, 2);
		this.add(button3,gbc);
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RemoveStudentDialog(MPmoniter);
			}
		});
		
		this.button4.setPreferredSize(new Dimension(150, 45));
		setXY(gbc, 0, 3);
		this.add(button4,gbc);
		
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StudentEdit();
			}
		});
		
		this.button5.setPreferredSize(new Dimension(150, 45));
		setXY(gbc, 0, 4);
		this.add(button5,gbc);
		
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MailDialog(MPmoniter);
			}
		});
		
		this.button6.setPreferredSize(new Dimension(150, 45));
		setXY(gbc, 0, 5);
		this.add(button6,gbc);
		
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ExitProgram();
			}
		});
		
	}
	
	public MainProgram() {
		super("Student Mangement");
		this.setSize(300, 400);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.buttonInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new MainProgram();
			}
		});
	}

}
