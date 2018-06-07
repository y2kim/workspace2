package fly.avoidBomb;

// 게임이 끝나면 던져지는 예외
public class EndExceptoin extends Exception{
	public EndExceptoin(String msg) {
		super(msg);
	}
}
