package icecream.Games;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

class GameOverDialog extends JDialog {
	GameOverDialog god = this;
	IcecreamDialog ice;
	GridBagConstraints gbc1 = new GridBagConstraints();
	final Object lock = new Object();
	
	private int x = 0;
	private int y = 0;
	private boolean rank;
	private int score;

	public static boolean start;

	private JPanel background = new JPanel(new GridBagLayout());

	private CardLayout clUpper = new CardLayout();
	private CardLayout clLower = new CardLayout();
	private JPanel cardPanelUpper = new JPanel(clUpper);
	private JPanel cardPanelLower = new JPanel(clLower);
	private JPanel imgPanel = new JPanel(new BorderLayout(5,5));
	private JPanel contentPanel = new JPanel(new GridBagLayout());
	private JPanel rankPanel = new JPanel(new GridBagLayout());
	private JPanel btnPanel = new JPanel(new GridBagLayout());

	private JPanel namePanel = new JPanel(new GridBagLayout());


	private JButton btnRe = new JButton("다시 시작");
	private JButton btnRank = new JButton("랭킹보기");
	private JButton btnMenu = new JButton("메뉴로 나가기");
	private JButton btnExit = new JButton("게임종료");

	private JButton btnOk = new JButton("등 록");

	private JLabel msg = new JLabel("축하합니다! 순위에 랭크되었습니다.");
	private JTextField nameField = new JTextField();


	private JButton[] btnsNoName = new JButton[] {btnRe, btnRank, btnMenu, btnExit};
	private JButton[] btnsName = new JButton[] {btnRe, btnRank, btnMenu, btnExit, btnOk};

	private JLabel[] labelsRank = new JLabel[] {new JLabel(), new JLabel(), new JLabel(),new JLabel(),new JLabel()};

	private void setXY(int x, int y) {
		gbc1.gridx = x;
		gbc1.gridy = y;
		this.x = x;
		this.y = y;
	}

	private void showRank() {
		//		Font small = new Font("Helvetica", Font.BOLD, 20);
		contentPanel.setPreferredSize(new Dimension(220, 150));
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		JLabel text = new JLabel("R A N K I N G");

		setXY(0,0); gbc1.weighty = 0.7;
		contentPanel.add(text, gbc1);
		gbc1.weighty = 0.2;
		for(int i = 0; i < ScoreRanking.rankList.size(); i++) {
			setXY(0,i+1);
			String str = " " + (i+1) +"위. " + ScoreRanking.rankList.get(i).getName() + ", " + ScoreRanking.rankList.get(i).getScore() + "점";
			labelsRank[i].setText(str);
			contentPanel.add(labelsRank[i], gbc1);
		}
		gbc1.fill = GridBagConstraints.NONE;
		rankPanel.add(contentPanel);
	}

	private void compInit() {
		try {
			UIManager.setLookAndFeel(
					"javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {}

		background = new JPanel() {
			public void paintComponent(Graphics g) {
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		this.setFocusable(true);
		this.requestFocusInWindow();
		background.setLayout(new BorderLayout());

		JLabel gameover = new JLabel(new ImageIcon("src/icecream/Games/resources/gameover.png"));
		imgPanel.add(gameover);

		gbc1.weighty = 0.0;
		clUpper.setVgap(8);
		cardPanelUpper.add("img", imgPanel);
		cardPanelUpper.add("rank", rankPanel);
	}

	private void compInitNoName() {
		gbc1.fill = GridBagConstraints.NONE;
		for(int i = 0; i < btnsNoName.length; i++) {
			btnsNoName[i].setPreferredSize(new Dimension(130, 30));
			btnsNoName[i].setOpaque(false);
			setXY(0, i);
			btnPanel.add(btnsNoName[i], gbc1);
		}
		cardPanelLower.add("btn", btnPanel);

		showRank();

		background.add(cardPanelUpper, BorderLayout.NORTH);
		background.add(cardPanelLower);
		this.getContentPane().add(background,0);
	}

	private void compInitName() {
		gbc1.fill = GridBagConstraints.NONE;
		for(int i = 0; i < btnsName.length; i++) {
			btnsName[i].setPreferredSize(new Dimension(130, 30));
			btnsName[i].setOpaque(false);
			setXY(0, i);
			btnPanel.add(btnsName[i], gbc1);
		}
		gbc1.insets = new Insets(2, 0, 2, 0);
		nameField.setPreferredSize(new Dimension(130, 30));
		setXY(0,0);
		namePanel.add(msg, gbc1);
		setXY(0,1);
		namePanel.add(nameField, gbc1);
		setXY(0,2);
		namePanel.add(btnOk, gbc1);

		cardPanelLower.add("name", namePanel);
		cardPanelLower.add("btn", btnPanel);

		background.add(cardPanelUpper, BorderLayout.NORTH);
		background.add(cardPanelLower);
		this.getContentPane().add(background,0);
	}

	private void eventInit() {
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyThread.pause = false;
				MyThread.start = true;
				MyThread.first = true;
				MyThread.sec = 60;
				IcecreamDialog.scoreInt = 0;
				IcecreamDialog.score.setText("0");
				repaint();
				synchronized(MyThread.lock) {
					MyThread.lock.notifyAll();
				}
				repaint();
				god.dispose();
			}
		});
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean rank = true;
				if(rank) {
					clUpper.show(cardPanelUpper, "rank");
					rank = false;
				} 
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyThread.start = false;
				MyThread.first = true;
				MyThread.sec = 60;
				ice.dispose();
				god.dispose();
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				if(name.equals("")||name.equals(" ")) { //
					name = "Unknown";
				}
				new ScoreRanking(score, name);
				showRank();
				clLower.show(cardPanelLower, "btn");
			}
		});
	}

	private void showGameOverNoName() {	// 이름 입력x
		compInit();
		compInitNoName();
		eventInit();
	}

	private void showGameOverName() {	// 이름 입력ㅇ 
		compInit();
		compInitName();
		eventInit();
	}

	public GameOverDialog(int score, IcecreamDialog parent) {
		//	public GameOverDialog(int score) {
		this.ice = parent;
		this.score = score;
		this.start = true;

		this.setSize(230,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		ScoreRanking sr = new ScoreRanking();

		int size = ScoreRanking.rankList.size();

		if(size <= 4) {
			showGameOverName();	// ArrayList 비어있으면 바로 기록
		} else {
			for(int i = 0; i < size; i++) {
				if(score >= ScoreRanking.rankList.get(i).getScore()) {
					showGameOverName();
					break;
				} else {
					int j = i + 1;
					while(j < size) {
						if(score >= ScoreRanking.rankList.get(j).getScore()) {
							showGameOverName();
							break;
						}
						j++;
					}
					showGameOverNoName();
					break;
				} 
			}
		}

		this.setModal(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}