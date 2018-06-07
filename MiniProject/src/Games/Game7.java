package Games;

import java.awt.Color;
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

import kh.semi.HowCatch;
import snakegame.HowSnake;
import snakegame.RankingDialog;
import snakegame.ScoreRanking;
import snakegame.Snake;



public class Game7 extends JPanel{
	
	private JLabel gameName = new JLabel("snake");
	public JButton startBtn = new JButton("시작");
	 public JButton howBtn = new JButton("방법");
	 public JButton rankBtn = new JButton("랭크");
	private Font myFont;
	 private ImageIcon backImg = new ImageIcon("src/Games/res/blackScreen.png"); 
	 private Image img2 = backImg.getImage(); 
	 JPanel panel = new JPanel(new GridBagLayout()) ;
	private ImageIcon gameImg = new ImageIcon("src/Games/res/bunake3.png"); 
	private Image img3 = gameImg.getImage(); 
	JPanel gamepanel = new JPanel(new GridBagLayout()) ;
		
	private void compInit() {
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets = new Insets(5,0,5,0);
		 try {
				myFont= Font.createFont(Font.TRUETYPE_FONT,new File("src/fly/resources/wiggle-hangeul.ttf")).deriveFont(25f);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 this.gameName.setFont(myFont);
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
			 this.panel.setPreferredSize(new Dimension(606, 510));
		     this.gamepanel.setPreferredSize(new Dimension(390, 385));
		     this.panel.add(gamepanel);
			this.add(panel);
 
			gbc.gridx =0; gbc.gridy =0;
			gameName.setPreferredSize(new Dimension(150, 40));
			this.gamepanel.add(gameName,gbc);
			//this.add(gameName,gbc);
			gbc.gridx =0; gbc.gridy =1;
			startBtn.setPreferredSize(new Dimension(150, 40));
			//this.add(startBtn,gbc);
			this.gamepanel.add(startBtn,gbc);
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
				Snake sn = new Snake();
				sn.setVisible(true);
			}
		});
		this.howBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HowCatch hc = new HowCatch(sg);
			}
		});
		
		this.rankBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RankingDialog();
			}
		});
	}
	
	public Game7(SmallGames sg) {
		
		this.setSize(400, 300);
		this.setLayout(new GridBagLayout());
		compInit();
		eventInit(sg);
		this.setFont(myFont);
	}

}
