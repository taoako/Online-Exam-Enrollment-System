package views;

import dao.AdminDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import models.Admin;
import models.Course;
import models.Student;

public class LoginFormGUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, adminLoginButton;
    private JCheckBox adminCheckbox;

    public LoginFormGUI() {
        initUI();
    }

    private void initUI() {
        setTitle("Exam Enrollment System - Login");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));

        // Email field
        JLabel emailLabel = new JLabel("Email/Username:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(emailLabel);
        mainPanel.add(Box.createVerticalStrut(5));

        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(emailField);
        mainPanel.add(Box.createVerticalStrut(15));

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createVerticalStrut(5));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(passwordField);
        mainPanel.add(Box.createVerticalStrut(15));

        // Admin checkbox
        adminCheckbox = new JCheckBox("Login as Administrator");
        adminCheckbox.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminCheckbox.setBackground(Color.WHITE);
        adminCheckbox.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(adminCheckbox);
        mainPanel.add(Box.createVerticalStrut(20));

        // Login button
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(300, 35));
        loginButton.setMaximumSize(new Dimension(300, 35));
        loginButton.addActionListener(event -> handleLogin());
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(10));

        // Register button
        registerButton = new JButton("Register New Student Account");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(300, 35));
        registerButton.setMaximumSize(new Dimension(300, 35));
        registerButton.addActionListener(event -> openRegisterDialog());
        mainPanel.add(registerButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void handleLogin() {
        String emailOrUsername = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (emailOrUsername.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email/username and password.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (adminCheckbox.isSelected()) {
            // Admin login
            AdminDAO adminDAO = new AdminDAO();
            // Create admin table if it doesn't exist
            adminDAO.createAdminTableIfNotExists();

            Admin admin = adminDAO.loginAdmin(emailOrUsername, password);

            if (admin != null) {
                JOptionPane.showMessageDialog(this, "Admin login successful! Welcome, " + admin.getUsername(),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AdminDashboard(admin).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid admin credentials.",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Student login
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.loginStudent(emailOrUsername, password);

            if (student != null) {
                JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + student.getName(),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new ExamEnrollmentSystem(student.getId()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openRegisterDialog() {
        JDialog registerDialog = new JDialog(this, "Register New Student", true);
        registerDialog.setSize(400, 400);
        registerDialog.setLocationRelativeTo(this);
        registerDialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField regEmailField = new JTextField(20);
        formPanel.add(regEmailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField regPasswordField = new JPasswordField(20);
        formPanel.add(regPasswordField, gbc);

        // Course
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        JComboBox<Course> courseCombo = new JComboBox<>();
        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getAllCourses();
        for (Course course : courses) {
            courseCombo.addItem(course);
        }
        formPanel.add(courseCombo, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Register");
        submitButton.setBackground(new Color(34, 139, 34));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(event -> {
            String name = nameField.getText().trim();
            String email = regEmailField.getText().trim();
            String password = new String(regPasswordField.getPassword());
            Course selectedCourse = (Course) courseCombo.getSelectedItem();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || selectedCourse == null) {
                JOptionPane.showMessageDialog(registerDialog, "Please fill all fields.",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Student newStudent = new Student(name, email, password, selectedCourse.getId());
            StudentDAO dao = new StudentDAO();

            if (dao.registerStudent(newStudent)) {
                JOptionPane.showMessageDialog(registerDialog, "Registration successful! You can now login.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                registerDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(registerDialog, "Registration failed. Email may already exist.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        formPanel.add(submitButton, gbc);

        registerDialog.add(formPanel, BorderLayout.CENTER);
        registerDialog.setVisible(true);
    }
}