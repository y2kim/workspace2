package student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	
	private Connection makeConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; 
		String DB_USER = "kh";
		String DB_PASSWORD = "kh";
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);;
		return con;
	}

	public int insertData(String name , int score) throws Exception {
		Connection con = this.makeConnection();
		String sql =
				"insert into student values(student_seq.nextval,?,?)"; 
		
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1,name);
		pst.setInt(2,score);
		int result = pst.executeUpdate();

		
		con.commit();
		pst.close();
		con.close();
		return result;
	}

	public int deleteData(String stuid) throws Exception {
		Connection con = this.makeConnection();
		
		String sql =
				"delete from student where ID =?"; 
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, stuid);
		int result = pst.executeUpdate();

		con.commit();
		return result;

	}

	public void selectData()throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; 
		String DB_USER = "kh";
		String DB_PASSWORD = "kh";
		
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);;
		Statement st = null; 
		PreparedStatement pst =null;
		
		String sql = "Select id,name,score,rank() over (order by score desc) from student order by score desc ";
		//String sql2 = "Select id,name,score,rank() over (order by score desc) from student order by score desc ";
			pst = con.prepareStatement(sql);
			ResultSet rs  = pst.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getString(1)+" ,"); // 번호 대신 컬럼헤더 이름을 쓸수 있다
				System.out.print(rs.getString(2)+" ,");
				System.out.print(rs.getInt(3)+"점 ");
				System.out.print(rs.getInt(4)+"위");
				System.out.println("");
			}


	}
}
