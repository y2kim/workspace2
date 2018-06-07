package fly.dodge;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JDialog;


public class Dodge extends JDialog implements KeyListener{
	// 자기 자신
	Dodge self = this;
	
	private List<NEnemy> nenList = null; // 북쪽에서 생성되는 공격
	private List<SEnemy> senList = null; // 남쪽에서 생성되는 공격
	private List<WEnemy> wenList = null; // 서쪽에서 생성되는 공격
	private List<EEnemy> eenList = null; // 동쪽에서 생성되는 공격
	private final int ATTACKEACHCP = 9;		 // 생성되는 공격의 수
	
	// 버퍼 이미지
	private BufferedImage bi = null;	// 전체 화면(그림)을 위해 생성
	
	// JDialog의 크기 정보를 저장하기 위한 필드
	private final int JDIALOG_WIDTH  = 430;
	private final int JDIALOG_HEIGHT = 430;
	
	// 우주선의 크기를 저장하기 위한 필드
	private final int UFO_SIZE = 20;
	
	// 우주선이 출력될 위치 정보를 저장하기 위한 필드
	private int uX, uY;
	
	// 우주선의 이미지를 저장하기 위한 필드
	private Image UFO;
	
	// 적들의 공격이 생성되는 주기 설정
	private int attackCount = 0;
	
	// 점수를 설정하기 위한 변수
	private int score = 0;
	private int scoreCount = 0;
	
	// 최종 점수 설정
	private int fScore = 0;
	
	// 방향키
	private boolean up    = false;
	private boolean down  = false;
	private boolean left  = false;
	private boolean right = false;
	
	// 게임 일시정지
	private boolean pause = false;
	// 다시 시작
	private boolean retry = false;
	
	// 게임 시작, 종료
	private boolean start = false, end = false;
	
	// 폰트 설정
	private Font myFont;
	
	// 게임 종료 예외 여부 설정
	private boolean exitGame = false;
	
