package financemanager;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import javax.swing.*;

public class Login {
    final String USER_NAME = "admin";
    final String PASS_WORD = "hello123";

    String username, password;
    // modal dialog to get user ID and password
    static String[] ConnectOptionNames = { "Login", "Cancel" };
    static String   ConnectTitle = "Farm Finance Manager Login";

    public boolean getIDandPassword() {
        JPanel      connectionPanel;

 	// Create the labels and text fields.
	JLabel     userNameLabel = new JLabel("Username:   ", JLabel.RIGHT);
 	JTextField userNameField = new JTextField("");
	JLabel     passwordLabel = new JLabel("Password:   ", JLabel.RIGHT);
	JTextField passwordField = new JPasswordField("");
	connectionPanel = new JPanel(false);
	connectionPanel.setLayout(new BoxLayout(connectionPanel, BoxLayout.X_AXIS));
	JPanel namePanel = new JPanel(false);
	namePanel.setLayout(new GridLayout(0, 1));
	namePanel.add(userNameLabel);
	namePanel.add(passwordLabel);
	JPanel fieldPanel = new JPanel(false);
	fieldPanel.setLayout(new GridLayout(0, 1));
	fieldPanel.add(userNameField);
	fieldPanel.add(passwordField);
	connectionPanel.add(namePanel);
	connectionPanel.add(fieldPanel);

        // Connect or quit
	if(JOptionPane.showOptionDialog(null, connectionPanel,
                                        ConnectTitle,
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null, ConnectOptionNames,
                                        ConnectOptionNames[0]) != 0) {
	    System.exit(0);
	}
        username = userNameField.getText();
        password = passwordField.getText();

        // Make sure username and password are correct
        if(username.equals(USER_NAME) || password.equals(PASS_WORD)) {
            return true;
        }
        else {
            return false;
        }
    }
}