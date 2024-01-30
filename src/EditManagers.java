import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditManagers extends JFrame implements ActionListener {

    private JTextField firstNameField, lastNameField;

    EditManagers() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel titleLabel = new JLabel("Edit Managers");
        titleLabel.setBounds(300, 50, 250, 30);
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        add(titleLabel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(200, 150, 100, 30);
        add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(200, 200, 100, 30);
        add(lastNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(300, 150, 100, 30);
        add(firstNameField);

        lastNameField = new JTextField();
        lastNameField.setBounds(300, 200, 100, 30);
        add(lastNameField);

        JButton addManagerButton = new JButton("Add Manager");
        addManagerButton.setBounds(300, 250, 150, 30);
        addManagerButton.addActionListener(this);
        add(addManagerButton);

        JButton deleteManagerButton = new JButton("Delete Manager");
        deleteManagerButton.setBounds(300, 300, 150, 30);
        deleteManagerButton.addActionListener(this);
        add(deleteManagerButton);

        JButton logOffButton = new JButton("Log Off");
        logOffButton.setBounds(300, 400, 150, 30);
        logOffButton.addActionListener(e -> {
            setVisible(false);
            new LoginScreen();
        });
        add(logOffButton);
    }

    public static void main(String[] args) {
        new EditManagers();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Add Manager")) {
                addManager();
            } else if (e.getActionCommand().equals("Delete Manager")) {
                deleteManager();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addManager() throws SQLException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int nextId = getNextManagerId();

        String addQuery = "INSERT INTO manager (Id, First_Name, Last_Name, Login_User_Name, Login_Password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {

            preparedStatement.setInt(1, nextId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Manager added successfully.");
        }
    }

    private int getNextManagerId() throws SQLException {
        int nextId = 1;

        String maxIdQuery = "SELECT MAX(Id) FROM manager";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(maxIdQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                nextId = resultSet.getInt(1) + 1;
            }
        }

        return nextId;
    }

    private void deleteManager() throws SQLException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        String deleteQuery = "DELETE FROM manager WHERE First_Name = ? AND Last_Name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Manager deleted successfully.");
        }
    }
}
