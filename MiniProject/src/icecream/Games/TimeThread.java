package icecream.Games;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeThread extends Thread {
	IcecreamDialog ice;
	public static int sec = 60;

	public void countdown(JLabel time, IcecreamDialog parent) {
		this.ice = parent;
		while(sec > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sec -= 1;
			time.setText(String.valueOf(sec));
		}
		time.setText(String.valueOf(0));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new GameOverDialog(IcecreamDialog.scoreInt, ice);
		//IcecreamDialog.over = true;

	}
}
