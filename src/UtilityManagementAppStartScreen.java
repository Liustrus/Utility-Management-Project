import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UtilityManagementAppStartScreen extends JFrame implements ActionListener {

    UtilityManagementAppStartScreen(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel heading = new JLabel("Utility Management App");
        heading.setBounds(350, 50, 1000, 40);
        heading.setFont(new Font("", Font.PLAIN, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        JButton startButton = new JButton("Start");
        startButton.setBounds(475, 400, 80, 50);
        startButton.addActionListener(this);
//        loginButton.setBackground(Color.YELLOW);
        add(startButton);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new LoginScreen();

    }
    public static void main(String args[]) {
        new UtilityManagementAppStartScreen();
    }
}