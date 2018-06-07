package icecream.Games;

import javax.swing.JDialog;

public class MyThread extends Thread {
	public static int sec = 60;
	public static final Object lock = new Object();

	public static boolean pause = false;
	public static boolean start = true;
	public static boolean first = true;

	

	public void run() {
		while(true) {
			synchronized (lock) {
				while(pause) {
					try {
						lock.wait();
					} catch (InterruptedException e) {}
				}
			}
			doTask();
		}
	}
	public void doTask() {}

	public void checkPause() {
		synchronized(lock) {
			if(pause) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				lock.notifyAll();
			}
		}
	}
	
	public void checkStart() throws InterruptedException {
		synchronized(lock) {
			if(!start) {
				lock.wait();
			} else {
				lock.notify();
			}
		}
	}
}
