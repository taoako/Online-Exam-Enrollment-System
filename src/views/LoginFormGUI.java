package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.StudentDAO;
import models.Student;

public class LoginFormGUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton;

    public LoginFormGUI() {
        setTitle("Online Enrollment Exam - Sign In");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel signInTitle = new JLabel("Sign In");
        signInTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(signInTitle, gbc);

        JLabel emailLabel = new JLabel("E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        signInButton.setBackground(new Color(70, 130, 180));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);
        signInButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(signInButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        // ✅ Sign In logic
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFormGUI.this,
                            "Please enter both email and password.",
                            "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.loginStudent(email, password);

                if (student != null) {
                    JOptionPane.showMessageDialog(LoginFormGUI.this,
                            "Welcome, " + student.getName() + "!",
                            "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    // ✅ Pass student ID to the dashboard
                    int studentId = student.getId();
                    System.out.println("✅ Logged in student ID: " + studentId);

                    dispose(); // close login window
                    SwingUtilities.invokeLater(() -> {
                        ExamEnrollmentSystem examSystem = new ExamEnrollmentSystem(studentId);
                        examSystem.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(LoginFormGUI.this,
                            "Invalid email or password.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFormGUI login = new LoginFormGUI();
            login.setVisible(true);
        });
    }
}
