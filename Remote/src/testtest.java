import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class testtest extends JFrame {
	
	private JLabel label = new JLabel("메뉴와 버튼을 만들어보세요");
	private JButton button = new JButton("OK");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("메뉴1");
	private JMenuItem menuItem1 = new JMenuItem("메뉴항목1");

	
	public testtest() {
		super("Test");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.add(label);
		this.add(button);
		this.setJMenuBar(menuBar);
		this.menuBar.add(menu1);
		this.menu1.add(menuItem1);
		
		
		this.setVisible(true);
	}
	

	
	public static void main(String[] args) {
		new testtest();
	}
}
