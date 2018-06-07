package trex;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;



public class GisOver extends JDialog {
	GisOver jself = this;
//	private static final int START_GAME_STATE = 0;
//	private static final int GAME_PLAYING_STATE = 1;
//	private MainCharacter mainCharacter = new MainCharacter();
//	private Land land = new Land(GameWindow.SCREEN_WIDTH, mainCharacter);
//	private EnemiesManager enemiesManager = new EnemiesManager(mainCharacter) ;
//	private Clouds clouds = new Clouds(GameWindow.SCREEN_WIDTH, mainCharacter);
//	private int gameState = START_GAME_STATE;
//	private Thread thread;
	
	
//	GameScreen gs = new GameScreen(self);
	public int re = KeyEvent.VK_SPACE;
	private boolean mouseclick = false;
	
	//GameWindow gw;
	public JButton btn1 = new JButton("1");
	public JButton btn2 = new JButton("2");
	
	KeyEvent key = new KeyEvent(btn1, 0, 0, 0, ' ', ' ') ;
	
	private void compInit() {
		btn1.setSize(new Dimension(50, 25));
		btn2.setSize(new Dimension(50, 25));
		this.add(btn1);
		this.add(btn2);
	}
	 
//	private void resetGame() {
//		enemiesManager.reset();
//		mainCharacter.dead(false);
//		mainCharacter.reset();
//	}
	
	public void eventInit() {
		this.btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			//	gs.resetGame();
				jself.dispose();
				//
			}
		});
	}
	
	
	public GisOver() {
		this.setLayout(new FlowLayout());
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		};
		this.eventInit();
		this.compInit();
		this.setVisible(true);
	}

}
