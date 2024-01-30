import javax.swing.*;
import java.awt.*;

public class AdminScreen extends JFrame {

    AdminScreen() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(200, 200);
        setVisible(true);

        JLabel heading = new JLabel("The Admin Has Arrived!");
        heading.setBounds(350, 50, 1000, 40);
        heading.setFont(new Font("", Font.PLAIN, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        JButton editHouses = new JButton("Edit Houses");
        editHouses.setBounds(400, 200, 200, 30);
        editHouses.addActionListener(e -> {
            setVisible(false);
            new EditHouses();
        });
        add(editHouses);

        JButton assignPersonToHouse = new JButton("Assign Person To House");
        assignPersonToHouse.setBounds(400, 250, 200, 30);
        assignPersonToHouse.addActionListener(e -> {
            setVisible(false);
            new AssignPersonToHouse();
        });
        add(assignPersonToHouse);

        JButton assignManagerForHouse = new JButton("Assign Manager To House");
        assignManagerForHouse.setBounds(400, 300, 200, 30);
        assignManagerForHouse.addActionListener(e -> {
            setVisible(false);
            new AssignManagerForHouse();
        });
        add(assignManagerForHouse);

        JButton editManagers = new JButton("Edit Managers");
        editManagers.setBounds(400, 350, 200, 30);
        editManagers.addActionListener(e -> {
            setVisible(false);
            new EditManagers();
        });
        add(editManagers);

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

}
