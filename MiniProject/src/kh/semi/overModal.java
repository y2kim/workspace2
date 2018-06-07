package kh.semi;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import kh.semi.Molle.Score;

public class overModal extends JDialog {
	
	private Molle mol = null; //�������ְ���

	private JPanel dividePanel = new JPanel(new GridLayout(2, 1));
	private JPanel topPanel = new JPanel(new GridLayout(1, 3));
	private JPanel bottomPanel = new JPanel(new FlowLayout());

	private JLabel titleLabel = new JLabel();

	private JLabel scoreRan = new JLabel("�� �� :");
	private JTextField scoreTf = new JTextField();
	private JButton resiBtn = new JButton("�� ��");

	private JButton rangBtn = new JButton("������� �ְ�����");
	private JButton rstBtn = new JButton("�ٽ��ϱ�");
	private JButton mainBtn = new JButton("���ΰ���");
	
	private JPanel backPan1 = new JPanel();
	
	private Cursor cursor;
	private Image img;
		
	public void compInit() {	
		
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/kh/semi/resources/wiggle.ttf")).deriveFont(13f);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("Candy.png");
		Point point = new Point(0, 0);
		cursor = tk.createCustomCursor(img, point, "attack");
		setCursor(cursor);
		
		this.rstBtn.setFont(myFont);
		this.rangBtn.setFont(myFont);
		this.resiBtn.setFont(myFont);
		this.scoreRan.setFont(myFont);
		this.mainBtn.setFont(myFont);

		this.scoreTf.setEditable(false);
		this.topPanel.add(scoreRan);
		this.topPanel.add(scoreTf);
		this.topPanel.add(resiBtn);
		
		this.scoreTf.setText((mol.getScore()+""));
		this.scoreTf.setHorizontalAlignment(SwingUtilities.CENTER);

		this.dividePanel.add(topPanel);

		this.bottomPanel.add(rangBtn);
		this.bottomPanel.add(rstBtn);
		this.bottomPanel.add(mainBtn);		

		this.dividePanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(dividePanel);
		
		
	}
	public void dispo() { //�ٽ� ����
		setModal(false);
		this.dispose();
	}

	public void eventInit() {
		//�ٽ��ϱ�
		this.rstBtn.addActionListener(new ActionListener() {		 	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispo();
			}
		});
		
		this.mainBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

	}
	class Score{
		private String name;
		private int score;
		
		public Score() {}
		public Score(String name, int score) {
			this.name = name;
			this.score = score;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
	}
	




	public overModal(Molle mainFrame) {
		
		mol = mainFrame; //������������ �����ٰ� �������.		
		this.setTitle("���ӿ���");
		this.setSize(350, 130);
		this.setLocationRelativeTo(mainFrame);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.compInit();
		this.eventInit();
		this.setModal(true);
		
		
		this.setVisible(true);		
		
	}

}
