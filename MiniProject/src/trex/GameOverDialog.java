package trex;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GameOverDialog extends JOptionPane{
	
	String[] st = {"restart", "2", "exit"};
	JPanel jp = new JPanel();
	JLabel jl = new JLabel("게임에 졌습니다 ");
	
//	UIManager.put("OptionPane.minimumSize", new Dimension(300,200));
//	int cho = JOptionPane.showOptionDialog(this, "Msg", "Title", JOptionPane.DEFAULT_OPTION,
//			JOptionPane.INFORMATION_MESSAGE, null, st, "");
//	if(cho ==0) {
//		gameState = GAME_PLAYING_STATE;
//		resetGame();
//	}else if (cho ==1) {
//		System.out.println("2");
//	}else {
//		System.exit(0);
//	}
//this.showOptionDialog(gs25, jp, "그래픽", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, st, gs25);
	
	public GameOverDialog(GameScreen gs25) {
		jp.add(jl);
		 
		
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		};
		UIManager.put("OptionPane.minimumSize", new Dimension(800,800));
	}


}
