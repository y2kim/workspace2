package trex;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	
	private List<BufferedImage> list; // 이미지 담는 리스트 
	private long deltaTime;
	private int currentFrame = 0;
	private long previousTime;
	
	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		list = new ArrayList<BufferedImage>(); 
		previousTime = 0;
	}
	
	public void updateFrame() {
		if (System.currentTimeMillis() - previousTime >= deltaTime) {
			currentFrame++;
			if (currentFrame >= list.size()) {
				currentFrame = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}
	
	public void addFrame(BufferedImage image) {
		list.add(image); // 이미지 저장 
	}
	
	public BufferedImage getFrame() {
		return list.get(currentFrame);// 이미지 리스트를 자연스레 흐르게 하는것 
	}
	
}