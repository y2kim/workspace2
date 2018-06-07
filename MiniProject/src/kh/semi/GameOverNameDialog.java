package kh.semi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class GameOverNameDialog extends JDialog {
	GameOverNameDialog god = this;
	Molle mol;
	GridBagConstraints gbc = new GridBagConstraints();
	private int x = 0;
	private int y = 0;
	private int count = 1;
	private int score;
	ScoreRanking sr;
	

	private JPanel background = new JPanel(new GridBagLayout());

	private CardLayout cl = new CardLayout();
	private CardLayout cl2 = new CardLayout();
	private JPanel cardPanel = new JPanel(cl);
	private JPanel cardPanel2 = new JPanel(cl2);
	private JPanel imgPanel = new JPanel(new BorderLayout(5,5));
	private JPanel contentPanel = new JPanel(new GridBagLayout());
	private JPanel rankPanel = new JPanel(new GridBagLayout());
	private JPanel btnPanel = new JPanel(new GridBagLayout());
	private JPanel namePanel = new JPanel(new GridBagLayout());


	private JButton btnRe = new JButton("다시하기");
	private JButton btnRank = new JButton("랭크");
	private JButton btnMenu = new JButton("메인으로 돌아가기");
	private JButton btnExit = new JButton("끝내기");
	private JButton btnOk = new JButton("왔더");

	private JLabel msg = new JLabel("얄리야리얄랴셩얄라리얄라");
	private JTextField nameField = new JTextField("");



	private JButton[] btns = new JButton[] {btnRe, btnRank, btnMenu, btnExit, btnOk};

	private void setXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.x = x;
		this.y = y;
		gbc.weightx = 1.0;
	}

	private void eventInit() {
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count%2 == 1) {
					cl.show(cardPanel, "rank");
				} else {
					cl.show(cardPanel, "img");
				}
				count++;
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				god.dispose();
				mol.dispose();
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
				if(name == null) {
					name = " ";
				}
				ScoreRanking sr = new ScoreRanking(score, name);
				cl2.show(cardPanel2, "btn");
				showRank();
			}
		});
	}

	private void showRank() {
		contentPanel.setPreferredSize(new Dimension(200, 130));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		for(int i = 0; i < 5; i++) {
			setXY(0,i);
			String str = i+1+"�쐞\t\t" + ScoreRanking.rankList.get(i).getName() + "\t\t" + ScoreRanking.rankList.get(i).getScore() + "�젏";
			JLabel rank = new JLabel(str);

			contentPanel.add(rank, gbc);
		}
		gbc.fill = GridBagConstraints.NONE;
		rankPanel.add(contentPanel, gbc);
	}

	private void compInit() {
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		this.setFocusable(true);
		this.requestFocusInWindow();
		background.setLayout(new BorderLayout());

		JLabel gameover = new JLabel(new ImageIcon("resources/gameover.png"));
		imgPanel.add(gameover);


		//		gameover.setOpaque(false);
		//		imgPanel.setOpaque(false);
		//		rankPanel.setOpaque(false);
		//		cardPanel.setOpaque(false);
		//		btnPanel.setOpaque(false);

		
		cardPanel.add("img", imgPanel);
		cardPanel.add("rank", rankPanel);

		//-----------------------------------------------------------
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		for(int i = 0; i < btns.length; i++) {
			btns[i].setPreferredSize(new Dimension(130, 30));
			btns[i].setOpaque(false);
			setXY(0, i);
			btnPanel.add(btns[i], gbc);
		}

		gbc.insets = new Insets(2, 0, 2, 0);
		nameField.setPreferredSize(new Dimension(130, 30));
		setXY(0,0);
		namePanel.add(msg, gbc);
		setXY(0,1);
		namePanel.add(nameField, gbc);
		setXY(0,2);
		namePanel.add(btnOk, gbc);

		
		
		cardPanel2.add("name", namePanel);
		cardPanel2.add("btn", btnPanel);


		background.add(cardPanel2);
		background.add(cardPanel, BorderLayout.NORTH);
		this.getContentPane().add(background,0);
	}

public GameOverNameDialog(int score, Molle parent) {
//	public GameOverDialog(int score) {
		ScoreRanking sr = new ScoreRanking();
		this.mol = parent;
		this.score = score;
		this.setSize(270,390);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		compInit();
		eventInit();
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}
