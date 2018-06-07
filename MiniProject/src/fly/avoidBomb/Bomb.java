package fly.avoidBomb;
public class Bomb {
	private int x;
	private int y;
	private int kr = 10;					// 폭탄의 살상 범위
	private int bs = 15;					// 폭탄의 사이즈
	private double gravity = 0.138;		// 중력 가속도
	private double verticalSpeed = 0;	// 속도
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void fall() {
		this.verticalSpeed = this.verticalSpeed + gravity;
		this.y = (int) (this.y + this.verticalSpeed);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getKr() {
		return kr;
	}

	public int getBs() {
		return bs;
	}
}
