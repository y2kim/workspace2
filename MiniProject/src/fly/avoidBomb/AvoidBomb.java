package fly.avoidBomb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JDialog;

import Games.SmallGames;

public class AvoidBomb extends JDialog implements KeyListener {
	// 자기 자신
	AvoidBomb self = this;
	
	// 버퍼이미지
	private BufferedImage bi = null;
	
	// JDialog의 크기 정보를 저장하기 위한 필드
	private final int JDIALOG_WIDTH = 400;
	private final int JDIALOG_HEIGHT = 400;

	// 군인의 크기를 저장하기위한 필드
	private final int SOLDIER_SIZE = 20;

	// 군인이 출력될 위치 정보를 저장하기 위한 필드
	private int sX, sY;

	// 군인,폭발 이미지를 저장히기 위한 필드
	private Image soldier;
	private Image explosion;
	private Image bomb;

	// 폭탄이 떨어지는 주기 설정
	private int bombCount = 0;
	
	// 점수를 설정하기 위한 변수
	private int score = 0;
	private int scoreCount = 0;
	
	// 최종 점수 설정
	private int fScore = 0;
	
	// 난이도를 설정하기 위한 변수
	private int level = 1;
	private int levelCount = 0;
	
	// 방향키
	private boolean left = false;
	private boolean right = false;
	
	// 일시정지
	private boolean pause = false;
	// 다시시작
	private boolean retry = false;
	
	// 게임 시작, 종료 
	private boolean start = false, end = false;
	
	// 폭탄 정보를 여러 개 저장하기 위한 필드 및 인스턴스 생성
	private List<Bomb> bombList;
	
	// 폭발 사이즈
	private int expSize = 60;
	
	// 폰트 설정
	private Font myFont;
	
