package Games;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.StyleContext.SmallAttributeSet;

import trex.GameWindow;

public class Test extends JFrame {
	public Test test1 = this;
	
	private JButton button1 = new JButton("게임선택하세요");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	
	private void compInit() {
		this.add(button1);
	}
	
	
	private void eventInit() {
		this.button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameWindow();
				test1.dispose();
			}
		});
	}
	
	public Test(SmallGames self) {
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(self);
		
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SmallGames rise = new SmallGames();
				self.dispose();
			}
		};
		test1.addWindowListener(exit);
		
		//this.setDefaultCloseOperation();  // HIDE_ON_CLOSE : 숨기는것 (마지막 화면 기억함)  DISPOSE : 지움
		this.compInit();
		this.eventInit();
		this.setLayout(new FlowLayout());
		this.setVisible(true);
	}
}
