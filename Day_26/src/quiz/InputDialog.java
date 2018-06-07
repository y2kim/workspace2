package quiz;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputDialog extends JDialog{
	 private JLabel idLabel = new JLabel("학생 번호:");
     private JLabel nameLabel = new JLabel("학생 이름 :");
     private JLabel korLabel = new JLabel("국어 점수 :");
     private JLabel engLabel = new JLabel("영어 점수 :");
     private JLabel mathLabel = new JLabel("수학 점수 :");
     
     private JTextField idField = new JTextField();
     private JTextField nameField = new JTextField();
     private JTextField korField = new JTextField();
     private JTextField engField = new JTextField();
     private JTextField mathField = new JTextField();
     private JButton okbttn = new JButton("입력");
     
     private JPanel idPane = new JPanel(new GridLayout(1,2));
     private JPanel namePane = new JPanel(new GridLayout(1,2));
     private JPanel korPane = new JPanel(new GridLayout(1,2));
     private JPanel engPane = new JPanel(new GridLayout(1,2));
     private JPanel mathPane = new JPanel(new GridLayout(1,2));   
     private JPanel okPane = new JPanel(new BorderLayout());
     
     private InputDialog self = this;

        
     public void compInit() {
        this.idPane.add(idLabel);
        this.idPane.add(idField);
        
        this.namePane.add(nameLabel);
        this.namePane.add(nameField);
        
        this.korPane.add(korLabel);
        this.korPane.add(korField);
        
        this.engPane.add(engLabel);
        this.engPane.add(engField);
        
        this.mathPane.add(mathLabel);
        this.mathPane.add(mathField);
        
        this.okPane.add(okbttn, BorderLayout.CENTER);
              
        this.add(idPane);
        this.add(namePane);
        this.add(korPane);
        this.add(engPane);
        this.add(mathPane);
        this.add(okPane);
     }
     
     public void eventInit() {
           this.okbttn.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                 try {
                    int confirm = JOptionPane.showConfirmDialog(null,"신규 정보를 등록 하시겠습니까?", "확인", JOptionPane.OK_CANCEL_OPTION);
                    if(confirm == JOptionPane.OK_OPTION) {
                       Student stdInfo = new Student(Integer.parseInt(idField.getText()), nameField.getText(),Integer.parseInt(korField.getText()), Integer.parseInt(engField.getText()), Integer.parseInt(mathField.getText()));
                       StudentMember.HashAddStudent(stdInfo.getStuId(), stdInfo);
                       self.dispose();

                    }else if(confirm == JOptionPane.CANCEL_OPTION) {                     
                    }   
                 }catch(Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "형식에 맞게 다시 입력해주세요.");
                 }finally {
                    idField.setText("");
                    nameField.setText("");
                    korField.setText("");
                    engField.setText("");
                    mathField.setText("");            
                 }                  
              }
           });
        }
     
     
     public InputDialog(MainProgram mainFrame) {
        this.setTitle("학생 정보 입력");
        this.setSize(300, 400);
        this.setLocationRelativeTo(mainFrame);
        this.setLayout(new GridLayout(6,1));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.compInit();
        this.eventInit();
        this.setVisible(true);
     }

}
