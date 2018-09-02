import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    private JPanel mainPanel;
    private JTextField usernametextField;
    private JPasswordField passwordField;
    private JButton registerbtn;
    private JLabel lbluser;
    private JLabel lblpwd;
    private JPasswordField passwordField1;
    private JTextField emailField;
    private JLabel cfPWD;


public Register(){
    registerbtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
    if(usernametextField.equals(""))
    {
        System.out.println();
    }
        }
    });
}
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new Register().mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


