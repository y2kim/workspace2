package trex;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnemiesManager {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random rand;
	private List<Enemy> enemies;
	private MainCharacter mainCharacter;
	public boolean can = false;
	
	public EnemiesManager(MainCharacter mainCharacter) { 
		rand = new Random();
		cactus1 = Resource.getResouceImage("/trex/data/cactus1.png");
		cactus2 = Resource.getResouceImage("/trex/data/cactus2.png");
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
	}
	
	public void update() {  
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			//
			enemies.clear();// 누적 없애기 
			enemies.add(createEnemy());//
			mainCharacter.upScore();
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g); // 그래픽 적으로 생성 
		}
	}
	
	private Enemy createEnemy() { 
		// if (enemyType = getRandom)
		int type = rand.nextInt(2);  // 적을 랜덤으로 나오게 하는것
		if(type == 0) {
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else {
			return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				
				GameScreen.setid =2;
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
}
