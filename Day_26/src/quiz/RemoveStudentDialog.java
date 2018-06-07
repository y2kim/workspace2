package quiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RemoveStudentDialog extends JDialog {
	public RemoveStudentDialog self = this;
	StudentMember std = new StudentMember();
	Student[] test = new Student[StudentMember.studentDB.size()];
	Set<Integer> keySet = StudentMember.studentDB.keySet(); 
    //test = new Student[StudentMember.studentDB.size()];

	
	
	private JTextField idField = new JTextField("");
	private JButton removeBtn = new JButton("삭제");
	private JPanel inputPanel = new JPanel(new FlowLayout());

	private String[] columnHeaders = new String[] {
			 "ID","name","국어","영어","수학","합계","평균"  	
	};

	private DefaultTableModel dtm = new DefaultTableModel(columnHeaders,0);
	private JTable table = new JTable(dtm);
	private JScrollPane paneTable = new JScrollPane(table);
	
	private void refresh() {
		dtm.setRowCount(0);
		for(int ic:StudentMember.studentDB.keySet()) {
			dtm.addRow(new Object[] {StudentMember.studentDB.get(ic).getStuId()
					,StudentMember.studentDB.get(ic).getStdName(),StudentMember.studentDB.get(ic).getKorScore() 
					,StudentMember.studentDB.get(ic).getEngScore(),StudentMember.studentDB.get(ic).getMathScore()});
		}
		idField.setText("");
	}

	private void compInit() {
		this.add(paneTable, BorderLayout.CENTER);
		this.idField.setPreferredSize(new Dimension(80,30));
		this.inputPanel.add(idField);
		
		for(int ic:StudentMember.studentDB.keySet()) {
			dtm.addRow(new Object[] {StudentMember.studentDB.get(ic).getStuId()
					,StudentMember.studentDB.get(ic).getStdName(),StudentMember.studentDB.get(ic).getKorScore() 
					,StudentMember.studentDB.get(ic).getEngScore(),StudentMember.studentDB.get(ic).getMathScore()
					,StudentMember.studentDB.get(ic).Total(),StudentMember.studentDB.get(ic).Avg()});
		}
		
//		 for(int i = 0; i<test.length; i++) {
//	         dtm.addRow(new Object[] {test[i].getStuId(), test[i].getStdName(),test[i].getKorScore(),test[i].getEngScore()
//	               ,test[i].getMathScore(),test[i].Total(),test[i].Avg()});
//	      }
		
		this.inputPanel.add(removeBtn);
		this.add(inputPanel, BorderLayout.SOUTH);

	}

	private void eventInit() {
		this.removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				try {
					id = Integer.parseInt(self.idField.getText());
				}catch(Exception e) {
					JOptionPane.showMessageDialog(self, "숫자를 입력하세요","경고",JOptionPane.CLOSED_OPTION);
				}
					int i = 0; int count = 0;

					for(Student tmp : StudentMember.studentDB.values()) {
						if(id == tmp.getStuId()) {
							int sel = JOptionPane.showConfirmDialog(self,"삭제하시겠습니까?","",JOptionPane.YES_NO_OPTION);
							if(sel == 0) { 
								StudentMember.HashDelStudent(tmp.getStuId());
								dtm.removeRow(count);
								count++;
								
					            refresh();
							}
							i++;
						}
					}
					if(count == 0) {
						JOptionPane.showMessageDialog(self, "삭제할 데이터가 없습니다.","경고",JOptionPane.CLOSED_OPTION);
					}
				

			}
		});
		
		
	}

	public RemoveStudentDialog(MainProgram parent) {
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		compInit();
		eventInit();
		this.setVisible(true);
	}
}
