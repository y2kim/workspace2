package quiz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentEdit extends JDialog {
	private String[] columnHeaders = new String[] { "학번", "이름", "국어", "수학", "영어", "합계", "평균" };

	private DefaultTableModel dtm = new DefaultTableModel(columnHeaders, 0) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private JTable table = new JTable(dtm);
	private JScrollPane paneTable = new JScrollPane(table);
	private StudentEdit self = this;
	private Student[] test;
	private int i = 0; // 인덱스에 접근하여 배열로 만듦
	private JPanel inputInfo = new JPanel(new GridLayout(1, 3));
	private JLabel inputIdInfo = new JLabel("수정할 학번 입력");
	private JTextField inputId = new JTextField();
	private JButton refresh = new JButton("새로고침");

	private void compInit() {
		this.add(paneTable, BorderLayout.CENTER);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		this.inputInfo.add(inputIdInfo);
		this.inputInfo.add(inputId);
		this.inputInfo.add(refresh);
		this.add(inputInfo, BorderLayout.SOUTH);
	}

	private void eventInit() {
		inputId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(inputId.getText());
					if (!StudentMember.studentDB.containsKey(id)) {
						throw new Exception();
					}
					new StudentEditInfo(self, id);

				} catch (Exception ae) {
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요 or 존재하는 학번만 입력하세요", "경고", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dtm.setRowCount(0);
				for (int i = 0; i < test.length; i++) {
					dtm.addRow(new Object[] { test[i].getStuId(), test[i].getStdName(), test[i].getKorScore(),
							test[i].getEngScore(), test[i].getMathScore(), test[i].Total(), test[i].Avg() });
				}
			}
		});
	}

	private void infoInit() {
		Set<Integer> keySet = StudentMember.studentDB.keySet();
		test = new Student[StudentMember.studentDB.size()];

		for (int k : keySet) {
			Student s = StudentMember.studentDB.get(k);
			test[i++] = s;
		}

		for (int i = 0; i < test.length; i++) {
			dtm.addRow(new Object[] { test[i].getStuId(), test[i].getStdName(), test[i].getKorScore(),
					test[i].getEngScore(), test[i].getMathScore(), test[i].Total(), test[i].Avg() });
		}
	}

	public StudentEdit() {
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.compInit();
		this.infoInit();
		this.eventInit();
	}
}
