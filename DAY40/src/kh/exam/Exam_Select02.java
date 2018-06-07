package kh.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exam_Select02 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
        String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; 
        String DB_USER = "kh";
        String DB_PASSWORD = "kh";
        ResultSet rs = null; // select 값을 받을 자료형 변수 선언 (DB의 특이한 자료 )
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        		Statement stmt = con.createStatement();) {
        	String sql = "Select * from employee"; // commit 필요없음 
        	String sql2 = "Select e.EMP_ID, e.EMP_NAME , d.DEPT_TITLE "
        			+ " from EMPLOYEE e , DEPARTMENT d WHERE e.DEPT_CODE = d.DEPT_ID";
        	 // select 만은 사용
        	rs  = stmt.executeQuery(sql2);
        	while( rs.next()) // next() = result :boolean 1행
        	{
        	System.out.print(rs.getString("EMP_ID")+" "); // 번호 대신 컬럼헤더 이름을 쓸수 있다
        	System.out.print(rs.getString("EMP_NAME")+" ");
        	System.out.print(rs.getString("DEPT_TITLE")+" ");
        	System.out.println("");
        	}
        	//rs.getString(1); // 1열
        	//rs.getString(2); 
        	//rs.getString(3); 
        	
			con.close();
			stmt.close();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
}
