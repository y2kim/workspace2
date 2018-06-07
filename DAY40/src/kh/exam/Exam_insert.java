package kh.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exam_insert {
// 웹파트  method,class,get,set,list관련 내용이 중요해짐 
// fuckingswing(쓸모X), socket(안드로이드때 다시사용),//스레드(일부사용,안드로이드 다시사용)
	public static void main(String[] args) {
		// data base + java 연계   -- like socket
		// new을 안쓰고 인스턴스 생성이란 :  우리가 보기에는 new가 안보임 그냥 눈속임
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클 DB 연결하는 인스턴스 생성
			/*데이터 베이스 사용하기 위한 드라이버 연결한  윗 내용은 DB에 따라 내용이 바뀜*/
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
        String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; // jdbc:oracle:thin:@  <-공통사항
        //DB  가지고 있는 주소 써야함   내 자신:  localhost , 127.0.0.1
        String DB_USER = "kh";
        String DB_PASSWORD = "kh";
		
		//Connection con;// 상속받은 애들을 이용해서 new 할수있다. 재활용, 다른 스타일을 적용할때
		//Statement stmt = null; // 쿼리문을 입력할수 있는 공간   서술
	    ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
				Statement stmt= con.createStatement();) {
			//con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);//DB위치 , ID, PW
			// 확인 버튼 누르는 순간    - connection
			//stmt= con.createStatement(); // statment 만들어서 생성 
			//stmt.executeQuery(sql);  //executeQuery <-Select 사용할때 매우 좋다
			String sql = "insert into person values('1004','sal',44)"; //여기서 DB 연결할떄 ; 뗄것
			int result = stmt.executeUpdate(sql); // select 제외한 나머지가 좋다 , 변화를 줄때
			//~~~(): int  -> DB에서 변화된 갯수가 반환됨
			if(result>0) {
				System.out.println("입력에 성공했습니다.");
			}else {
				System.out.println("입력에 실패했습니다.");
			}
			
			con.commit();
			
			con.close();// 더미 사용자 하지 않게 더미 데이터 
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	
	}
}
