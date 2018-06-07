package fly.dodge;

// 서쪽에서 공격하는 적
public class WEnemy {
	private int x;
	private int y;
	private int w = 5;
	private int h = 5;
	
	public WEnemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveEnemy() {
		x++;
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
