package quiz;

public class Student {
	private int stuId;
	private String stdName;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int sumsco;
	private double avgsco;
	
	public Student(int stuId, String stdName, int korScore, int engScore, int mathScore) {
		super();
		this.stuId = stuId;
		this.stdName = stdName;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.sumsco = korScore+engScore+mathScore;
		this.avgsco = ((korScore+engScore+mathScore)/3);
	}
	
	public int Total() {
		return this.korScore+this.engScore + this.mathScore ;
	}
	
	public double Avg() {
		return (this.korScore+this.engScore + this.mathScore)/3 ;
	}
	
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public int getKorScore() {
		return korScore;
	}
	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}
	public int getEngScore() {
		return engScore;
	}
	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	
	
}
