package Games;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstMenu extends JPanel {
	
	 private JLabel gameName = new JLabel("mini games ");
	 public JButton startBtn = new JButton("시작");
	 private JButton mailBtn = new JButton("문의");
	 private JButton quitBtn = new JButton("종료");
	 Font myFont;
	 private void compInit() {
		 
		 try {
			 myFont = Font.createFont(Font.TRUETYPE_FONT,new File("src/Games/res/wiggle-hangeul.ttf")).deriveFont(25f);
			 }catch (Exception e){
				 System.out.println("해당파일이 없음 ");
			 }
		 GridBagConstraints gbc = new GridBagConstraints(); 
		 gbc.insets = new Insets(5,3,5,3); // 좌우상하 공백 
		 gbc.gridx =0; gbc.gridy =0;
		 this.gameName.setFont(myFont);
		 this.add(gameName, gbc);
		 gbc.gridx =0; gbc.gridy =1;
		 startBtn.setPreferredSize(new Dimension(100, 30));
		 this.add(startBtn, gbc);
		 gbc.gridx =0; gbc.gridy =2;
		 mailBtn.setPreferredSize(new Dimension(100, 30));
		 this.add(mailBtn, gbc);
		 gbc.gridx =0; gbc.gridy =3;
		 quitBtn.setPreferredSize(new Dimension(100, 30));
		 this.add(quitBtn, gbc);
		 
	}
	 private void eventInit(SmallGames sg) {
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LayoutManager lm = sg.panelCenter.getLayout();
				((CardLayout)lm).show(sg.panelCenter, "game1");
				SmallGames.countPage =1;
			}
		});
		 
		quitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		mailBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MailDialog();
			}
		});
	}
	
	public FirstMenu(SmallGames sg) {
        setBackground(Color.LIGHT_GRAY);
        this.setSize(330, 80);
        this.setLayout(new GridBagLayout());
        this.compInit();
        eventInit(sg);
        //this.setVisible(true);
	}
}
