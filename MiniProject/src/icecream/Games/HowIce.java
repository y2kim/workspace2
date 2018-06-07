package icecream.Games;

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

public class HowIce extends JDialog {
	HowIce bow = this;
	JButton backBtn = new JButton("선택지로 돌아가기");
	JTextArea textarea = new JTextArea("화면에 나타난 아이스크림과 똑같은 모양으로 아이스크림을 쌓아야 한다."
			+ " 화면 밑부분의 아이스크림 그림을 클릭하거나 방향키를 이용해 아이스크림을 쌓는다 "
			+ "스페이스바는 게임 일시 중지 이다");
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
	
	public HowIce(SmallGames sg) {
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
