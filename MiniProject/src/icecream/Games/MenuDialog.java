package icecream.Games;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class MenuDialog extends JDialog {
	MenuDialog md = this;
	IcecreamDialog ice;
	GridBagConstraints gbc = new GridBagConstraints();

	private int x = 0;
	private int y = 0;

	private JPanel background = new JPanel(new GridBagLayout());

	private JPanel btnPanel = new JPanel(new GridBagLayout());

	private JButton btnCon = new JButton("게임으로 돌아가기");
	private JButton btnRe = new JButton("게임 다시시작");
	private JButton btnMenu = new JButton("메뉴로 나가기");
	private JButton btnExit = new JButton("게임종료");

	//private JButton[] btns = new JButton[] {btnCon, btnRe, btnMenu, btnExit};
	private JButton[] btns = new JButton[] {btnRe,btnMenu,btnExit};

	private void setXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.x = x;
		this.y = y;
	}

	private void eventInit() {
		this.addKeyListener(new KeyAdapter() {	// 방향키로 아이스크림 추가 
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if(keycode == KeyEvent.VK_SPACE) {
					MyThread.pause = false;
					synchronized(MyThread.lock) {
						MyThread.lock.notifyAll();
					}
					md.dispose();
				}
			}
		});

		btnCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyThread.pause = false;
				synchronized(MyThread.lock) {
					MyThread.lock.notifyAll();
				}
				md.dispose();
				repaint();
			}
		});
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
				md.dispose();
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyThread.pause = false;
				MyThread.start = true;
				MyThread.first = true;
				MyThread.sec = 60;
				md.dispose();
				ice.dispose();
				
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

	public MenuDialog(IcecreamDialog parent) {
		ice = parent;

		this.setSize(180,150);
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