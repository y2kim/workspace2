package kh.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exam_Update {
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
        
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        		Statement stmt = con.createStatement();) {
			String sql = "update person set age = 40 where id = '1003'";
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				System.out.println("갱신에 성공.");
			}else {
				System.out.println("갱신에 실패.");
			}
			
			con.commit();
			
			con.close();
			stmt.close();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
}
