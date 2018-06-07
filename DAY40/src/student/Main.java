package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		DBManager manager = new DBManager(); 
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
				int result =0;
				try {
				 result = manager.insertData(name, score);
				 if(result>0) {
						System.out.println("입력에 성공했습니다.");
					}else {
						System.out.println("입력에 실패했습니다.");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("에러발생");
				}
								

			}else if(menu.equals("2")) {  //rank() OVER (ORDER BY SALARY DESC)
				try {
					manager.selectData();
				} catch (Exception e) {
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
				int result = 0;
				try {
					result = manager.deleteData(stuid);
					if(result >0) {
						System.out.println("삭제에 성공했습니다");
					}else {
						System.out.println("삭제에 실패하였습니다.");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("삭제과정에서 오류 발생.");
				}
				
			} 
			else if (menu.equals("4")) {
				System.out.println("프로그램 종료합니다");
				System.exit(0);
			} else {
				System.out.println("메뉴를 확인해주세요");
			}
		}
	}
}
