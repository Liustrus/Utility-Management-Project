import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditHouseUtilities extends JFrame implements ActionListener {

    private JTextField houseIdField, utilityIdField, priceField;

    EditHouseUtilities() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel titleLabel = new JLabel("Edit House Utilities");
        titleLabel.setBounds(300, 50, 250, 30);
        titleLabel.setFont(new Font("", Font.PLAIN, 24));
        add(titleLabel);

        JLabel houseIdLabel = new JLabel("House ID:");
        houseIdLabel.setBounds(200, 150, 100, 30);
        add(houseIdLabel);

        JLabel utilityIdLabel = new JLabel("Utility ID:");
        utilityIdLabel.setBounds(200, 200, 100, 30);
        add(utilityIdLabel);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(200, 250, 100, 30);
        add(priceLabel);

        houseIdField = new JTextField();
        houseIdField.setBounds(300, 150, 100, 30);
        add(houseIdField);

        utilityIdField = new JTextField();
        utilityIdField.setBounds(300, 200, 100, 30);
        add(utilityIdField);

        priceField = new JTextField();
        priceField.setBounds(300, 250, 100, 30);
        add(priceField);

        JButton addButton = new JButton("Add Utility");
        addButton.setBounds(300, 300, 150, 30);
        addButton.addActionListener(this);
        add(addButton);

        JButton deleteButton = new JButton("Delete Utility");
        deleteButton.setBounds(300, 350, 150, 30);
        deleteButton.addActionListener(this);
        add(deleteButton);

        JButton updateButton = new JButton("Update Price");
        updateButton.setBounds(300, 400, 150, 30);
        updateButton.addActionListener(this);
        add(updateButton);

        JButton logOffButton = new JButton("Log Off");
        logOffButton.setBounds(300, 500, 150, 30);
        logOffButton.addActionListener(e -> {
            setVisible(false);
            new LoginScreen();
        });
        add(logOffButton);

    }

    public static void main(String[] args) {
        new EditHouseUtilities();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int houseId = Integer.parseInt(houseIdField.getText());
        int utilityId = Integer.parseInt(utilityIdField.getText());

        try {
            if (e.getActionCommand().equals("Add Utility")) {
                addUtility(houseId, utilityId);
            } else if (e.getActionCommand().equals("Delete Utility")) {
                removeUtility(houseId, utilityId);
            } else if (e.getActionCommand().equals("Update Price")) {
                updatePrice(houseId, utilityId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addUtility(int houseId, int utilityId) throws SQLException {
        double price = Double.parseDouble(priceField.getText());
        String addQuery = "INSERT INTO house_utilities (House_Id, Utility_Id, Price) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {

            preparedStatement.setInt(1, houseId);
            preparedStatement.setInt(2, utilityId);
            preparedStatement.setDouble(3, price);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Utility added successfully.");
        }
    }

    private void removeUtility(int houseId, int utilityId) throws SQLException {
        String removeQuery = "DELETE FROM house_utilities WHERE House_Id = ? AND Utility_Id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {

            preparedStatement.setInt(1, houseId);
            preparedStatement.setInt(2, utilityId);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Utility removed successfully.");
        }
    }

    private void updatePrice(int houseId, int utilityId) throws SQLException {
        try {
            double newPrice = Double.parseDouble(priceField.getText());
            String updateQuery = "UPDATE house_utilities SET Price = ? WHERE House_Id = ? AND Utility_Id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

                preparedStatement.setDouble(1, newPrice);
                preparedStatement.setInt(2, houseId);
                preparedStatement.setInt(3, utilityId);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Price updated successfully.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
        }
    }
}
