package snakegame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	   private final int B_WIDTH = 500;
	   private final int B_HEIGHT = 500;
	   private final int OFFSET_W = 30; // target 
	   private final int OFFSET_H = 30;
	   private final int DOT_SIZE = 30;
	   private final int ALL_DOTS = 277;
	   private final int RAND_POS = 29;
	   private final int DELAY = 8;

	   private final int x[] = new int[B_WIDTH -1]; 
	   private final int y[] = new int[B_HEIGHT -1];

	   private int dots;
	   private int score;
	   private int targetX, targetY;
	   private int bodyX, bodyY;

	   private boolean leftDirection = false;
	   private boolean rightDirection = true;
	   private boolean upDirection = false;
	   private boolean downDirection = false;
	   private boolean inGame = true;
	   
	   private Snake snake;
	   private Timer timer;
	   private Image body;
	   private Image target;
	   private Image head;
	   private Image back;

	   public Board(Snake parent) {
	      initBoard();
	      snake = parent;
	   }

	   private void initBoard() {

	      addKeyListener(new TAdapter());
	      setFocusable(true);
	      setDoubleBuffered(true);
	      setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
	      loadImages();
	      initGame();
	   }

	   private void loadImages() { 

	      ImageIcon iiDot = new ImageIcon("src/snakegame/resources/dirt33.png");
	      body = iiDot.getImage();

	      ImageIcon iiTarget= new ImageIcon("src/snakegame/resources/dirt33.png");
	      target = iiTarget.getImage();

	      ImageIcon iiHead = new ImageIcon("src/snakegame/resources/bunnyright.png");
	      head = iiHead.getImage();
	      
	      ImageIcon iiBack = new ImageIcon("src/snakegame/resources/night500.png");
	      back = iiBack.getImage();   
	   }

	   private void initGame() {
	      
	      dots = 1;
	      score = 0;
	      for (int z = 0; z < dots; z++) {
	         x[z] = 50 - z * 10;
	         y[z] = 50;
	      }
	      locateTarget();

	      timer = new Timer(DELAY, this);
	      timer.start();
	   }

	   @Override
	   public void paintComponent(Graphics g) {
	      g.drawImage(back, 0, 0, null);
	      this.setOpaque(false);
	      super.paintComponent(g);
	      doDrawing(g);
	   }

	   private void doDrawing(Graphics g) {

	      if (inGame) {
	         g.setColor(Color.WHITE);
	         g.setFont(new Font("Helvetica", Font.BOLD, 16));   
	         String score = "SCORE: " + this.score;
	         g.drawString(score, (B_WIDTH / 2) - OFFSET_W,  OFFSET_H);   
	         
	         g.drawImage(target, targetX, targetY, this);         
	         int rightCount = -5;
	         int leftCount = 5;
	         int upCount = 3;
	         int downCount = -3;
	         
	         for (int z = 0; z < dots; z++) {
	            if (z == 0) {
	               g.drawImage(head, x[z], y[z], this);
	            }else if(z == 1) {
	               g.drawImage(body, x[z] + bodyX, y[z] + bodyY, null);
	            }else{         
	               if(rightDirection) {
	                  g.drawImage(body, x[z] + (bodyX + (rightCount-=5)), y[z] + bodyY, null);
	               }else if(leftDirection) {
	                  g.drawImage(body, x[z] + (bodyX + (leftCount+=5)), y[z] + bodyY, null);
	               }else if(upDirection) {
	                  g.drawImage(body, x[z] + bodyX, y[z] + bodyY + (upCount+=5), null);
	               }else if(downDirection) {
	                  g.drawImage(body, x[z] + bodyX, y[z] + bodyY + (downCount-=5), null);
	               }
	            }
	         }
	         Toolkit.getDefaultToolkit().sync();
	      } else {
//	         gameOver(g);
	      }        
	   }

	   private void gameOver(Graphics g) {
	      String msg = "Game Over";
	      Font small = new Font("Helvetica", Font.BOLD, 14);
	      FontMetrics metr = getFontMetrics(small);
	      g.setColor(Color.black);
	      g.setFont(small);      
	      g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
	   }

	   private void checkTarget() {

	      if ((x[0] <= targetX + 15 && targetX + 15 <= x[0]+ 28) && (y[0] <= targetY + 15 && targetY + 15 <= y[0] + 40)) {
	         dots++;
	         locateTarget();
	         score += 10;
	      }
	   }

	   private void move() {

	      for (int z = dots; z > 0; z--) {
	         x[z] = x[(z - 1)];
	         y[z] = y[(z - 1)];
	      }

	      if (leftDirection) {
	         x[0] -= DOT_SIZE/3;
	      }

	      if (rightDirection) {
	         x[0] += DOT_SIZE/3;
	      }

	      if (upDirection) {
	         y[0] -= DOT_SIZE/3;
	      }

	      if (downDirection) {
	         y[0] += DOT_SIZE/3;
	      }
	   }

	   private void checkCollision() {

	      for (int z = dots; z > 0; z--) {
	         if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
	            inGame = false;
	            new GameOverDialog(score, snake);
	         }
	      }

	      if (y[0] >= B_HEIGHT) {
	         inGame = false;
	         new GameOverDialog(score, snake);
	      }

	      if (y[0] < 0) {
	         inGame = false;
	         new GameOverDialog(score, snake);
	      }

	      if (x[0] >= B_WIDTH) {
	         inGame = false;
	         new GameOverDialog(score, snake);
	      }

	      if (x[0] < 0) {
	         inGame = false;
	         new GameOverDialog(score, snake);
	      }

	      if (!inGame) {
	         timer.stop();
	        
	      }
	   }

	   private void locateTarget() {

	      int r = (int) (Math.random() * RAND_POS);
	      targetX = ((r * 10));

	      r = (int) (Math.random() * RAND_POS);
	      targetY = ((r * 10));
	   }

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      if (inGame) {

	         checkTarget();
	         checkCollision();
	         move();
	      }
	      repaint();
	   }

	   private class TAdapter extends KeyAdapter {
	      @Override
	      public void keyPressed(KeyEvent e) {
	         try {
	            int key = e.getKeyCode();

	            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
	               leftDirection = true;
	               upDirection = false;
	               downDirection = false;
	               head = ImageIO.read(new File("src/snakegame/resources/bunnyleft.png"));
	               bodyX = 18; bodyY = 18;
	            }

	            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
	               rightDirection = true;
	               upDirection = false;
	               downDirection = false;
	               head = ImageIO.read(new File("src/snakegame/resources/bunnyright.png"));
	               bodyX = -18; bodyY = 16;
	            }

	            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
	               upDirection = true;
	               rightDirection = false;
	               leftDirection = false;
	               head = ImageIO.read(new File("src/snakegame/resources/bunnyup.png"));
	               bodyX = 20; bodyY = 20;
	            }

	            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
	               downDirection = true;
	               rightDirection = false;
	               
	               leftDirection = false;
	               head = ImageIO.read(new File("src/snakegame/resources/bunnydown.png"));
	               bodyX = 0; bodyY = -8;
	            }
	         }catch(Exception e1) {
	            e1.printStackTrace();
	         }
	      }
	   }
	}