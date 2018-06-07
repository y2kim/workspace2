package trex;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameWindow extends JDialog{
	GameWindow self =this;
	public static final int SCREEN_WIDTH = 600;
	private GameScreen gamescreen = new GameScreen(self);
	private JButton bttn = new JButton("23");
	private GisOver gio ;
	
	public GameWindow() {
		super();
		this.setSize(SCREEN_WIDTH, 200);
		//setLocation(400, 200);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModal(true);
		setUndecorated(true); 
		//this.setUndecorated(true);
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stopGame(); 
				self.dispose();
				self.setModal(false);
			}
		};
		this.addWindowListener(exit);
		this.setResizable(false);
		this.addKeyListener(gamescreen);
		this.addMouseListener(gamescreen);
		this.add(gamescreen);
		this.startGame();
		System.out.println();
		//this.setVisible(true);
	}
	
	public void startGame() {
		gamescreen.startGame();
	}
	
	public void stopGame() {
		gamescreen.gamePuase();
	}
	
	
	public static void main(String[] args) {
		//GameWindow gw = new GameWindow();
		//gw.setVisible(true);
		//gw.startGame();
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				//new GameWindow();
			}
		});
	}
}
