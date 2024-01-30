import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonScreen extends JFrame {

    PersonScreen(int personId) {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel heading = new JLabel("Welcome back!" );
        heading.setBounds(350, 50, 1000, 40);
        heading.setFont(new Font("", Font.PLAIN, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        String address = getAddress(personId);
        JLabel addressLabel = new JLabel("Address: " + address);
        addressLabel.setBounds(200, 100, 600, 30);
        addressLabel.setFont(new Font("", Font.PLAIN, 25));
        add(addressLabel);

        String utilitiesInfo = getUtilitiesInfo(personId);
        JTextArea utilitiesTextArea = new JTextArea(utilitiesInfo);
        utilitiesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(utilitiesTextArea);
        scrollPane.setBounds(200, 150, 600, 300);
        scrollPane.setFont(new Font("", Font.PLAIN, 20));
        add(scrollPane);

        JButton logOffButton = new JButton("Log Off");
        logOffButton.setBounds(450, 500, 100, 30);
        logOffButton.addActionListener(e -> {
            setVisible(false);
            new LoginScreen();
        });
        add(logOffButton);
    }

    private String getAddress(int personId) {
        String query = "SELECT h.Address FROM house h LEFT JOIN comune c ON c.House_Id = h.Id LEFT JOIN person p ON c.Person_Id = p.Id WHERE p.Id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, personId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Address");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Address not found";
    }

    private String getUtilitiesInfo(int personId) {
        StringBuilder utilitiesInfo = new StringBuilder("Utilities and their prices:\n\n");

        String query = "SELECT utility.Name, house_utilities.Price FROM house_utilities LEFT JOIN comune ON house_utilities.House_Id = comune.House_Id LEFT JOIN utility ON house_utilities.Utility_Id = utility.Id WHERE comune.Person_Id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, personId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String utilityName = resultSet.getString("Name");
                    double price = resultSet.getDouble("Price");
                    utilitiesInfo.append(utilityName).append(": ").append(price).append(" EUR").append("\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return utilitiesInfo.toString();
    }
}
