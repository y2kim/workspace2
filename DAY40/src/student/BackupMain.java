package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BackupMain {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;  // preparedStatement(sql)   exeupdate 에 sql 지정안해줘도 됨 ,?
		PreparedStatement pst =null;

		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; 
		String DB_USER = "kh";
		String DB_PASSWORD = "kh";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			st = con.createStatement();
			// pst = con.prepareStatement(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("데이터 베이스 연결에 문제가 발생 ");
			System.out.println("dis connect db ");
			System.out.println("데이터 베이스 연결에 문제가 발생 ");
			System.exit(0);
		}


		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println(" << == 학생 관리 프로그램 == >>");
			System.out.println(" 1. 신규 정보 입력");
			System.out.println(" 2. 학생 목록 출력");
			System.out.println(" 3. 학생 정보 삭제");
			System.out.println(" 4. 프로 그램 종료");
			System.out.println(" >> ");
			String menu = sc. nextLine();
			if(menu.equals("1")) {
				System.out.println(">> === 신규 정보를 등록합니다."); //학번,이름,점수 
				System.out.println("이 름 : ");
				String name = sc.nextLine();
				System.out.println("점 수 : ");
				int score = Integer.parseInt(sc.nextLine());

				String sql =
						"insert into student values(student_seq.nextval,?,?)"; 

				String sql2 =
						"insert into student values(student_seq.nextval , '"+name+"',"+score+")"; 
				try {
					pst = con.prepareStatement(sql);
					pst.setString(1,name);
					pst.setInt(2,score);
					int result = pst.executeUpdate();

					if(result>0) {
						System.out.println("정보 추가 하였습니다.");
					}else {
						System.out.println("정보 추가 실패하였습니다.");
					}
					con.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 문자열 들어갈때  '' 싱글 쿼테이션 붙일것 <- DB 관련 
				//				try {
				//					int result = st.executeUpdate(sql);
				//					if(result >0) {
				//						System.out.println("성공");
				//					}
				//					con.commit();
				//				} catch (Exception e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//					System.out.println("실패");
				//				}

			}else if(menu.equals("2")) {  //rank() OVER (ORDER BY SALARY DESC)
				String sql = "Select id,name,score,rank() over (order by score desc) from student order by score desc ";
				String sql2 = "Select id,name,score,rank() over (order by score desc) from student order by score desc ";
				try {
					pst = con.prepareStatement(sql);
					ResultSet rs  = pst.executeQuery();
					while(rs.next()) {
						System.out.print(rs.getString(1)+" ,"); // 번호 대신 컬럼헤더 이름을 쓸수 있다
						System.out.print(rs.getString(2)+" ,");
						System.out.print(rs.getInt(3)+"점 ");
						System.out.print(rs.getInt(4)+"위");
						System.out.println("");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}else if(menu.equals("3")) {

				System.out.println(">> === 삭제할 학생의 ID를 넣어주세요 0 입력시 메뉴.");
				System.out.println("학생 ID : ");
				String stuid = sc.nextLine();

				if(stuid.equals("0")) {
					continue;
				}

				String sql =
						"delete from student where ID =?"; 
				String sql2 =
						"delete from student where ID = '"+stuid+"'"; 
				
				try {
					pst = con.prepareStatement(sql);
					pst.setString(1, stuid);
					int result = pst.executeUpdate();
					if(result >0) {
						System.out.println("성공");
					}else {
						System.out.println("실패하였습니다.");
					}
					
					con.commit();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("실패");
				}

			} 
			else if (menu.equals("4")) {
				try {
					con.close();
					st.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("프로그램 종료합니다");
				System.exit(0);
			} else {
				System.out.println("메뉴를 확인해주세요");
			}
		}
	}
}
