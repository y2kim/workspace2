package fly.dodge;

public class NEnemy {
	private int x;
	private int y;
	private int w = 5;
	private int h = 5;
	
	public NEnemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveEnemy() {
		y++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
}
