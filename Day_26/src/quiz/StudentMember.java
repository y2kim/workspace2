package quiz;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class StudentMember {
	public static Map<Integer, Student> studentDB = new HashMap<>();
	
	public static void HashAddStudent(int stuid,  Student StudnetMember) {
		boolean isFind = true;
		for(int i :studentDB.keySet()) {
			if(i==StudnetMember.getStuId()) {
				isFind = false;
				break;
			}
		}
		if(isFind == true) {
			studentDB.put(stuid,StudnetMember);
			//map.put(StudnetMember.getStuID(),StudnetMember);
			 JOptionPane.showMessageDialog(null, "입력하신 정보가 등록되었습니다.");     
		}else {
			 JOptionPane.showMessageDialog(null, "이미 있는 학번입니다. 다시 입력해주세요.");
		}
		
	}
	
	public static void HashDelStudent(int StudnetMember) {
	            studentDB.remove(StudnetMember);      
	   }

	
	public void HashScreenStuednt() {
		System.out.println("학생ID\t학생이름\t국어점수\t영어점수\t수학점수\t합계\t평균");

		for(int ic :studentDB.keySet()) {
			System.out.println(studentDB.get(ic));

		}
	}
	
	
}
