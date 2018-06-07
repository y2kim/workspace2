package kh.semi;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class MenuDialog extends JDialog {
	MenuDialog md = this;
	Molle mol;
	GridBagConstraints gbc = new GridBagConstraints();
	private int x = 0;
	private int y = 0;

	private JPanel background = new JPanel(new GridBagLayout());

	private JPanel btnPanel = new JPanel(new GridBagLayout());

	private JButton btnCon = new JButton("이어하기");
	private JButton btnRe = new JButton("다시하기");
	private JButton btnMenu = new JButton("메인메뉴");
	private JButton btnExit = new JButton("종료하기");

	private JButton[] btns = new JButton[] { btnCon, btnRe, btnMenu, btnExit };

	private Cursor cursor;
	private Image img;

	private void setXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.x = x;
		this.y = y;
	}

	private void eventInit() {
		btnCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				md.dispose();
			}
		});
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				md.dispose();
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mol.dispose();
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

		// -----------------------------------------------------------
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		for (int i = 0; i < btns.length; i++) {
			btns[i].setPreferredSize(new Dimension(130, 30));
			setXY(0, i);
			btnPanel.add(btns[i], gbc);
		}

		background.add(btnPanel);
		this.getContentPane().add(background, 0);
		
		Toolkit tk = Toolkit.getDefaultToolkit();img=tk.getImage("src/kh/semi/resources/Candy.png");
		Point point = new Point(0, 0);cursor=tk.createCustomCursor(img,point,"attack");
		setCursor(cursor);
	}

	

	public MenuDialog(Molle parent) {
		mol = parent;

		this.setSize(270, 200);
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