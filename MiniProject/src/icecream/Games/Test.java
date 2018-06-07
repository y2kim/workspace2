package icecream.Games;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class Test extends JDialog {
	public Test test1 = this;
	
	private JButton button1 = new JButton("게임선택하세요");
	private JButton button2 = new JButton("Icecream Game");
	private JButton button3 = new JButton("3");
	
	private void compInit() {
		this.add(button1);
		this.add(button2);
	}
	
	
	private void eventInit() {
		this.button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				IcecreamDialog ice = new IcecreamDialog(test1,"Icecream game");
//				ice.setVisible(true);
			}
		});
	}
	
	public Test(IceCreamGames self) {
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(self);
		
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IceCreamGames rise = new IceCreamGames();
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
