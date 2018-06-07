package tetris;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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

public class MenuDialog extends JDialog {
	MenuDialog md = this;
	Tetris tetris;
	GridBagConstraints gbc = new GridBagConstraints();
	private int x = 0;
	private int y = 0;

	private JPanel background = new JPanel(new GridBagLayout());
	
	private JPanel btnPanel = new JPanel(new GridBagLayout());

	private JButton btnCon = new JButton("게임으로 돌아가기");
	private JButton btnRe = new JButton("게임 다시시작");
	private JButton btnMenu = new JButton("메뉴로 나가기");
	private JButton btnExit = new JButton("게임종료");

	private JButton[] btns = new JButton[] {btnCon, btnRe, btnMenu, btnExit};

	private void setXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.x = x;
		this.y = y;
	}

	private void eventInit() {
		btnCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tetris.dispose();
				md.dispose();
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
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

		//-----------------------------------------------------------
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		for(int i = 0; i < btns.length; i++) {
			btns[i].setPreferredSize(new Dimension(130, 30));
			setXY(0, i);
			btnPanel.add(btns[i], gbc);
		}
		
		background.add(btnPanel);
		this.getContentPane().add(background,0);
	}

public MenuDialog(Tetris parent) {
		tetris = parent;
		
		this.setSize(270,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		compInit();
		eventInit();
		this.setModal(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}