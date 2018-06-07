package tetris;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOver {
	GridBagConstraints gbc = new GridBagConstraints();

	String[] str = {"다시하기", "나가기"};

	private JPanel rankPanel = new JPanel(new GridBagLayout());
	private JPanel panel = new JPanel(new GridBagLayout());
	JTextField field;

	public void rankDialog()
	{
		JOptionPane.showOptionDialog(null, getRankPanel(), "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "");
		String name = field.getText();
		if(name.equals("")) {
			name="unknown";
		}
		int sco ;
		sco = Integer.parseInt(Tetris.statusbar2.getText());
		ScoreRanking sr = new ScoreRanking(sco, name);
		try {
			 sr.readTxt();
			 sr.saveTxt();
			 System.out.println(name);
		} catch (Exception e) {
			System.out.println("숫자가 아님");
		}
	}

	public void messageDialog()
	{
		int num = JOptionPane.showOptionDialog(null, getPanel(), "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "");
		if(num == 0) {
			System.exit(0);
		}
		
	}
	
	private void showRank() {
		JLabel set = new JLabel("순위\t닉네임\t점수");
		for(int i = 0; i < 5; i++) {
		
//			String str = i+1+"위\t" + IcecreamDialog.sr.rankList.get(i).getName() + "\t" + IcecreamDialog.sr.rankList.get(i).getName();
//			JLabel rank = new JLabel(str);
//			gbc.gridy = i+1;
//			panel.add(rank, gbc);
		}
	}

	private JPanel getPanel()
	{
		JLabel text = new JLabel("GAME OVER");
		rankPanel.setSize(250, 350);
		showRank();
		panel.add(text);
		return panel;
	}

	private JPanel getRankPanel()
	{
		JLabel text = new JLabel("순위에 들었습니다!");
		rankPanel.setSize(250, 400);
		JLabel score = new JLabel(Tetris.statusbar2.getText());
		JLabel label = new JLabel("닉네임을 입력하세요");
		rankPanel.add(score);
		rankPanel.add(label);
		
		field = new JTextField();
		field.setPreferredSize(new Dimension(90,30));
		
		rankPanel.add(text);
		gbc.gridy = 1;
		rankPanel.add(label, gbc);
		gbc.gridy = 2;
		rankPanel.add(field, gbc);
		return rankPanel;
	}
}
