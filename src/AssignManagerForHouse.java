import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssignManagerForHouse extends JFrame implements ActionListener {

    private JTextField houseIdField, managerIdField;

    AssignManagerForHouse() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel titleLabel = new JLabel("Assign Manager to House");
        titleLabel.setBounds(300, 50, 300, 30);
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        add(titleLabel);

        JLabel houseIdLabel = new JLabel("House ID:");
        houseIdLabel.setBounds(200, 150, 100, 30);
        add(houseIdLabel);

        JLabel managerIdLabel = new JLabel("Manager ID:");
        managerIdLabel.setBounds(200, 200, 100, 30);
        add(managerIdLabel);

        houseIdField = new JTextField();
        houseIdField.setBounds(300, 150, 100, 30);
        add(houseIdField);

        managerIdField = new JTextField();
        managerIdField.setBounds(300, 200, 100, 30);
        add(managerIdField);

        JButton assignButton = new JButton("Assign Manager to House");
        assignButton.setBounds(300, 250, 200, 30);
        assignButton.addActionListener(this);
        add(assignButton);

        JButton removeButton = new JButton("Remove Manager from House");
        removeButton.setBounds(300, 300, 200, 30);
        removeButton.addActionListener(this);
        add(removeButton);

        JButton logOffButton = new JButton("Log Off");
        logOffButton.setBounds(450, 500, 100, 30);
        logOffButton.addActionListener(e -> {
            setVisible(false);
            new LoginScreen();
        });
        add(logOffButton);
    }

    public static void main(String[] args) {
        new AssignPersonToHouse();
    }

    public void actionPerformed(ActionEvent e) {
        int houseId = Integer.parseInt(houseIdField.getText());
        int personId = Integer.parseInt(managerIdField.getText());

        try {
            if (e.getActionCommand().equals("Assign Manager to House")) {
                assignPersonToHouse(houseId, personId);
            } else if (e.getActionCommand().equals("Remove Manager from House")) {
                removePersonFromHouse(houseId, personId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void assignPersonToHouse(int houseId, int managerId) throws SQLException {
        String assignQuery = "INSERT INTO house_management (House_Id, Manager_Id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(assignQuery)) {

            preparedStatement.setInt(1, houseId);
            preparedStatement.setInt(2, managerId);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Manager assigned to house successfully.");
        }
    }

    private void removePersonFromHouse(int houseId, int managerId) throws SQLException {
        String removeQuery = "DELETE FROM house_management WHERE House_Id = ? AND Manager_Id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {

            preparedStatement.setInt(1, houseId);
            preparedStatement.setInt(2, managerId);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Manager no longer manages the house.");
        }
    }
}
