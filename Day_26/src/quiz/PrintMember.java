package quiz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PrintMember extends JDialog {
	 private PrintMember self = this;

	private String[] columnHeaders = new String[] {
			"학번","이름","국어","수학","영어","합계","평균"  
	};
	
	private DefaultTableModel dtm = new DefaultTableModel(columnHeaders,0);
	private JTable table = new JTable(dtm);
	private JScrollPane paneTable = new JScrollPane(table);
	private JButton btnClose = new JButton("닫기");
	
	private void compInit() {
		this.add(paneTable);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		this.add(btnClose,BorderLayout.SOUTH);
		
		for(int ic:StudentMember.studentDB.keySet()) {
			dtm.addRow(new Object[] {StudentMember.studentDB.get(ic).getStuId()
					,StudentMember.studentDB.get(ic).getStdName(),StudentMember.studentDB.get(ic).getKorScore() 
					,StudentMember.studentDB.get(ic).getEngScore(),StudentMember.studentDB.get(ic).getMathScore()
					,StudentMember.studentDB.get(ic).Total(),StudentMember.studentDB.get(ic).Avg()});
		}
	}
	
	private void eventInit() {
	      this.btnClose.addActionListener(new ActionListener() {      
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         // TODO Auto-generated method stub
	         self.dispose();
	      }
	   });
	}


	public PrintMember() {
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
}
