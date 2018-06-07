package icecream.Games;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {
	public static ArrayList<String> quiz = new ArrayList<>();
	public static ArrayList<String> answer = new ArrayList<>();
	public static HashMap<String,Image> arrayImg = new HashMap<>();
	
	
	public void setQuiz() {
		quiz.clear();
		int count = (int)(Math.random()*3 + 2);

		for(int i = 0; i < count; i++) {
			int num = (int)(Math.random()*3 + 1);
			switch(num) {
			case 1: 
				quiz.add("yellow");
				break;
			case 2:
				quiz.add("pink");
				break;
			case 3:
				quiz.add("blue");
				break;
			}
		}
		System.out.println(quiz);
	}
	
	public void putArrayImg(String str, Image img) {
		arrayImg.put(str, img);
	}

	public void reset() {
		this.quiz.clear();
		this.answer.clear();
	}

	public boolean check(int count) {
		if(!(quiz.get(count).equals(answer.get(count))) || count > 5) {
			if(MyThread.sec > 10) {
			MyThread.sec -= 10;
			System.out.println("오답 : 시간 -10");
			} else {
				MyThread.sec = 0;
			}
			return false;
		} else {
			if(count+1 == quiz.size())
				System.out.println("정답 : 시간 +2");
		}
		return true;
	}
}
