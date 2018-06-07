package tetris;

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
	Tetris tetris;
	GridBagConstraints gbc = new GridBagConstraints();
	//ScoreRanking srk = new ScoreRanking();
	private int x = 0;
	private int y = 0;
	private int count = 1;
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

	private JLabel[] labelsRank = new JLabel[] {new JLabel(), new JLabel(), new JLabel(),new JLabel(),new JLabel()};
	private JButton btnRe = new JButton("다시 시작");
	private JButton btnRank = new JButton("랭킹보기");
	private JButton btnMenu = new JButton("메뉴로 나가기");
	private JButton btnExit = new JButton("게임종료");
	private JButton btnOk = new JButton("등 록");

	private JLabel msg = new JLabel("축하합니다! 순위에 랭크되었습니다.");
	private JTextField nameField = new JTextField();
	
	private JButton[] btnsNoName = new JButton[] {btnRe, btnRank, btnMenu, btnExit};
	private JButton[] btnsName = new JButton[] {btnRe, btnRank, btnMenu, btnExit, btnOk};

	private void setXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.x = x;
		this.y = y;
	}

	private void showRank() {
		//		Font small = new Font("Helvetica", Font.BOLD, 20);
		contentPanel.setPreferredSize(new Dimension(220, 150));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel text = new JLabel("R A N K I N G");

		setXY(0,0); gbc.weighty = 0.7;
		contentPanel.add(text, gbc);
		gbc.weighty = 0.2;
		for(int i = 0; i < ScoreRanking.rankList.size(); i++) {
			setXY(0,i+1);
			String str = " " + (i+1) +"위. " + ScoreRanking.rankList.get(i).getName() + ", " + ScoreRanking.rankList.get(i).getScore() + "점";
			labelsRank[i].setText(str);;
			contentPanel.add(labelsRank[i], gbc);
		}
		gbc.fill = GridBagConstraints.NONE;
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

		JLabel gameover = new JLabel(new ImageIcon("src/tetris/resources/gameover.png"));
		imgPanel.add(gameover);

		gbc.weighty = 0.0;
		clUpper.setVgap(8);
		cardPanelUpper.add("img", imgPanel);
		cardPanelUpper.add("rank", rankPanel);
	}

	private void compInitNoName() {
		gbc.fill = GridBagConstraints.NONE;
		for(int i = 0; i < btnsNoName.length; i++) {
			btnsNoName[i].setPreferredSize(new Dimension(130, 30));
			btnsNoName[i].setOpaque(false);
			setXY(0, i);
			btnPanel.add(btnsNoName[i], gbc);
		}
		cardPanelLower.add("btn", btnPanel);
		showRank();
		background.add(cardPanelUpper, BorderLayout.NORTH);
		background.add(cardPanelLower);
		this.getContentPane().add(background,0);
	}

	private void compInitName() {
		gbc.fill = GridBagConstraints.NONE;
		for(int i = 0; i < btnsName.length; i++) {
			btnsName[i].setPreferredSize(new Dimension(130, 30));
			btnsName[i].setOpaque(false);
			setXY(0, i);
			btnPanel.add(btnsName[i], gbc);
		}
		gbc.insets = new Insets(2, 0, 2, 0);
		nameField.setPreferredSize(new Dimension(130, 30));
		setXY(0,0);
		namePanel.add(msg, gbc);
		setXY(0,1);
		namePanel.add(nameField, gbc);
		setXY(0,2);
		namePanel.add(btnOk, gbc);

		cardPanelLower.add("name", namePanel);
		cardPanelLower.add("btn", btnPanel);

		background.add(cardPanelUpper, BorderLayout.NORTH);
		background.add(cardPanelLower);
		this.getContentPane().add(background,0);
	}

	private void eventInit(Tetris tetris) {
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				god.dispose();
			
			}
		});
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count%2 == 1) {
					clUpper.show(cardPanelUpper, "rank");
				} else {
					clUpper.show(cardPanelUpper, "img");
				}
				count++;
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				god.dispose();
				tetris.dispose();
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

	private void showGameOverNoName(Tetris ts) {	// 이름 입력x
		compInit();
		compInitNoName();
		eventInit(ts);
	}

	private void showGameOverName(Tetris ts) {	// 이름 입력ㅇ
		compInit();
		compInitName();
		eventInit(ts);
	}

	public GameOverDialog(int score , Tetris ts) {
		//	public GameOverDialog(int score) {
		//this.tetris = parent;
		this.score = score;
		this.start = true;
		
		this.setSize(230,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		ScoreRanking sr = new ScoreRanking();

		int size = ScoreRanking.rankList.size();
		System.out.println("size : " + size);
		for(int i = 0; i < ScoreRanking.rankList.size(); i++) {
			System.out.println(ScoreRanking.rankList.get(i).getName() + ", " + ScoreRanking.rankList.get(i).getScore() );
		}
		if(size <= 4) {
			showGameOverName(ts);	// ArrayList 비어있으면 바로 기록
		} else {
			for(int i = 0; i < size; i++) {
				if(score >= ScoreRanking.rankList.get(i).getScore()) {
					showGameOverName(ts);
					break;
				} else {
					int j = i + 1;
					while(j < size) {
						if(score >= ScoreRanking.rankList.get(j).getScore()) {
							showGameOverName(ts);
							break;
						}
						j++;
					}
					showGameOverNoName(ts);
				} 
			}
		}
		this.setModal(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}