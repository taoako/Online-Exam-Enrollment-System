import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFormGUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton;

    public LoginFormGUI() {
        // Set up the JFrame
        setTitle("Online Enrollment Exam - Sign In");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a main panel with a BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); // A light background for contrast

        // Create a panel for the form elements
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        formPanel.setBackground(Color.WHITE); // White background for the form itself

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Sign In"
        JLabel signInTitle = new JLabel("Sign In");
        signInTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        formPanel.add(signInTitle, gbc);

        // Email Label
        JLabel emailLabel = new JLabel("E-mail :");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        formPanel.add(emailLabel, gbc);

        // Email
        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        //Password
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordLabel, gbc);

        // Password Text Field
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(passwordField, gbc);

        // Sign In Button
        signInButton = new JButton("sign in");
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        signInButton.setBackground(new Color(70, 130, 180)); // SteelBlue color
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false); // Remove focus border
        signInButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Padding
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        formPanel.add(signInButton, gbc);

        // Add action listener to the button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                JOptionPane.showMessageDialog(LoginFormGUI.this,
                        "Email: " + email + "\nPassword: " + password,
                        "Login Attempt",
                        JOptionPane.INFORMATION_MESSAGE);
                // In a real application, you would validate credentials here.
            }
        });

        // Add the form panel to the center of the main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel); // Add the main panel to the JFrame

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFormGUI();
            }
        });
    }
}