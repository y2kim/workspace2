package quiz;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExitProgram extends JFrame {
	
	public ExitProgram() {
		int confirmed = JOptionPane.showConfirmDialog(null, 
				"Are you sure you want to exit the program?", "Exit Program Message Box",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(confirmed == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}else {
			
		}
	}
}