	// this.init()에 게임이 시작되는 스레드가 존재
	public Dodge() {
		bi = new BufferedImage(JDIALOG_WIDTH, JDIALOG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.setTitle("Dodge");
		this.init();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(JDIALOG_WIDTH, JDIALOG_HEIGHT);
		this.addKeyListener(this);
		this.setLocationRelativeTo(null);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	// 게임에 필요한 정보들을 초기화
	public void init() {
		try {
			UFO = ImageIO.read(this.getClass().getResource("/fly/resources/ufo.png"));
			myFont = Font.createFont(Font.TRUETYPE_FONT,new File("src/fly/resources/wiggle-hangeul.ttf")).deriveFont(25f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 리스트 생성
		nenList = new LinkedList<NEnemy>();
		senList = new LinkedList<SEnemy>();
		wenList = new LinkedList<WEnemy>();
		eenList = new LinkedList<EEnemy>();
		
		// 우주선의 최초 좌표
		uX = 205;
		uY = 205;
		
		// 게임 스레드 생성 후 시작
		new GameStart().start();
	}
	
	// 초기에 그려질 이미지
	public void initdraw() {
		Graphics gs = bi.getGraphics();
		gs.setColor(Color.black);
		gs.fillRect(0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT); // 배경을 흰 색으로 설정 후 다이얼로그 너비, 높이만큼 흰색으로 칠 함
		gs.setColor(Color.white);
		gs.setFont(myFont);
		gs.drawString("게임 시작 : Enter", 125, 50);
		gs.drawString("일시 정지 : Space", 125, 90);
		gs.drawString("조작 방법 : ↑ ↓ ← →", 120, 130);
		gs.drawString("적들의 공격을 피하세요!", 100, 170);
		gs.drawImage(UFO, uX, uY, UFO_SIZE, UFO_SIZE, this);

		Graphics ge = this.getGraphics();
		ge.drawImage(bi, 0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT, this);
	}
	
	// 게임 진행 도중에 그려질 이미지
	public void draw() {
		Graphics gs = bi.getGraphics();
		gs.setColor(Color.black);
		gs.fillRect(0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT); // 배경을 흰 색으로 칠함
		gs.setColor(Color.white);
		gs.setFont(myFont);
		gs.drawString("점 수 : " + score, 170, 30);
		
		// 게임이 끝났을 때
		if(end) {
			score = 0;
			gs.drawString("G A M E     O V E R", 100, 220);
			
			eenList.clear();
			wenList.clear();
			senList.clear();
			nenList.clear();
			
			attackCount = 0;
			scoreCount = 0;
			
			// 게임이 정지되게 함
			pause = true;
			
			uX = 205;
			uY = 205;
		}
		
		// 게임을 다시 시작 할 때
		if(retry) {
			// 점수와 점수를 측정하는 시간을 초기화
			score = 0;
			
			attackCount = 0;
			scoreCount = 0;
			
			eenList.clear();
			wenList.clear();
			senList.clear();
			nenList.clear();
			
			// retry를 다시 거짓으로 해야 실행이 안됨
			retry = false;
			
			uX = 205;
			uY = 205;
		}
		
		// 동, 서, 남, 북에서 적이 이동
		eenMove();
		wenMove();
		senMove();
		nenMove();
		gs.drawImage(UFO, uX, uY, UFO_SIZE, UFO_SIZE, this);
		
		Graphics ge = this.getGraphics(); // 프레임의 그래픽을 불러와서
		ge.drawImage(bi, 0, 0, JDIALOG_WIDTH, JDIALOG_HEIGHT, this); // (gs==bi)-> 그린화면을 다이얼로그에 그림
	}
	
	public class GameStart extends Thread{
		public void run() {
			try {
				Thread.sleep(100);
				initdraw();
				
				while(true) {
					Thread.sleep(10);
					if(start) {
						if(!pause) {
							
							// 1.8초 (1000 -> 1초)
							if(attackCount >= 2000) {
								nenCreate();
								senCreate();
								eenCreate();
								wenCreate();
								attackCount = 0;
							}
							
							// 1초마다 점수가 올라감
							if(scoreCount >= 1000) {
								score++;
								scoreCount = 0;
							}
							
							attackCount += 10;
							scoreCount += 10;
							
							// 충돌 검사
							crashChk();
							
							// 키가 입력되면 캐릭터의 좌표를 바꿈
							keyControl();
							
							// 적이 이동되고 캐릭터가 움직이는것을 그림
							draw();
						}
					}
					
					if(end) {
						GameOverDialog.dodgerank = false;
						new GameOverDialog(fScore, self);
						pauseKeySetting();
					}
					
					if(pause) {
						if(exitGame) {
							// 게임이 재 실행되면 실행됨 ㅠㅠ
						}else {
							new MenuDialog(self);
							pauseKeySetting();
						}
					}
					
					if(exitGame) {
						throw new GameExitException("게임종료");
					}
				}
			}catch(GameExitException e1) {
				return;
			}catch(Exception e2) {}
		}
	}
	
	// 게임 일시정지를 풀 때 제멋대로 안움직이게
	public void pauseKeySetting() {
		if(left) {
			left = false;
		}
		
		if(right) {
			right = false;
		}
		
		if(up) {
			up = false;
		}
		
		if(down) {
			down = false;
		}
	}
	
	// 적의 공격이 북쪽에서 생성 됨
	public void nenCreate() {
		for(int i = 0; i < ATTACKEACHCP; i++) {
			double rx = Math.random() * (JDIALOG_WIDTH - UFO_SIZE);
			double ry = Math.random() * 5;
			NEnemy nen = new NEnemy((int)rx, (int)ry);
			nenList.add(nen);
		}
	}
	
	// 북쪽의 공격이 이동함
	public void nenMove() {
		Graphics gs = bi.getGraphics();
		for(int i = 0; i < nenList.size(); i++) {
			NEnemy e = nenList.get(i);
			gs.setColor(Color.yellow);
			gs.drawOval(e.getX(), e.getY(), e.getW(), e.getH());
			gs.fillOval(e.getX(), e.getY(), e.getW(), e.getH());
			if(e.getY() > JDIALOG_HEIGHT)
				nenList.remove(i);
			e.moveEnemy();
		}
	}
	
	// 적의 공격이 남쪽에서 생성 됨
	public void senCreate() {
		for(int i = 0; i < ATTACKEACHCP; i++) {
			double rx = Math.random() * (JDIALOG_WIDTH - UFO_SIZE);
			double ry = (Math.random() * UFO_SIZE) + (JDIALOG_HEIGHT - UFO_SIZE);
			SEnemy sen = new SEnemy((int)rx, (int)ry);
			senList.add(sen);
		}
	}
	
	// 남쪽의 공격이 이동함
	public void senMove() {
		Graphics gs = bi.getGraphics();
		for(int i = 0; i < senList.size(); i++) {
			SEnemy e = senList.get(i);
			gs.setColor(Color.yellow);
			gs.drawOval(e.getX(), e.getY(), e.getW(), e.getH());
			gs.fillOval(e.getX(), e.getY(), e.getW(), e.getH());
			if(e.getY() < 0)
				senList.remove(i);
			e.moveEnemy();
		}
	}
	
	// 동쪽에서 공격이 생성됨
	public void eenCreate() {
		for(int i = 0; i < ATTACKEACHCP; i++) {
			double rx = (Math.random() * UFO_SIZE) + (JDIALOG_WIDTH - UFO_SIZE);
			double ry = Math.random() * (JDIALOG_HEIGHT - UFO_SIZE);
			EEnemy een = new EEnemy((int)rx, (int)ry);
			eenList.add(een);
		}
	}
	
	// 동쪽의 공격이 이동함
	public void eenMove() {
		Graphics gs = bi.getGraphics();
		for(int i = 0; i < eenList.size(); i++) {
			EEnemy e = eenList.get(i);
			gs.setColor(Color.yellow);
			gs.drawOval(e.getX(), e.getY(), e.getW(), e.getH());
			gs.fillOval(e.getX(), e.getY(), e.getW(), e.getH());
			if(e.getX() <  0)
				eenList.remove(i);
			e.moveEnemy();
		}
	}
	
	// 서쪽에서 공격이 생성됨
	public void wenCreate() {
		for(int i = 0; i< ATTACKEACHCP; i++) {
			double rx = Math.random() * 5;
			double ry = Math.random() * (JDIALOG_HEIGHT - UFO_SIZE);
			WEnemy wen = new WEnemy((int)rx, (int)ry);
			wenList.add(wen);
		}
	}
	
	// 서쪽의 공격이 이동
	public void wenMove() {
		Graphics gs = bi.getGraphics();
		for(int i = 0; i < wenList.size(); i++) {
			WEnemy e = wenList.get(i);
			gs.setColor(Color.yellow);
			gs.drawOval(e.getX(), e.getY(), e.getW(), e.getH());
			gs.fillOval(e.getX(), e.getY(), e.getW(), e.getH());
			if(e.getX() > JDIALOG_WIDTH)
				wenList.remove(i);
			e.moveEnemy();
		}
	}
	
	// 충돌 검사 p는 우주선
	public void crashChk() {
		Polygon p = null;
		for (int i = 0; i < nenList.size(); i++) {
			NEnemy e = nenList.get(i);
			int[] xpoints = { uX, (uX + UFO_SIZE), (uX + UFO_SIZE), uX};
			int[] ypoints = { uY, uY, (uY + UFO_SIZE), (uY + UFO_SIZE) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.getX(), (double) e.getY(), (double) e.getW(), (double) e.getH())) {
				//최종 점수를 설정하고 게임을 끝나게 함
				fScore = score;
				nenList.remove(i);
				start = false;
				end = true;
			}
		}

		for (int i = 0; i < senList.size(); i++) {
			SEnemy e = senList.get(i);
			int[] xpoints = { uX, (uX + UFO_SIZE), (uX + UFO_SIZE), uX};
			int[] ypoints = { uY, uY, (uY + UFO_SIZE), (uY + UFO_SIZE) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.getX(), (double) e.getY(), (double) e.getW(), (double) e.getH())) {
				//최종 점수를 설정
				fScore = score;
				senList.remove(i);
				start = false;
				end = true;
			}
		}

		for (int i = 0; i < wenList.size(); i++) {
			WEnemy e = wenList.get(i);
			int[] xpoints = { uX, (uX + UFO_SIZE), (uX + UFO_SIZE), uX};
			int[] ypoints = { uY, uY, (uY + UFO_SIZE), (uY + UFO_SIZE) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.getX(), (double) e.getY(), (double) e.getW(), (double) e.getH())) {
				//최종 점수를 설정
				fScore = score;
				wenList.remove(i);
				start = false;
				end = true;
			}
		}

		for (int i = 0; i < eenList.size(); i++) {
			EEnemy e = eenList.get(i);
			int[] xpoints = { uX, (uX + UFO_SIZE), (uX + UFO_SIZE), uX };
			int[] ypoints = { uY, uY, (uY + UFO_SIZE), (uY + UFO_SIZE) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.getX(), (double) e.getY(), (double) e.getW(), (double) e.getH())) {
				//최종 점수를 설정
				fScore = score;
				eenList.remove(i);
				start = false;
				end = true;
			}
		}
	}
	
	public void keyTyped(KeyEvent e) {}

	// 키를 눌렸을 때 true로 설정
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
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
	
	// 키를 뗏을 때 false로 설정 
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_UP:
				up = false;
				break;
		}
	}
	
	// 키가 눌렸을때 우주선이 움직이게 함
	public void keyControl() {
		// 우주선이 다이얼로그의 너비를 넘지 않도록
		if(0 < uX) {
			if(left)
				uX -= 3;
		}
		
		// 우주선이 다이얼로그의 너비를 넘지 않도록
		if(JDIALOG_WIDTH > uX + UFO_SIZE) {
			if(right)
				uX += 3;
		}
		
		// 우주선이 다이얼로그의 높이를 넘지 않도록
		if(25 < uY) {
			if(up)
				uY -= 3;
		}
		
		if(JDIALOG_HEIGHT > uY + UFO_SIZE) {
			if(down)
				uY += 3;
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
	
	// 외부에서 호출했을떄 스레드를 종료
	public void setExit() {
		exitGame = true;
	}
}