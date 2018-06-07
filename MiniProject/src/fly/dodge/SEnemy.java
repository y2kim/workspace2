package fly.dodge;

// 남쪽에서 공격하는 적
public class SEnemy {
	private int x;
	private int y;
	private int w = 5;
	private int h = 5;
	
	public SEnemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveEnemy() {
		y--;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final int getW() {
		return w;
	}

	public final int getH() {
		return h;
	}
}
