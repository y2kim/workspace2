package trex;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	// 이미지 얻는 리소스
	public static BufferedImage getResouceImage(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src"+path));
		} catch (IOException e) {
			System.out.println(path);
			e.printStackTrace();
		}
		return img;
	}
	
}
