package fly.dodge;

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

import Games.SmallGames;

public class HowDodge extends JDialog {
	HowDodge bow = this;
	JButton backBtn = new JButton("선택지로 돌아가기");
	JTextArea textarea = new JTextArea("- 네 가지 방향키를 이용해 적들의 공격을 최대한 오래 피해야 하는 게임이다.\r\n" + 
			" Enter 게임시작\r\n" + 
			" 게임이 시작되면 사방에서 적의 공격이 쏟아지는데 그 공격을 방향키(위,아래,좌,우)를 이용해 피해야한다.\r\n" + 
			" SPACE 게임 일시정지 ");
	JLabel textl = new JLabel();
	JPanel pan = new JPanel();
	
	private void compInit() {
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.insets = new Insets(5,3,5,3);
		this.pan.setPreferredSize(new Dimension(200, 250));
		this.textarea.setPreferredSize(new Dimension(120, 160));
		this.textarea.setLineWrap(true);
		this.textarea.setEnabled(false);
		this.textarea.setHighlighter(null);
		gbc.gridx =0; gbc.gridy =0;
		this.pan.add(textarea,gbc);
		//this.pan.set
		this.add(pan);
		gbc.gridx =0; gbc.gridy =1;
		this.add(backBtn,gbc);
		
		this.backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bow.setModal(false);
				bow.dispose();
			}
		});
	}
	
	public HowDodge(SmallGames sg) {
		bow.setModal(true);
		//bow.setUndecorated(true);
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
		this.setLocationRelativeTo(sg);
		this.compInit();
		this.setSize(250,330);
		this.setVisible(true);
	}
}
