import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditHouses extends JFrame implements ActionListener {

    private JTextField houseAddressField;

    EditHouses() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel titleLabel = new JLabel("Edit Houses");
        titleLabel.setBounds(300, 50, 250, 30);
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        add(titleLabel);

        JLabel firstNameLabel = new JLabel("Full address:");
        firstNameLabel.setBounds(200, 150, 100, 30);
        add(firstNameLabel);

        houseAddressField = new JTextField();
        houseAddressField.setBounds(300, 150, 100, 30);
        add(houseAddressField);

        JButton addManagerButton = new JButton("Add House");
        addManagerButton.setBounds(300, 250, 150, 30);
        addManagerButton.addActionListener(this);
        add(addManagerButton);

        JButton deleteManagerButton = new JButton("Delete House");
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
            if (e.getActionCommand().equals("Add House")) {
                addHouse();
            } else if (e.getActionCommand().equals("Delete House")) {
                deleteHouse();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addHouse() throws SQLException {
        String houseAddress = houseAddressField.getText();
        int nextId = getNextHouseId();

        String addQuery = "INSERT INTO house (Id, Address) VALUES (?, ?);";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {

            preparedStatement.setInt(1, nextId);
            preparedStatement.setString(2, houseAddress);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "House added successfully.");
        }
    }

    private int getNextHouseId() throws SQLException {
        int nextId = 1;

        String maxIdQuery = "SELECT MAX(Id) FROM house";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(maxIdQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                nextId = resultSet.getInt(1) + 1;
            }
        }

        return nextId;
    }

    private void deleteHouse() throws SQLException {
        String houseAddress = houseAddressField.getText();

        String deleteQuery = "DELETE FROM house WHERE Address = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, houseAddress);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "House deleted successfully.");
        }
    }
}
