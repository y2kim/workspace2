package icecream.Games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class IcecreamDialog	extends JDialog {
	public static int scoreInt=0;
	public static ScoreRanking sr = new ScoreRanking();
	public static boolean pause = false;

	private IcecreamDialog ice = this;

	private int count = 0;
	private int numQuiz, numAnswer;
	private Image yellow, pink, blue, cone, back;

	Resources r = new Resources();
	MyThread timeThread;


	private JLabel[] arrayQuiz = new JLabel[] { new JLabel(), new JLabel(), new JLabel(), new JLabel() };
	private JLabel[] arrayAnswer = new JLabel[] { new JLabel(), new JLabel(), new JLabel(), new JLabel() };
	private JLabel timeLabel = new JLabel("TIME");
	private JLabel time = new JLabel("60");
	private JLabel scoreLabel = new JLabel("SCORE");
	public static JLabel score = new JLabel("0");

	private JButton btnYellow = new JButton(); // 노랑 
	private JButton btnPink = new JButton(); // 핑크
	private JButton btnBlue = new JButton(); // 파랑 


	private JPanel background;
	public  TimePanel timePanel = new TimePanel();
	private JLayeredPane centerPanel = this.getLayeredPane();
	private JPanel panelBtns = new JPanel(new FlowLayout());	// 버튼패널 
	private JPanel panelHeader = new JPanel(new BorderLayout()); // 헤더패널
	private JPanel timeminiPanel = new JPanel(new GridLayout(1,2));
	private JPanel scoreminiPanel = new JPanel(new BorderLayout());



	private void removeLabel() {
		for(int i = 0; i <= numQuiz; i++) {
			centerPanel.remove(arrayQuiz[i]);
		}
		for(int i = 0; i <= numQuiz; i++) {
			centerPanel.remove(arrayAnswer[i]);
		}
		repaint();
	}

	private void setNewRound() {
		r.reset();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				removeLabel();
			}
		});
		count = 0;
		r.setQuiz();
		addQuizIce(Resources.quiz);
	}

	private void goNextRound(boolean bool) {
		if(bool) {	// 정답 
			count++;
			if(count == Resources.quiz.size()) { // 전체 정답 
				scoreInt += 10;
				score.setText(String.valueOf(scoreInt));
				setNewRound();
			}
		} else {	// 오답
			setNewRound();
		}
	}

	private void setImage() {	// 이미지 셋팅(버튼, 배경)
		try {
			this.yellow = ImageIO.read(new File("src/icecream/Games/resources/yellow.png"));
			this.pink = ImageIO.read(new File("src/icecream/Games/resources/pink.png"));
			this.blue = ImageIO.read(new File("src/icecream/Games/resources/blue.png"));
			this.cone = ImageIO.read(new File("src/icecream/Games/resources/cone.png"));
			this.back = ImageIO.read(new File("src/icecream/Games/resources/back3.png"));
			r.putArrayImg("yellow", yellow);
			r.putArrayImg("pink", pink);
			r.putArrayImg("blue", blue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addCone() {
		JLabel coneLab1 = new JLabel();
		JLabel coneLab2 = new JLabel();
		coneLab1.setIcon(new ImageIcon(cone)); coneLab1.setBounds(95, 210, 90, 122);
		coneLab2.setIcon(new ImageIcon(cone)); coneLab2.setBounds(370, 210, 90, 122);
		this.centerPanel.add(coneLab1);
		this.centerPanel.add(coneLab2);
	}

	private void addQuizIce(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			arrayQuiz[i].setIcon(new ImageIcon(Resources.arrayImg.get(list.get(i))));
			switch(i) {
			case 0:
				arrayQuiz[i].setBounds(85, 155, 110, 86);
				break;
			case 1:
				arrayQuiz[i].setBounds(85, 125, 110, 86);
				break;
			case 2:
				arrayQuiz[i].setBounds(85, 95, 110, 86);
				break;
			case 3:
				arrayQuiz[i].setBounds(85, 65, 110, 86);
				break;
			}
			this.centerPanel.add(arrayQuiz[i],0);
			numQuiz = i;
		}
	}

	private void addAnswerIce(ArrayList<String> list) {
		arrayAnswer[count].setIcon(new ImageIcon(Resources.arrayImg.get(list.get(count))));
		switch(count) {
		case 0:
			arrayAnswer[count].setBounds(360, 155, 110, 86);
			break;
		case 1:
			arrayAnswer[count].setBounds(360, 125, 110, 86);
			break;
		case 2:
			arrayAnswer[count].setBounds(360, 95, 110, 86);
			break;
		case 3:
			arrayAnswer[count].setBounds(360, 65, 110, 86);
			break;
		}
		this.centerPanel.add(arrayAnswer[count],0);
		repaint();
		numAnswer = count;
	}

	private void eventInit() {
		this.addKeyListener(new KeyAdapter() {	// 방향키로 아이스크림 추가 
			public void keyPressed(KeyEvent e) {
				if(count+1 <= Resources.quiz.size()) {
					int keycode = e.getKeyCode();

					if(keycode == KeyEvent.VK_SPACE) {
						MyThread.pause = true;
						new MenuDialog(ice);
					} else {
						if(keycode == KeyEvent.VK_LEFT) {
							Resources.answer.add("yellow");
						} else if(keycode == KeyEvent.VK_UP) {
							Resources.answer.add("pink");
						} else if(keycode == KeyEvent.VK_RIGHT) {
							Resources.answer.add("blue");
						} 
						addAnswerIce(Resources.answer);
						new CheckThread().start();
					}
				}
			}

		});

		this.btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count+1 <= Resources.quiz.size()) {
					Resources.answer.add("yellow");
					addAnswerIce(Resources.answer);
					new CheckThread().start();
				}
			}
		});

		this.btnPink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resources.answer.add("pink");
				addAnswerIce(Resources.answer);
				new CheckThread().start();
			}
		});

		this.btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resources.answer.add("blue");
				addAnswerIce(Resources.answer);
				new CheckThread().start();
			}
		});
	}

	public void compInit() {
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(back, 0, 0, getWidth (), getHeight(),  null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		this.setFocusable(true);
		this.requestFocusInWindow();
		background.setLayout(new BorderLayout());
		this.getContentPane().add(background,0);
		// ------------------------------------------ 배경설정 

		this.btnYellow.setIcon(new ImageIcon(yellow));
		this.btnPink.setIcon(new ImageIcon(pink));
		this.btnBlue.setIcon(new ImageIcon(blue));

		this.btnYellow.setBorderPainted(false); btnPink.setBorderPainted(false); btnBlue.setBorderPainted(false);
		this.btnYellow.setContentAreaFilled(false); btnPink.setContentAreaFilled(false); btnBlue.setContentAreaFilled(false);
		this.btnYellow.setFocusPainted(false); btnPink.setFocusPainted(false); btnBlue.setFocusPainted(false);

		this.panelBtns.setOpaque(false);
		this.panelBtns.add(btnYellow);
		this.panelBtns.add(btnPink);
		this.panelBtns.add(btnBlue);

		// ------------------------------------------ button 패널 

		try {
			Font myFont= Font.createFont(Font.TRUETYPE_FONT,new File("src/icecream/Games/resources/wiggle-hangeul.ttf")).deriveFont(25f);
			this.timeLabel.setFont(myFont); time.setFont(myFont); scoreLabel.setFont(myFont); score.setFont(myFont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.timeLabel.setForeground(Color.white); scoreLabel.setForeground(Color.white); time.setForeground(Color.white); score.setForeground(Color.white);
		this.timeLabel.setBorder(BorderFactory.createEmptyBorder(5,5,0,0)); time.setBorder(BorderFactory.createEmptyBorder(5,15,0,0));
		this.scoreLabel.setBorder(BorderFactory.createEmptyBorder(5,0,0,15)); score.setBorder(BorderFactory.createEmptyBorder(5,0,0,5));
		//		this.scoreLabel.setPreferredSize(new Dimension(scoreLabel.getWidth()+20,scoreLabel.getHeight()));

		this.timeminiPanel.add(timeLabel); this.timeminiPanel.add(time);
		this.scoreminiPanel.add(scoreLabel); this.scoreminiPanel.add(score,BorderLayout.EAST);
		this.timeminiPanel.setOpaque(false); this.scoreminiPanel.setOpaque(false);
		// ------------------------------------------ header 패널 


		this.timePanel.setOpaque(false);
		centerPanel.setOpaque(false);
		background.add(panelBtns,BorderLayout.SOUTH);
		this.panelHeader.add(timePanel);
		this.panelHeader.add(timeminiPanel, BorderLayout.WEST);
		this.panelHeader.add(scoreminiPanel, BorderLayout.EAST);
		this.panelHeader.setOpaque(false);
		background.add(panelHeader, BorderLayout.NORTH);
	}

	private void Init() {
		MyThread.sec = 60;
		
		scoreInt = 0;
		score.setText("0");
		TimePanel.timer.notify();
		repaint();
		
		setImage();
		compInit();
		eventInit();
		r.setQuiz();
		addCone();
		addQuizIce(Resources.quiz);
		timeThread.notify();
		setUndecorated(true);
		setVisible(true);

		this.setResizable(false);
		this.setSize(550, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public IcecreamDialog(JFrame parent, String title){
		if(MyThread.first) {

			this.setResizable(false);
			this.setSize(550, 450);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			MyThread.first = false;
			setImage();
			compInit();
			scoreInt = 0;
			score.setText("0");
			eventInit();
			r.setQuiz();
			addCone();
			addQuizIce(Resources.quiz);
			setUndecorated(true);
			setVisible(true);

			timeThread = new MyThread() {
				public void doTask() {
					countdown();
				}

				public void countdown() {				
					while(sec > 0) {
						checkPause();
						try {
							checkStart();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						sec -= 1;
						time.setText(String.valueOf(sec));
					}
					time.setText(String.valueOf(0));
					start = false;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if(!start) {
						new GameOverDialog(IcecreamDialog.scoreInt, ice);
						TimePanel.timer.stop();
						sec = 60;
						score.setText("0");
						repaint();
						return;
					}
				}
			};
			timeThread.start();
		} else {
			System.out.println("여기 ");
			Init();
		}
	}

	class CheckThread extends Thread {
		public void run(){
			boolean result = r.check(count);
			goNextRound(result);
		}
	}

}

