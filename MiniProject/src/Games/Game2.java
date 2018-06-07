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

import trex.GameWindow;
import trex.HowTrex;
import trex.TrexRanking;

public class Game2 extends JPanel {
	private JLabel gameName = new JLabel("trex");
	 public JButton startBtn = new JButton("시작");
	 public JButton howBtn = new JButton("방법");
	 public JButton rankBtn = new JButton("랭크");
	 private Font myFont;
	 private ImageIcon backImg = new ImageIcon("src/Games/res/blackScreen.png"); 
	 private Image img2 = backImg.getImage(); 
	 JPanel panel = new JPanel(new GridBagLayout()) ;
		private ImageIcon gameImg = new ImageIcon("src/Games/res/trex.png"); 
		private Image img3 = gameImg.getImage(); 
		JPanel gamepanel = new JPanel(new GridBagLayout()) ;
	 
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
		    this.gamepanel.setPreferredSize(new Dimension(362, 120));
		    this.panel.add(gamepanel);
			this.add(panel);
			GridBagConstraints gbc = new GridBagConstraints(); 
			gbc.insets = new Insets(5,3,5,3);
			gbc.gridx =0; gbc.gridy =0;
			gameName.setPreferredSize(new Dimension(100, 30));
			this.gamepanel.add(gameName,gbc);
			//this.add(gameName,gbc);
			gbc.gridx =0; gbc.gridy =1;
			startBtn.setPreferredSize(new Dimension(100, 30));
			//this.add(startBtn,gbc);
			this.gamepanel.add(startBtn,gbc);
			gbc.gridx =-1; gbc.gridy =1;
			howBtn.setPreferredSize(new Dimension(130, 30));
			//this.add(startBtn,gbc);
			this.gamepanel.add(howBtn,gbc);
			gbc.gridx =-2; gbc.gridy =1;
			rankBtn.setPreferredSize(new Dimension(130, 30));
			//this.add(startBtn,gbc);
			this.gamepanel.add(rankBtn,gbc);
	}
	 
	 private void eventInit(SmallGames sg) {
		this.startBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow gw = new GameWindow();
				gw.setVisible(true);
			}
		});
		this.howBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HowTrex ht = new HowTrex(sg);
			}
		});
		
		this.rankBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TrexRanking();
			}
		});
	}
	 
	 public Game2(SmallGames sg) {
	       
	        this.setSize(300, 200);
	        this.setLayout(new GridBagLayout());
	        this.compInit();
	        this.eventInit(sg);
	        this.setFont(myFont);
	}
}