	// 게임 종료 예외 여부 설정
	private boolean exitGame = false;
	
	
	// 생성자 init메서드 안에서 스레드 시작
	public AvoidBomb(SmallGames sg) {
		bi = new BufferedImage(JDIALOG_WIDTH, JDIALOG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.setTitle("avoid Bomb");
		this.setFocusable(true);
		this.init();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(JDIALOG_WIDTH,JDIALOG_HEIGHT);
		this.addKeyListener(this);
		this.setLocationRelativeTo(sg);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	public void init() {
		try {
			// 군인 이미지와 폭탄 이미지를 읽어들임
			soldier = ImageIO.read(this.getClass().getResource("/fly/resources/soldier.png"));
			bomb = ImageIO.read(this.getClass().getResource("/fly/resources/bomb.png"));
			explosion = ImageIO.read(this.getClass().getResource("/fly/resources/explosion.png"));
			myFont= Font.createFont(Font.TRUETYPE_FONT,new File("src/fly/resources/wiggle-hangeul.ttf")).deriveFont(25f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 폭탄 배열을 위한 변수 선언
		bombList = new LinkedList<Bomb>();

		// 군인의 최초 좌표
		sX = 190;
		sY = 375;

		// 게임 스레드 생성후 시작
		new GameStart().start();
	}
	
	// 초기 화면
	public void initdraw() {
		Graphics gs = bi.getGraphics();
		gs.setColor(new Color(33,133,24));
		gs.fillRect(0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT); // 배경을 초록색으로 설정 후 다이얼로그 너비, 높이만큼 흰색으로 칠 함
		gs.setColor(Color.white);
		gs.setFont(myFont);
		gs.drawString("게임 시작 : Enter", 100, 50);
		gs.drawString("일시 정지 : Space", 95, 90);
		gs.drawString("조작 방법 : ← →", 105, 130);
		gs.drawString("떨어지는 폭탄을 피하세요!", 60, 170);
		gs.drawImage(soldier, sX,sY,SOLDIER_SIZE,SOLDIER_SIZE,this);
		
		Graphics ge = this.getGraphics();
		ge.drawImage(bi, 0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT, this);
	}
	
	// 게임중 실행되는 화면
	public void draw() {
		Graphics gs = bi.getGraphics();
		gs.setColor(new Color(33,133,24));
		gs.fillRect(0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT); // 배경을 초록색으로 설정 후 다이얼로그 너비, 높이만큼 흰색으로 칠 함
		gs.setFont(myFont);
		gs.setColor(Color.white);
		gs.drawString("점 수 : " + score, 150, 30);
		
		// 게임 종료시 그려지는 그림
		if(end) {
			// 점수를 0점으로 초기화
			score = 0;
			gs.drawString("G A M E   O V E R", 95, 210);
			// 화면에 있는 폭탄들을 제거
			bombList.clear();
			
			// 모든 값들을 초기화
			levelCount = 0;
			scoreCount = 0;
			bombCount = 0;
			
			// 게임이 정지 되게 함
			pause = true;
			// 군인의 초기 위치 지정
			sX = 190;
			sY = 375;
			// 게임 레벨을 1로
			level = 1;
		}
		
		if(retry) {
			// 점수와 점수를 측정하는 시간을 초기화
			score = 0;
			
			// 모든 값들을 초기화
			levelCount = 0;
			scoreCount = 0;
			bombCount = 0;
			
			bombList.clear();
			
			sX = 190;
			sY = 375;
			
			// retry를 다시 거짓으로 해야 실행이 안됨
			retry = false;
			
			// 게임 레벨을 1로
			level = 1; 
		}
		
		gs.drawImage(soldier, sX,sY,SOLDIER_SIZE,SOLDIER_SIZE,this);
		
		// 폭탄이 이동함
		bombMove();
		
		Graphics ge = this.getGraphics();
		ge.drawImage(bi, 0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT, this);
	}
	
	// 폭탄 이동
	public void bombMove() {
		Graphics gs = bi.getGraphics();
		for (int i = 0; i < bombList.size(); i++) {
			Bomb tmpBomb = bombList.get(i);
			
			// expSize 는 폭발 사진의 크기임
			if(tmpBomb.getY() > JDIALOG_HEIGHT - 40) {
				gs.drawImage(explosion, tmpBomb.getX()-expSize/4, tmpBomb.getY()-expSize/4, expSize, expSize, this);
			}else {
				gs.drawImage(bomb,tmpBomb.getX(), tmpBomb.getY(), tmpBomb.getBs(), tmpBomb.getBs(),this);
			}
			if (tmpBomb.getY() > JDIALOG_HEIGHT) {			
				bombList.remove(i);
			}
			
			// 폭탄이 덜어짐
			tmpBomb.fall();
		}	
	}

	public class GameStart extends Thread {
		public void run() {
			try {
				Thread.sleep(100);
				initdraw();
				
				while (true) {
					Thread.sleep(10);
					if(start) {
						if (!pause) {
							// 일정 주기가 되면 폭탄 생성
							if(bombCount >= 650/level) {
								bombCreate();
								bombCount = 0;
							}
							
							// 1초마다 점수 증가
							if(scoreCount >= 1000) {
								score++;
								scoreCount = 0;
							}
							
							// 2초가 지날때마다 난이도 증가
							if(levelCount >= 2000) {
								if(level <= 19) {
									level++;
								}
								levelCount = 0;
							}
							
							bombCount += 10;
							scoreCount += 10;
							levelCount += 10;
							
							crashChk();
							draw();
							keyControl();
						}
					}
					
					// 게임이 끝났을 때
					if(end) {
						GameOverDialog.avoidrank = false;
						new GameOverDialog(fScore, self);
						pauseKeySetting();
					}
					
					if(pause) {
						if(exitGame) {
							// 게임이 재 실행되면 실행되는데..
						}else {
							new MenuDialog(self);
							pauseKeySetting();
						}
					}
					
					if(exitGame) {
						throw new GameExitException("게임종료");
					}
				}
			}catch(GameExitException e) {
				return;
			}catch(Exception e) {}
		}
	}
	
	// 일시정지 해제후 제멋대로 움직임을 방지하기 위해
	public void pauseKeySetting() {
		if(left) {
			left = false;
			sX += 3;
		}
		
		if(right) {
			right = false;
			sX -= 3;
		}
	}

	// 폭탄 생성
	public void bombCreate() {
		int rx = (int) (Math.random() * (JDIALOG_WIDTH - 10));
		int ry = 0;
		Bomb tmpBomb = new Bomb(rx, ry);
		bombList.add(tmpBomb);
	}
	
	// 군인이 폭탄에 맞았는지 검사
	public void crashChk() {
		Polygon p = null;
		
		for(int i = 0; i < bombList.size(); i++) {
			Bomb tmpBomb = bombList.get(i);
			int[] xpoints = {sX, (sX+SOLDIER_SIZE), (sX+SOLDIER_SIZE), sX};
			int[] ypoints = {sY, sY, (sY+SOLDIER_SIZE), (sY+SOLDIER_SIZE)};
			p = new Polygon(xpoints,ypoints,4);
			if(p.intersects((double)tmpBomb.getX(),(double)tmpBomb.getY(),(double)tmpBomb.getKr(),(double)tmpBomb.getKr())) {
				// 부딪히면 최종 점수를 저장
				fScore = score;
				bombList.remove(i);
				
				start = false;
				//부딪히면 게임 종료
				end = true;
			}
		}
	}

	// 사용X
	public void keyTyped(KeyEvent e) {}

	// 좌, 우, 일시정지, 게임시작
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_SPACE:
				pause = true;
				break;
			case KeyEvent.VK_ENTER:
				start = true;
				end = false;
				break;
		}
	}

	// 좌, 우 키에서 손을 떼면 군인이 움직이지 못 하도록 설정
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
		}
	}
	
	public void keyControl() {
		// 다이얼로그의 왼 쪽으로 접근하지 못하게 함
		if(5 < sX) {									// 5로 설정한이유는 끝에 걸치기 때문에
			if(left)
				sX -= 3;
		}
		
		if(JDIALOG_WIDTH-5 > sX + SOLDIER_SIZE) {		// 5를 뺀 이유는 끝에 걸치기 떄문에
			if(right)
				sX += 3;
		}
	}

	// 외부에서 호출 했을 때 일시정지
	public void setPause() {
		this.pause = false;
	}
	
	// 외부에서 호출 했을 때 다시시작
	public void setRetry() {
		this.retry = true;
	}
	
	// 게임 다시 시작
	public void setStart() {
		start = true;
		end = false;
	}
	
	// 게임 종료 예외 던지기
	public void setExit() {
		exitGame = true;
	}
}
