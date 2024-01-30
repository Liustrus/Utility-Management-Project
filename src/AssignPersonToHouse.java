import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssignPersonToHouse extends JFrame implements ActionListener {

    private JTextField houseIdField, personIdField;

    AssignPersonToHouse() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel titleLabel = new JLabel("Assign Person to House");
        titleLabel.setBounds(300, 50, 300, 30);
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        add(titleLabel);

        JLabel houseIdLabel = new JLabel("House ID:");
        houseIdLabel.setBounds(200, 150, 100, 30);
        add(houseIdLabel);

        JLabel personIdLabel = new JLabel("Person ID:");
        personIdLabel.setBounds(200, 200, 100, 30);
        add(personIdLabel);

        houseIdField = new JTextField();
        houseIdField.setBounds(300, 150, 100, 30);
        add(houseIdField);

        personIdField = new JTextField();
        personIdField.setBounds(300, 200, 100, 30);
        add(personIdField);

        JButton assignButton = new JButton("Assign Person to House");
        assignButton.setBounds(300, 250, 200, 30);
        assignButton.addActionListener(this);
        add(assignButton);

        JButton removeButton = new JButton("Remove Person from House");
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
        int personId = Integer.parseInt(personIdField.getText());

        try {
            if (e.getActionCommand().equals("Assign Person to House")) {
                assignPersonToHouse(houseId, personId);
            } else if (e.getActionCommand().equals("Remove Person from House")) {
                removePersonFromHouse(houseId, personId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void assignPersonToHouse(int houseId, int personId) throws SQLException {
        String assignQuery = "INSERT INTO comune (House_Id, Person_Id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(assignQuery)) {

            preparedStatement.setInt(1, houseId);
            preparedStatement.setInt(2, personId);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Person assigned to house successfully.");
        }
    }

    private void removePersonFromHouse(int houseId, int personId) throws SQLException {
        String removeQuery = "DELETE FROM house_management WHERE House_Id = ? AND Person_Id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {

            preparedStatement.setInt(1, houseId);
            preparedStatement.setInt(2, personId);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Person removed from house successfully.");
        }
    }
}
