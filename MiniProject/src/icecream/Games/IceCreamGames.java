package icecream.Games;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class IceCreamGames extends JDialog {
	
	public IceCreamGames self = this;
	
	private JLabel named = new JLabel("SmallGames");  // 임시
	private JButton btn1 = new JButton("버튼 1");
	private JButton btn2 = new JButton("종료");
//	private JButton btn3 = new JButton("버튼 1");
//	private JButton btn4 = new JButton("버튼 1");
//	private JButton btn5 = new JButton("버튼 ");
	
	private void compInint() {
		this.add(named);
		this.add(btn1);
		this.add(btn2);
	}
	
	private void eventInit() {
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//IcecreamDialog ice = new IcecreamDialog(self);
				self.dispose();	
				
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new GameOverDialog(80);
				self.dispose();	
				
			}
		});
	}
	
	public IceCreamGames() {
	
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		self.setModal(true);
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				self.setModal(false);
				self.removeAll();
			}
		};
		this.addWindowListener(exit);
		this.setLayout(new GridBagLayout());
		this.compInint();
		this.eventInit();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				//new IceCreamGames();
			}
		});
	}
}
