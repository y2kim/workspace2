package trex;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameScreen extends JPanel implements Runnable, KeyListener , MouseListener {

	private static final int START_GAME_STATE = 0;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	private static final int GAME_PUASE = 3;
	public static int setid = 0 ;
	
	private Land land;
	private MainCharacter mainCharacter;
	private EnemiesManager enemiesManager;
	private Clouds clouds;
	private Thread thread;
	private GisOver gso;
	private GameWindow gwo ;
	private boolean isKeyPressed;

	private int gameState = START_GAME_STATE;

	private BufferedImage replayButtonImage;
	private BufferedImage gameOverButtonImage;

	private JButton btn1 = new JButton("재시작");
	public static boolean deadsure = false;
	public NewGameOverDialog ngod;

	public GameScreen(GameWindow gw) {
		mainCharacter = new MainCharacter();
		land = new Land(GameWindow.SCREEN_WIDTH, mainCharacter);
		mainCharacter.setSpeedX(4);
		replayButtonImage = Resource.getResouceImage("/trex/data/replay_button.png");
		gameOverButtonImage = Resource.getResouceImage("/trex/data/gameover_text.png");
		enemiesManager = new EnemiesManager(mainCharacter);
		clouds = new Clouds(GameWindow.SCREEN_WIDTH, mainCharacter);
		gwo =gw;
	}
/*		GameOverDialog god = new GameOverDialog(this);
		int chomenu = god.setmenu;
		if(chomenu == 1) {
			gameState = GAME_PLAYING_STATE;
			enemiesManager.reset();
			mainCharacter.dead(false);
			mainCharacter.reset();
		}else if(chomenu == 2) {
			System.out.println("");
		}else if(chomenu == 3) {
			System.exit(0);
		}else {
			System.out.println("인생니미 씨발");
		}*/
	public void startGame() {
		
		thread = new Thread(this);
		thread.start();

	}
	
	public void gamePuase() {
		gameState = GAME_OVER_STATE;
	}

	public void gameUpdate() {
		if (gameState == GAME_PLAYING_STATE) {
			clouds.update();
			land.update();
			mainCharacter.update();
			enemiesManager.update();

				//mainCharacter.upScore();
			deadsure = false;
			setid =  1;
			if (enemiesManager.isCollision()) {
				mainCharacter.playDeadSound();
				gameState = GAME_OVER_STATE;
				mainCharacter.dead(true);
				setid = GAME_OVER_STATE;
				deadsure = true;
				GameOverDialog god = new GameOverDialog(this);
				int scorering = mainCharacter.score;
				ngod = new NewGameOverDialog(scorering, gwo);
				//UIManager.put(GameOverDialog);
//				int chomenu = GameOverDialog.showOptionDialog(
//						this, god.jp, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
//						null, god.st, " ");
//				if(chomenu ==0) {
//					gameState = GAME_PLAYING_STATE;
//					resetGame();
//				}else if (chomenu ==1) {
//					System.out.println("2");
//				}else {
//					System.exit(0);
//				}
			
//				String[] st = {"1", "2", "3"};
//				UIManager.put("OptionPane.minimumSize", new Dimension(300,200));
//				int cho = JOptionPane.showOptionDialog(this, "Msg", "Title", JOptionPane.DEFAULT_OPTION,
//						JOptionPane.INFORMATION_MESSAGE, null, st, "");
//				if(cho ==0) {
//					gameState = GAME_PLAYING_STATE;
//					resetGame();
//				}else if (cho ==1) {
//					System.out.println("2");
//				}else {
//					System.exit(0);
//				}
			}
		}	
	}
	
	public void stage() {
		if (gameState == GAME_PLAYING_STATE) {
			deadsure = false;
			if (enemiesManager.isCollision()) {
				deadsure = true;
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(deadsure == true) {
			btn1.setLocation(100, 25);
			btn1.revalidate();
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());

		switch (gameState) {
		case START_GAME_STATE:
			mainCharacter.draw(g);
			break;
		case GAME_PLAYING_STATE:
			
		case GAME_OVER_STATE:
			clouds.draw(g);
			land.draw(g);
			enemiesManager.draw(g);
			mainCharacter.draw(g);
			g.setColor(Color.BLACK);
			g.drawString("Score " + mainCharacter.score, 500, 20);
			g.drawString("" + mainCharacter.states, 100, 20);
			if (gameState == GAME_OVER_STATE) {
				mainCharacter.states = "스페이스바 누르면 재시작 됩니다.";
				g.drawImage(gameOverButtonImage, 200, 30, null);
				g.drawImage(replayButtonImage, 283, 50, null);
				setid =  GAME_OVER_STATE;
			}
			break;
		case GAME_PUASE:
			clouds.draw(g);
			land.draw(g);
			enemiesManager.draw(g);
			mainCharacter.draw(g);
			break;
		}
	}
	
	
	
	@Override
	public void run() {
		
			int fps = 100;
			long msPerFrame = 1000 * 1000000 / fps;
			long lastTime = 0;
			long elapsed;
			int msSleep;
			int nanoSleep;
			long endProcessGame;
			long lag = 0;
			while (true) {
				gameUpdate();
				repaint(); // 중요 
				stage();
				endProcessGame = System.nanoTime();
				elapsed = (lastTime + msPerFrame - System.nanoTime());
				msSleep = (int) (elapsed / 1000000);
				nanoSleep = (int) (elapsed % 1000000);
				if (msSleep <= 0) {
					lastTime = System.nanoTime();
					continue;
				}
				try {
					Thread.sleep(msSleep, nanoSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lastTime = System.nanoTime();

			}
		
	}

	boolean yes = false;

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isKeyPressed) {
			isKeyPressed = true;
			switch (gameState) {
			case START_GAME_STATE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameState = GAME_PLAYING_STATE;
				}
				break;
			case GAME_PLAYING_STATE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					mainCharacter.jump();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					mainCharacter.down(true);
				}
				break;
			case GAME_OVER_STATE:
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameState = GAME_PLAYING_STATE;
					mainCharacter.states = " ";
					resetGame();
					setid =  1;
				}
				//				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				//					gameState = GAME_PLAYING_STATE;
				//					resetGame();
				//				}
				break;
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		if (gameState == GAME_PLAYING_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				mainCharacter.down(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void resetGame() {
		mainCharacter.score = 0;
		enemiesManager.reset();
		mainCharacter.dead(false);
		mainCharacter.reset();
	}
	
	Component contente;
	
	
	
	public void mouseClicked(MouseEvent e) {  //
		//GisOver gso= new GisOver();
//		contente = e.getComponent();
//		System.out.println((contente).toString());
//		try {
//			if( ((JButton)contente).getText().equals("1")) {
//				isKeyPressed = true;
//				if(gameState == GAME_OVER_STATE) {
//					gameState = GAME_PLAYING_STATE;
//					resetGame();
//				}
//			}
//		} catch(Exception e1) {
//			
//		}
		
	}
//MouseEvent evn = new MouseEvent(gso.btn1, MouseEvent.MOUSE_CLICKED, 1, MouseEvent.BUTTON1, 0, 0, 1, false);
	public void mousePressed(MouseEvent e) {  // 단순 버튼이 눌러졌을때
		//GisOver gso= new GisOver();
		//if(!isKeyPressed) {
			//isKeyPressed = true;
		//e = new MouseEvent(gso.btn1, MouseEvent.MOUSE_CLICKED, 1, MouseEvent.BUTTON1, 0, 0, 1, false);
		//contente = e.getComponent();
		//System.out.println(contente);
		
		
			if(gameState == GAME_PLAYING_STATE) {
				gameState = GAME_PUASE;
				System.out.println("puase");
				String[] st = {"게임으로 돌아가기", "메인화면으로", "종료"};
				UIManager.put("OptionPane.minimumSize", new Dimension(300,200));
				int cho = JOptionPane.showOptionDialog(this, "Msg", "Title", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, st, "");
				if(cho ==0) {
					gameState = GAME_PLAYING_STATE;
					//resetGame();
				}else if (cho ==1) {
					//System.out.println("2");
				gwo.dispose();
				}else {
					System.exit(0);
				}
			}else if(gameState == GAME_PUASE) {
				gameState = GAME_PLAYING_STATE;
				System.out.println("start");
			}
		
			
		//}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
