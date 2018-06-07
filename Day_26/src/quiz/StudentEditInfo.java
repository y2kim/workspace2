package quiz;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentEditInfo extends JDialog {
	private int editId;
	private StudentEdit self;
	private JLabel stuId = new JLabel("학번");
	private JLabel stuName = new JLabel("이름");
	private JLabel stuKor = new JLabel("국어");
	private JLabel stuMath = new JLabel("수학");
	private JLabel stuEng = new JLabel("영어");
	private JLabel stuSum = new JLabel("합계");
	private JLabel stuAvg = new JLabel("평균");
	
	private JTextField showStuId = new JTextField();
	private JTextField stuNameInput = new JTextField();
	private JTextField stuKorInput = new JTextField();
	private JTextField stuMathInput = new JTextField();
	private JTextField stuEngInput = new JTextField();
	private JTextField showStuSum = new JTextField();
	private JTextField showStuAvg = new JTextField();
	
	private JButton edit = new JButton("수정");
	private JButton cancel = new JButton("취소");
	
	private void compInit() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,400);
		this.setVisible(true);
		this.setLocationRelativeTo(self);
		this.setLayout(new GridLayout(8,2));
		this.setVisible(true);
		
		this.showStuId.setText(StudentMember.studentDB.get(editId).getStuId()+"");
		this.stuNameInput.setText(StudentMember.studentDB.get(editId).getStdName());
		this.stuKorInput.setText(StudentMember.studentDB.get(editId).getKorScore()+"");
		this.stuMathInput.setText(StudentMember.studentDB.get(editId).getMathScore()+"");
		this.stuEngInput.setText(StudentMember.studentDB.get(editId).getEngScore()+"");
		this.showStuSum.setText(StudentMember.studentDB.get(editId).Total()+"");
		this.showStuAvg.setText(StudentMember.studentDB.get(editId).Avg()+"");
		
		this.add(stuId); 
		this.add(showStuId);
		
		this.add(stuName);
		this.add(stuNameInput);
		
		this.add(stuKor);
		this.add(stuKorInput);
		
		this.add(stuMath);
		this.add(stuMathInput);
		
		this.add(stuEng);
		this.add(stuEngInput);
		
		this.add(stuSum);
		this.add(showStuSum);
		
		this.add(stuAvg);
		this.add(showStuAvg);
		
		this.showStuId.setEditable(false);
		this.showStuSum.setEditable(false);
		this.showStuAvg.setEditable(false);
		
		this.add(edit);
		this.add(cancel);
	}
	
	private void eventInit() {
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String stuName = stuNameInput.getText();
					int kor = Integer.parseInt(stuKorInput.getText());
					int math = Integer.parseInt(stuMathInput.getText());
					int eng = Integer.parseInt(stuEngInput.getText());
					
					if((kor > 100 || kor < 0) || (math > 100 || math < 0) || (eng > 100 || eng < 0)) {
						throw new Exception("0부터 100까지의 숫자만 입력하세요");
					}
					Student s = StudentMember.studentDB.get(editId);
					s.setKorScore(kor);
					s.setMathScore(math);
					s.setEngScore(eng);
					s.setStdName(stuName);
					StudentMember.studentDB.put(s.getStuId(), s);
					dispose();
				}catch(Exception ae) {
					JOptionPane.showMessageDialog(null, "성적에 숫자만 입력하세요 or 0~100 까지의 숫자를 입력하세요", "경고", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public StudentEditInfo(StudentEdit self,int id) {
		this.self = self;
		this.editId = id;
		this.compInit();
		this.eventInit();
	}
}
