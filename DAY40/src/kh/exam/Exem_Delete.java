package kh.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Exem_Delete {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Scanner sc = new Scanner(System.in);
        String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; 
        String DB_USER = "kh";
        String DB_PASSWORD = "kh";
        String sql2 = "delete from person where id = '1004'";
        String sql = "delete from person where id = ?";
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        		Statement stmt = con.createStatement();
        		PreparedStatement pst =con.prepareStatement(sql);) {
        String ID = sc.nextLine();
		 pst.setString(1,ID);
		 int result = pst.executeUpdate();
		
			if(result>0) {
				System.out.println("삭제에 성공했습니다.");
			}else {
				System.out.println("삭제에 실패했습니다.");
			}
			
			con.commit();
			
			con.close();
			stmt.close();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}

}
