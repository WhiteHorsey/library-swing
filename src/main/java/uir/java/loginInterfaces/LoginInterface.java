package uir.java.loginInterfaces;


import uir.java.VendorInterfaces.VendorHistorySalesInterface;
import uir.java.models.User;
import uir.java.utils.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static uir.java.utils.Constants.ErrorMessages.USERNAME_OR_PASSWORD_EMPTY;
import static uir.java.utils.Constants.ErrorMessages.USERNAME_OR_PASSWORD_INCORRECT;


public class LoginInterface {

    private User authenticateUserWithPassword(String username, String password, Connection connection) throws SQLException {
        String sql = "SELECT * FROM java_swing_db.users WHERE username=? AND password=?";
        try (PreparedStatement statementToBeSentToDB = connection.prepareStatement(sql)) {
            statementToBeSentToDB.setString(1, username);
            statementToBeSentToDB.setString(2, password);
            System.out.println("Searching for username: " + username + " ...");
            try (ResultSet rs = statementToBeSentToDB.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password")); // Storing password in object might be a security risk
                    user.setAdmin(rs.getBoolean("is_admin"));
                    return user;
                }
            }
        }
        return null;
    }

    public LoginInterface() {


        final JFrame frame = new JFrame("Login Interface");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = HORIZONTAL;
        constraints.anchor = CENTER;

        JLabel welcomeLabel = new JLabel("Welcome to Info Technologies");
        welcomeLabel.setFont(new Font("Arial", BOLD, 16));

        JLabel authLabel = new JLabel("Authentication Interface");
        authLabel.setFont(new Font("Arial", PLAIN, 14));

        final JLabel errorMessage = new JLabel("");
        errorMessage.setFont(new Font("Arial", PLAIN, 13));
        errorMessage.setForeground(Color.RED); // Set text color to red

        JLabel usernameLabel = new JLabel("Username:");
        final JTextField usernameField = new JTextField(15);
        usernameField.setText("vendor");

        JLabel passwordLabel = new JLabel("Password:");
        final JPasswordField passwordField = new JPasswordField(15);
        passwordField.setText("vendor");


        JButton goToInterface2Button = new JButton("Login");
        goToInterface2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                // todo : password must be hashed before to be sent to database
                String password = new String(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    errorMessage.setText(USERNAME_OR_PASSWORD_EMPTY); // Clear error message if both fields are filled
                    return;
                }
                try (Connection connection = DatabaseManager.getDatabaseConnection()) {
                    User user = authenticateUserWithPassword(username, password, connection);
                    if (user == null) {
                        errorMessage.setText(USERNAME_OR_PASSWORD_INCORRECT);
                        return;
                    }
                    if (user.isAdmin()) {
                        frame.dispose();
                        new VendorHistorySalesInterface();
                    } else {
                        frame.dispose();
                        new VendorHistorySalesInterface();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error related to SQL." + ex);
                    ex.printStackTrace();
                }
            }
        });

        // Welcome jlabel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(welcomeLabel, constraints);
        // Auth jlabel
        constraints.gridy = 1;
        panel.add(authLabel, constraints);
        // Username jlabel
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);
        // Username textfield
        constraints.gridx = 1;
        panel.add(usernameField, constraints);
        // Password jlabel
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(passwordLabel, constraints);
        // Password textfield
        constraints.gridx = 1;
        panel.add(passwordField, constraints);
        // button jbutton
        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = CENTER;
        panel.add(goToInterface2Button, constraints);
        // error message
        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(errorMessage, constraints);

        frame.add(panel);
        frame.pack(); // Adjust frame size to fit components
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
        frame.setSize(600, 400);

    }
}
