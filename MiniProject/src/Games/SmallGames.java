package Games;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//최종jar 파일 만들때는 src 파일이 빠짐 
public class SmallGames extends JFrame {
	public SmallGames self = this;
	//private JButton startbtn = new FirstMenu().startBtn;
	private JLabel named = new JLabel("SmallGames");  // 임시
	public  JPanel panelCenter =new JPanel(new CardLayout());
	private JPanel panelDown = new JPanel();
	private JPanel panelUp = new JPanel();
	private JButton btn1 = new JButton("시작");
	private Font myFont;
	private JButton btnPrev = new JButton("◀");
	private JButton btnNext = new JButton("▶");
	public static int countPage = 0;
	private JButton btn4 = new JButton("∩");
	//--
	private JPanel first = new FirstMenu(self);
//-- 게임리스트
	private JPanel game1 = new Game1(self);
	private JPanel game2 = new Game2(self);
	private JPanel game3 = new Game3(self);
	private JPanel game4 = new Game4(self);
	private JPanel game5 = new Game5(self);
	private JPanel game6 = new Game6(self);
	private JPanel game7 = new Game7(self);
	//--
	private void compInint() {
		try {
			myFont= Font.createFont(Font.TRUETYPE_FONT,new File("src/fly/resources/wiggle-hangeul.ttf")).deriveFont(25f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.btn4.setPreferredSize(new Dimension(48, 48));
		this.panelUp.add(btn4);
		this.panelUp.add(named);
		this.add(panelUp,BorderLayout.NORTH);
		this.add(btnPrev , BorderLayout.WEST);
		this.add(btnNext , BorderLayout.EAST);
		this.add(panelCenter, BorderLayout.CENTER);
		this.panelCenter.setPreferredSize(new Dimension(612, 510));
		this.panelDown.setPreferredSize(new Dimension(700, 20));
		this.add(panelDown, BorderLayout.SOUTH);
	}
	String[] panelNames = new String[]{"game1","game2","game3"
			,"game4","game5","game6","game7"};
	
	private void addPane() {
		panelCenter.add(first,"first");
		panelCenter.add(game1,"game1");
		panelCenter.add(game2,"game2");
		panelCenter.add(game3,"game3");
		panelCenter.add(game4,"game4");
		panelCenter.add(game5,"game5");
		panelCenter.add(game6,"game6");
		panelCenter.add(game7,"game7");
	}
	
	private void eventInit() {
//		this.startbtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//System.exit(0);
//			}
//		});
		
		this.btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LayoutManager lm = panelCenter.getLayout();
				((CardLayout)lm).show(panelCenter, "first");
				countPage = 0;
				System.out.println(countPage);
			}
		});
		
		this.btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(countPage > 0) {
					countPage++;
					LayoutManager lm = panelCenter.getLayout();
					if(countPage<=panelNames.length) {	
						System.out.println(countPage);
						((CardLayout)lm).next(panelCenter);
					}else if(countPage>panelNames.length){
						System.out.println(countPage);
						((CardLayout)lm).show(panelCenter, "game1");
						countPage=1;
						System.out.println("재정비"+countPage);
					}
				}else {
					System.out.println("시작버튼을 눌러보세요");
				}
				
			}
		});
		
		this.btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(countPage > 0) {
					countPage--;
					LayoutManager lm = panelCenter.getLayout();
					if(countPage>=1) {
						System.out.println(countPage);
						((CardLayout)lm).previous(panelCenter);
					}else{
						((CardLayout)lm).show(panelCenter, panelNames[panelNames.length-1]);
						System.out.println(countPage);
						countPage=panelNames.length;
						System.out.println(countPage);
					}
				}else {
					System.out.println("시작버튼을 눌러보세요");
				}
			}
		});
		
	}
	
	 Point locked=null;
	    public void JFrameTypeLocking(String string) {
	        //super(string);
	        super.addComponentListener(new ComponentAdapter(){
	            public void componentMoved(ComponentEvent e) {
	                if (locked!=null) SmallGames.this.setLocation(locked);
	            }});
	    }
	    public void lockLocation() {
	        locked=super.getLocation();
	    }
	
	public SmallGames() {
		super("SmallGames");
		this.setFont(myFont);
		this.setSize(900, 700);
		this.setResizable(false);
		setUndecorated(true); 
		getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInint();
		this.addPane();
		this.eventInit();
		this.lockLocation();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new SmallGames().setVisible(true);
	    try {
	         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	      } catch (Exception e) {}
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				new SmallGames();
			}
		});
	}
}
