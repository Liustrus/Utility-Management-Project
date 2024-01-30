import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerScreen extends JFrame {

    ManagerScreen(int managerId) {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel heading = new JLabel("Welcome back to work!");
        heading.setBounds(350, 50, 1000, 40);
        heading.setFont(new Font("", Font.PLAIN, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        JLabel managerIdLabel = new JLabel("Your manager Id is: " + managerId);
        managerIdLabel.setBounds(200, 100, 600, 30);
        managerIdLabel.setFont(new Font("", Font.PLAIN, 25));
        add(managerIdLabel);

        String utilitiesInfo = getUtilitiesInfo(managerId);
        JTextArea utilitiesTextArea = new JTextArea(utilitiesInfo);
        utilitiesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(utilitiesTextArea);
        scrollPane.setBounds(200, 150, 600, 200);
        scrollPane.setFont(new Font("", Font.PLAIN, 20));
        add(scrollPane);

        JButton editHouseUtilities = new JButton("Edit House Utilities");
        editHouseUtilities.setBounds(400, 400, 200, 30);
        editHouseUtilities.addActionListener(e -> {
            setVisible(false);
            new EditHouseUtilities();
        });
        add(editHouseUtilities);

        JButton logOffButton = new JButton("Log Off");
        logOffButton.setBounds(450, 500, 100, 30);
        logOffButton.addActionListener(e -> {
            setVisible(false);
            new LoginScreen();
        });
        add(logOffButton);
    }


    private String getUtilitiesInfo(int managerId) {
        StringBuilder utilitiesInfo = new StringBuilder("Utilities and their prices:\n\n");

        String query = "SELECT h.Id AS HouseId, h.Address, u.Id AS UtilityId, u.Name AS UtilityName, hu.Price FROM house_utilities hu LEFT JOIN house h ON h.Id = hu.House_Id LEFT JOIN house_management hm ON hm.House_Id = h.Id LEFT JOIN utility u ON u.Id = hu.Utility_Id WHERE hm.Manager_Id = ? ORDER BY h.Id, u.Id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, managerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int houseId = resultSet.getInt("HouseId");
                    String address = resultSet.getString("Address");
                    int utilityId = resultSet.getInt("UtilityId");
                    double price = resultSet.getDouble("Price");
                    String utilityName = resultSet.getString("UtilityName");
                    utilitiesInfo.append(houseId).append("   ").append(address).append("   ||   ").append(utilityId).append("   ").append(utilityName).append("   ").append(price).append(" EUR").append("\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return utilitiesInfo.toString();
    }
}
