import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import jrdesktop.main;

public class Hint extends JFrame {
	
	private JButton button = new JButton("옷");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Menu1");
	private JMenuItem jmi = new JMenuItem("MenuItem1");
	private JMenuItem jmi2 = new JMenuItem("MenuItem2");
	private JPopupMenu pop = new JPopupMenu("test");
	
	public Hint() {
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.button.setBounds(20, 20, 50, 50);
		this.add(button);
		
		this.setJMenuBar(menuBar);
		this.menuBar.add(menu1);
		this.menu1.add(jmi);
		this.menu1.add(jmi2);
		
		
		this.button.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {  // 인지
				// TODO Auto-generated method stub
				//System.out.println("x");
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 떼는순간
				// TODO Auto-generated method stub
				//System.out.println("1");
			}
			
			@Override
			public void keyPressed(KeyEvent e) { // 누르는 순간 
				// TODO Auto-generated method stub
				//System.out.println("k");
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					//System.out.println("rigth");
					button.setLocation(button.getX()+1, button.getY());
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					//System.out.println("rigth");
					button.setLocation(button.getX()-1, button.getY());
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					//System.out.println("rigth");
					button.setLocation(button.getX(), button.getY()-1);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					//System.out.println("rigth");
					button.setLocation(button.getX(), button.getY()+1);
				}
			}
		});
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Hint();
//		SwingUtilities.invokeLater(new Thread(
//				
//				));
	}
}
