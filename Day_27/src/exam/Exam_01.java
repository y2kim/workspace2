package exam;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Exam_01 extends JFrame {
	
	private JPanel panelCenter =new JPanel(new CardLayout());
	private JPanel panelRed = new JPanel();
	private JPanel panelGreen = new JPanel();
	private JPanel panelBlue = new JPanel();
	
	private JButton buttonRed = new JButton("RED");
	private JButton buttonGreen = new JButton("GREEN");
	private JButton buttonBlue = new JButton("BLUE");
	private JPanel panelButton = new JPanel(new FlowLayout());
	
	private void compInit() {
		
		this.panelRed.setBackground(Color.RED);
		this.panelGreen.setBackground(Color.GREEN);
		this.panelBlue.setBackground(Color.BLUE);
		
		this.panelCenter.add(panelRed, "red");
		this.panelCenter.add(panelGreen, "green");
		this.panelCenter.add(panelBlue, "blue");
		this.add(panelCenter);
		
		this.panelButton.add(buttonRed);
		this.panelButton.add(buttonGreen);
		this.panelButton.add(buttonBlue);
		this.add(panelButton, BorderLayout.SOUTH);
	}
	
	private void eventInit() {
		this.buttonGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LayoutManager lm = panelCenter.getLayout(); // 카드레이아웃을 못쓰는 함정이 있다.  다운캐스팅 방법을 쓸수 있다,
				((CardLayout)lm).show(panelCenter, "green");// Object o = String  부르는것처럼 하면됨 
				//parent : panelcenter , name : 값
			}
		});
		this.buttonBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LayoutManager lm = panelCenter.getLayout();
				((CardLayout)lm).show(panelCenter, "blue");
			}
		});
		this.buttonRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LayoutManager lm = panelCenter.getLayout();
				((CardLayout)lm).show(panelCenter, "red");
			}
		});
	}
	
	public Exam_01() {
		super("CardLayout");
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
				new Exam_01();
			}
		});
	}
}
