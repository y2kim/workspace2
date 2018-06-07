package icecream.Games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

class TimePanel extends JPanel {
	public static Timer timer;

	int width = 230;
	Color color = new Color(255,214,0);

	final Object lock = new Object();

	public TimePanel() {
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int sec = MyThread.sec-1;
				if(sec > 0 && sec < 60) {
					width = (int)(sec*3+sec*0.8);
					if(sec<=10) {
						color = Color.red;
					}
				} else if(sec >= 60) {
					width = 230;
				} else {
					width = 0;
					((Timer)e.getSource()).stop();
				}
				repaint();
			}
		});
		timer.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawRect(10, 5, 230, 30);
		g.setColor(color);
		g.fillRect(10, 5, width, 30);
	}
};
