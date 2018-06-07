package trex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrexRanking extends JDialog {
	TrexRanking self = this;
	
	GridBagConstraints gbc = new GridBagConstraints();
	private int x = 0;
	private int y = 0;
	
	private JLabel blank = new JLabel(" ");

	private JPanel rankPanel = new JPanel(new GridBagLayout());
	private JPanel btnPanel = new JPanel(new GridBagLayout());
	
	private JButton btnOk = new JButton("확 인");
	
	private JLabel[] labelsRank = new JLabel[] {new JLabel(), new JLabel(), new JLabel(),new JLabel(),new JLabel()};

	
	private void setXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.x = x;
		this.y = y;
	}
	
	private void showRank() {
		//		Font small = new Font("Helvetica", Font.BOLD, 20);
		rankPanel.setPreferredSize(new Dimension(220, 100));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel text = new JLabel("R A N K I N G");

		setXY(0,0); gbc.weighty = 0.3;
		rankPanel.add(text, gbc);
		gbc.weighty = 0.1;
		for(int i = 0; i < ScoreRanking.rankList.size(); i++) {
			setXY(0,i+1);
			String str = " " + (i+1) +"위. " + ScoreRanking.rankList.get(i).getName() + ", " + ScoreRanking.rankList.get(i).getScore() + "점";
			labelsRank[i].setText(str);
			rankPanel.add(labelsRank[i], gbc);
		}
		setXY(0,6);
		gbc.weighty = 0.2;
		rankPanel.add(blank, gbc);

	}
	
	private void compInit() {		
		btnOk.setPreferredSize(new Dimension(130, 30));
		btnOk.setOpaque(false);
		showRank();
		btnPanel.add(btnOk);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.add(rankPanel);
	}
	
	private void eventInit() {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
	}
	
	public TrexRanking() {
		new ScoreRanking();
		this.setSize(230,250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		compInit();
		eventInit();
		this.setModal(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}
}
