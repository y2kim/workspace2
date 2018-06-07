package fly.dodge;

// 게임이 종료되면 던져지는 예외
public class EndExceptoin extends Exception{
	public EndExceptoin(String msg) {
		super(msg);
	}
}
