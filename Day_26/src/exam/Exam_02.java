package exam;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Exam_02 extends JFrame {
	
	private String[] columHeaders = new String[] {
			"Seq","Name","Email"
	};
	private DefaultTableModel dtm = new DefaultTableModel(columHeaders,0); // 데이터 보관 용도 
	private JTable table = new JTable(dtm);  // 모델이라는 개념이 추가적으로 들어감 // 껍데기 // 시각적 나타냄 
	private JScrollPane paneTable = new JScrollPane(table);
	
	private Exam_02 self = this;
	
	private JButton buttonInput = new JButton("입력");
	private JButton buttonDelput = new JButton("삭제");
	private JPanel panelButton = new JPanel(new GridLayout(1, 2));
	
	private void compInit() {
		this.add(paneTable);
		this.panelButton.add(buttonInput);
		this.panelButton.add(buttonDelput);
		this.add(panelButton, BorderLayout.SOUTH);
	}
	
	private void eventInit() {
		this.buttonInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dtm.addRow(new Object[] {1,"Jack","jack@net.co"});
			}
		});
		this.buttonDelput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dtm.removeRow(0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(self, "삭제할 데이터가 없습니다","경고",JOptionPane.OK_OPTION);
				} 
			}
		});
	}
	
	public Exam_02() {
		super("JTABLE practice");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Thread() {
			@Override
			public void run() {
				new Exam_02();
			}
		});
	}

}
