package tetris;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HowBorad extends JDialog {
	HowBorad bow = this;
	JButton backBtn = new JButton("선택지로 돌아가기");
	JTextArea textarea = new JTextArea("p:일시정지  d:한줄 빨리 내려가기"
			+ "스페이스바 : 바로 내리기  좌우방향기: 이동 상하 방향기 조각돌리기 ");
	JLabel textl = new JLabel();
	JPanel pan = new JPanel();
	
	private void compInit() {
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets = new Insets(5,3,5,3);
		this.pan.setPreferredSize(new Dimension(120, 160));
		this.textarea.setLineWrap(true);
		this.pan.add(textarea);
		//this.pan.set
		this.add(pan);
		this.add(backBtn);
		
		this.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bow.setModal(false);
				bow.dispose();
			}
		});
	}
	
	public HowBorad() {
		bow.setModal(true);
		bow.setUndecorated(true);
		this.setLayout(new GridBagLayout());
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bow.setModal(false);
				//selfthis.setUndecorated(false);
				bow.dispose();
			}
		};
		this.addWindowListener(exit);
		this.setLocationRelativeTo(null);
		this.compInit();
		this.setSize(220,300);
		this.setVisible(true);
	}
}
