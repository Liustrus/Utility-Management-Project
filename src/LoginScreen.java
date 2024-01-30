
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreen extends JFrame implements ActionListener {

    JTextField loginUserName, loginPassword;

    LoginScreen() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel heading = new JLabel("Please enter your credentials");
        heading.setBounds(350, 50, 1000, 40);
        heading.setFont(new Font("", Font.PLAIN, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        JLabel loginTextUserName = new JLabel("User name:");
        loginTextUserName.setBounds(400, 200, 100, 30);
        add(loginTextUserName);

        JLabel loginTextUserPassword = new JLabel("Password:");
        loginTextUserPassword.setBounds(400, 250, 100, 30);
        add(loginTextUserPassword);

        loginUserName = new JTextField();
        loginUserName.setBounds(500, 200, 100, 30);
        add(loginUserName);

        loginPassword = new JTextField();
        loginPassword.setBounds(500, 250, 100, 30);
        add(loginPassword);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(500, 300, 100, 30);
        add(loginButton);
        loginButton.addActionListener(this);
    }

    private int getPersonId(String username, String password) {
        String query = "SELECT Id FROM person WHERE Login_User_Name = ? AND Login_Password = ?";
        return getUserId(username, password, query);
    }

    private int getManagerId(String username, String password) {
        String query = "SELECT Id FROM manager WHERE Login_User_Name = ? AND Login_Password = ?";
        return getUserId(username, password, query);
    }

    private int getAdminId(String username, String password) {
        String query = "SELECT Id FROM admin WHERE Login_User_Name = ? AND Login_Password = ?";
        return getUserId(username, password, query);
    }

    private int getUserId(String username, String password, String query) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("Id");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String username = loginUserName.getText();
            String password = loginPassword.getText();

            int personId = getPersonId(username, password);
            int managerId = getManagerId(username, password);
            int adminId = getAdminId(username, password);

            if (personId != -1) {
                setVisible(false);
                new PersonScreen(personId);
            } else if (managerId != -1) {
                setVisible(false);
                new ManagerScreen(managerId);
            } else if (adminId != -1) {
                setVisible(false);
                new AdminScreen();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed, try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}