package Games;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import icecream.Games.HowIce;
import icecream.Games.IcecreamDialog;
import icecream.Games.MyThread;
import icecream.Games.RankingDialog;


public class Game3 extends JPanel {
	private JLabel gameName = new JLabel("ice cream");
	public JButton startBtn = new JButton("시작");
	 public JButton howBtn = new JButton("방법");
	 public JButton rankBtn = new JButton("랭크");
	private Font myFont;
	 private ImageIcon backImg = new ImageIcon("src/Games/res/blackScreen.png"); 
	 private Image img2 = backImg.getImage(); 
	 JPanel panel = new JPanel(new GridBagLayout()) ;
		private ImageIcon gameImg = new ImageIcon("src/Games/res/icecream.png"); 
		private Image img3 = gameImg.getImage(); 
		JPanel gamepanel = new JPanel(new GridBagLayout()) ;
		final Object lock = new Object();
		
	private void compInit() {
		 try {
				myFont= Font.createFont(Font.TRUETYPE_FONT,new File("src/fly/resources/wiggle-hangeul.ttf")).deriveFont(25f);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			this.panel = new JPanel(new GridBagLayout()) {
				 @Override
				public void paintComponent(Graphics g) {
					 super.paintComponent(g);
					 g.drawImage(backImg.getImage(),0,0,null);
				}
			 };
			 this.gamepanel = new JPanel(new GridBagLayout()) {
				 @Override
				public void paintComponent(Graphics g) {
					 super.paintComponent(g);
					 g.drawImage(gameImg.getImage(),0,0,null);
				}
			 };
			 this.gameName.setFont(myFont); 
			 this.panel.setPreferredSize(new Dimension(606, 510));
			this.gamepanel.setPreferredSize(new Dimension(410, 337));
		    this.panel.add(gamepanel);
			this.add(panel);
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets = new Insets(5,3,5,3);
		gbc.gridx =0; gbc.gridy =0;
		gameName.setPreferredSize(new Dimension(130, 30));
		this.gamepanel.add(gameName,gbc);
		//this.add(gameName,gbc);
		gbc.gridx =0; gbc.gridy =1;
		startBtn.setPreferredSize(new Dimension(100, 30));
		this.gamepanel.add(startBtn,gbc);
		//this.add(startBtn,gbc);
		gbc.gridx =0; gbc.gridy =2;
		howBtn.setPreferredSize(new Dimension(130, 30));
		//this.add(startBtn,gbc);
		this.gamepanel.add(howBtn,gbc);
		gbc.gridx =0; gbc.gridy =3;
		rankBtn.setPreferredSize(new Dimension(130, 30));
		//this.add(startBtn,gbc);
		this.gamepanel.add(rankBtn,gbc);
	}

	private void eventInit(SmallGames sg) {
		this.startBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyThread() {
					public void run() {
						MyThread.pause = false;
						MyThread.start = true;
						MyThread.first = true;
						MyThread.sec = 60;
						synchronized(MyThread.lock) {
							MyThread.lock.notifyAll();
						}
						checkPause();
						try {
							checkStart();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						doTask();
					}
					public void doTask() {
						MyThread.pause = false;
						MyThread.start = true;
						MyThread.first = true;
						MyThread.sec = 60;
						synchronized(MyThread.lock) {
							MyThread.lock.notifyAll();
						}
						IcecreamDialog ice = new IcecreamDialog(sg,"Icecream game");
						ice.setVisible(true);
					}
				}.start();
			}
		});
		this.howBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HowIce hi = new HowIce(sg);
			}
		});
		
		this.rankBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RankingDialog();
			}
		});
	}
	
	public Game3(SmallGames sg) {
		
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		compInit();
		eventInit(sg);
		this.setFont(myFont);
	}
}