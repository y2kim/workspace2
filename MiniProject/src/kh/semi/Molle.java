package kh.semi;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Games.SmallGames;

public class Molle extends JDialog {
   
   private Molle self = this;
   
   private static final String OFF_STRING = "   ";
   // 버튼에 올라왔을때
   private static final String UP_STRING = "(^-^)";
   // 올라온 버튼 눌렀을때
   private static final String HIT_STRING = "(XoX)";   
   // 기본 버튼색
   private static final Color OFF_COLOR = Color.LIGHT_GRAY;
   // 올라올때 버튼색
   private static final Color UP_COLOR = new Color((float) 0.118, (float) 0.565, (float) 1.000);
   // 눌렀을때 버튼색
   private static final Color HIT_COLOR = new Color((float) 1, (float) 0.388, (float) 0.278);
   // 버튼의 갯수
   private static final int BUTTON_NUM = 42;
   // 점수와 시간
   private int count, score;
   // 배열처리
   private JButton[] buttons;
   // mole쓰레드배열
   private Thread[] moleThreads = new Thread[BUTTON_NUM];
   // 타이머
   private JTextField timeText = new JTextField(5);
   // 점수
   private JTextField scoreText = new JTextField(5);
   // 스윙GUI프레임
   private JFrame window;
   // 컨텐트 페인
   private JPanel pane = new JPanel();
   // 게임시작 남은시간 점수가 나오는 타이틀 패널
   private JPanel title = new JPanel();
   private JPanel buttonsPane = new JPanel();
   //시작버튼
   private JButton start = new JButton();
   // 남은시간 라벨
   private JLabel timeLeft = new JLabel();
   // 점수라벨
   private JButton puase = new JButton("일시정지");
   // 일시중지 버튼
   private Boolean pause = false;
   // 불린값으로 일시정지
   private JLabel scoreBoard = new JLabel();
   // 랜덤으로 생성되는 것
   private Random random = new Random();
   // 폰트
   private Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);
   // 커서 이미지
   private Cursor cursor;
   
   private Image img;
   
   //바탕화면   src/kh/semi/resources/Candy.png
   private ImageIcon backImg = new ImageIcon("src/kh/semi/resources/backBottom.png"); 
   private Image img2 = backImg.getImage(); 

   private ImageIcon backImg2 = new ImageIcon("src/kh/semi/resources/backTop.png");
   private Image img3 = backImg2.getImage(); 

   public int getScore() {
      return score;
   }

   public JButton getStart() {
      return start;
   }

   public void setStart(JButton start) {
      this.start = start;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public JTextField getScoreText() {
      return scoreText;
   }

   public void setScoreText(JTextField scoreText) {
      this.scoreText = scoreText;
   }
   public void compInit() {  
	   
	   this.buttonsPane = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img2, 0, 0, this);
			}
		};
		
		this.title = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img3, 0, 0, this);
			}
		};
	   

      this.buttonsPane.setLayout(new GridLayout(7, 7)); // 버튼패널에 레이아웃
      this.title.setBorder(new EmptyBorder(20, 10, 0, 10));
      this.buttonsPane.setBorder(new EmptyBorder(0, 20, 20, 20));
      this.pane.add(title); // 패널에 타이틀추가
      this.pane.add(buttonsPane); //패널에 버튼패널추가
      
      this.setContentPane(pane);

      this.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
      this.setResizable(false);     

      Toolkit tk = Toolkit.getDefaultToolkit();
      img = tk.getDefaultToolkit().getImage("src/kh/semi/resources/Candy.png");;      
      Point point = new Point(0, 0);
      cursor = tk.createCustomCursor(img, point, "attack");
      setCursor(cursor);
   }

   public void eventInit() {
      
      Font myFont = null;      
      try {
         //URL imageURL = getClass().getClassLoader().getResource("wigle.ttf");
         myFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/kh/semi/resources/wiggle.ttf")).deriveFont(10f);
      } catch (Exception e) {
         e.printStackTrace();
      }   
      
      this.start = new JButton("게임 시작");
      this.start.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            start.setEnabled(false);
            Thread timer = new TimerThread();
            timer.start();
            for (int i = 0; i < BUTTON_NUM; i++) {
               moleThreads[i] = new HelperThread(buttons[i]);
               moleThreads[i].start();
            }
         }
      });
      this.puase.addActionListener(new ActionListener() {         
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new MenuDialog(self);            
         }
      });
      
      
      title.add(start);
      // title time      
      timeLeft = new JLabel("           남은 시간: ");
      title.add(timeLeft); // 타이틀에 남은 시간 라벨 추가
      timeText.setEditable(false); // 텍스트 수정못하게
      timeText.setHorizontalAlignment(SwingUtilities.CENTER);
      title.add(timeText); // 타이틀에 시간 추가
      // title score
      scoreBoard = new JLabel("        캡쳐 성공: ");
      title.add(scoreBoard); // 타이틀에 점수 라벨 추가
      scoreText.setEditable(false); //텍스트 수정 못하게
      scoreText.setHorizontalAlignment(SwingUtilities.CENTER);
      this.timeLeft.setFont(myFont);
      this.scoreBoard.setFont(myFont);
      title.add(scoreText); // 타이틀패널에 점수 추가
      title.add(puase); // 타이틀에 일시정지 버튼 추가
      // buttons
      buttons = new JButton[BUTTON_NUM];
      for (int i = 0; i < buttons.length; i++) { // 버튼의 길이만큼 반복(49)
         buttons[i] = new JButton(OFF_STRING); // off스트링상태의 버튼배열을 만듬         
         buttons[i].setBackground(OFF_COLOR); //  버튼배열에 백그라운드색을offcolor로함
         buttons[i].setFont(font); // 버튼배열에 모노스페이스 폰트 설정함
         buttons[i].setOpaque(true); // 버튼배경색 트루가 켬
         //buttons[i].setPreferredSize(new Dimension(50,30));
         buttonsPane.add(buttons[i]); // 버튼패널에 버튼배열을 넣음.
         JButton moleButton = buttons[i]; // 버튼패널을 mole버튼이라고 선언
         moleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (count > 0) { // 점수가 0보다 클때
                  if (moleButton.getText().equals(UP_STRING)) {
                     // 웃는모습 불러옴
                     score++;
                     // 점수증가
                     scoreText.setText("" + score);
                     // 점수를+1해줌
                     moleButton.setText(HIT_STRING);
                     // 버튼을 누르면 눌럿을때 텍스트 불러옴
                     moleButton.setBackground(HIT_COLOR);
                     // 눌렸을때 색 변환
                  } else {
                     score--; // 잘못눌렀을대 점수 감소
                     scoreText.setText("" + score);
                     // 감소한 점수 적용
                     moleButton.setBackground(OFF_COLOR);
                     // 불꺼짐
                  }
               }
            }
         });
      }
   }

   private class HelperThread extends Thread {
      
      private JButton button;

      HelperThread(JButton inputButton) {
         button = inputButton;
         if (count > 0) { // 시간이 0보다 클때
            if (button.getText().equals(OFF_STRING)) {
               // 버튼에 offstring 불러옴
               button.setText(UP_STRING);
               // 버튼에 upstring 셋함
               button.setBackground(UP_COLOR);
               // 버튼의 백그라운드컬러를 불러옴
            } else { // 시간이 0보다 작을때
               button.setText(OFF_STRING);
               // 버튼에 offstring을 셋함
               button.setBackground(OFF_COLOR);
               // 버튼에 뱃그라운드컬러를 셋함
            }
         }
      }
      @Override
      public void run() {
         while (count > 0) { // 시간이 0보다 남을때
            int randomUpNum = random.nextInt(3000);
            int randomDownNum = random.nextInt(8000);
            synchronized (button) {
               if (!start.isEnabled()) { // 시작이 활성화 되었으면
                  if (button.getText().equals(OFF_STRING)) {
                     button.setText(UP_STRING);
                     button.setBackground(UP_COLOR);
                     // 버튼에 텍스트를 불러온다.
                     try {
                        Thread.sleep(1000 + randomUpNum);
                        // 누르고 1초뒤 삭제
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                  } else { // 시작이 활성화 되지 않았으면
                     button.setText(OFF_STRING);
                     button.setBackground(OFF_COLOR);
                     // 다 꺼놓는다.
                     try {
                        Thread.sleep(1000); // 시작시 1초뒤 시작
                        Thread.sleep(randomDownNum);
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                  }
               }
            }
         }
         synchronized (button) { // 버튼 동기화
            if (count < 1) { // 시간이1보다작을때
               button.setText(OFF_STRING);
               button.setBackground(OFF_COLOR);
            }
         }
      }
   }

   // 타이머 쓰레드를 생성한다.
   private class TimerThread extends Thread {
      public boolean go = false;
      @Override
      public void run() {
         synchronized (scoreText) {
            count = 15;
            score = 0;
            while (count >= 0) { //  시간이 0보다 크거나 같을때
               // 이프문으로 일시정지 이곳
               timeText.setText(String.valueOf(count));
               //  남은시간에 시간을 스트링형으로 셋한다,
               scoreText.setText(String.valueOf(score));
               // 얻은 점수총합에 현재점수를 스트링형으로 셋한다.
               count--; // 점수감소
               try {
                  Thread.sleep(1000); //점수가 1초마다 줄어듬
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               if (count == 0) { // 시간이 0이 되면
                   new GameOverDialog(score, self);                               
               }
               if(go) {
                  go = false;
                  
                  new MenuDialog(self);
               }
            }
            try {
               Thread.sleep(1000); //끝난후 게임시작 버튼 활성화되는 시간
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            count = 15;
            score = 0;
            scoreText.setText("" + score);
            start.setEnabled(true);
         }
      }
   }   
   


   class Score {
      private String name;
      private int score;

      public Score() {
      }

      public Score(String name, int score) {
         this.name = name;
         this.score = score;
      }
   }

   public Molle(SmallGames sm) {
      this.setTitle("스마일 캐쳐!!");
      this.setSize(600, 370);
      this.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setLocationRelativeTo(sm);
      this.compInit();
      this.eventInit();     
      this.setUndecorated(true);
      this.setModalityType(ModalityType.APPLICATION_MODAL);
      this.setVisible(true);
   }

   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel(
               "javax.swing.plaf.nimbus.NimbusLookAndFeel");
      } catch (Exception e) {
         //Molle f = new Molle();
      }

      SwingUtilities.invokeLater(new Thread() {
         public void run() {
           // new Molle();
         }
      });
   }
}