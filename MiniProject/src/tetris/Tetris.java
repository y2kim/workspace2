package tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Games.SmallGames;

public class Tetris extends JDialog{
	public Tetris selfthis = this;
	int sco = Integer.parseInt(getStatusBar2().getText());
	
	public JLabel statusbar = new JLabel("0");
	public static JLabel statusbar2 = new JLabel("0");
	public JPanel status = new JPanel(new BorderLayout());
	public static int countfirst;
	
	public Tetris(SmallGames sg) {
		//super("Tetris");
		//this.setLayout(new BoxLayout(sg, BoxLayout.PAGE_AXIS));
		this.statusbar.setPreferredSize(new Dimension(200, 30));
		this.status.add(statusbar, BorderLayout.WEST);
		this.status.add(statusbar2, BorderLayout.EAST);
		this.add(status , BorderLayout.SOUTH);
		this.setSize(290, 485);
		Borad board = new Borad(this);
		int as = Integer.parseInt(getStatusBar2().getText());
		selfthis.setModal(true);
		this.setUndecorated(true);	
		this.add(board);
		board.start();
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				selfthis.setModal(false);
				//selfthis.setUndecorated(false);
				selfthis.dispose();
				sg.setEnabled(true);
			}
		};
		this.addWindowListener(exit);
		this.setLocationRelativeTo(sg);
		this.setVisible(true);
	}
	

	public JLabel getStatusBar() {
		return statusbar;
	}

	public JLabel getStatusBar2() {
		return statusbar2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				//new Tetris();
			}
		});
	}

}
