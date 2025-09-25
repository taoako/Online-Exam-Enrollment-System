import java.awt.*;
import javax.swing.*;

public class ExamEnrollmentSystem extends JFrame {
    private JFrame frame;

    public ExamEnrollmentSystem() {
        showDashboard();
    }

    // Dashboard Page (Updated UI)
    public void showDashboard() {
        frame = new JFrame("Exam Enrollment - Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null); 
        frame.setLayout(null);

        // Top Navbar
        JPanel topNavbar = new JPanel();
        topNavbar.setBackground(new Color(70, 130, 180)); // SteelBlue
        topNavbar.setBounds(0, 0, 800, 50);
        topNavbar.setLayout(new BorderLayout(10, 10));
        topNavbar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JLabel titleLabel = new JLabel("Exam Enrollment - Dashboard", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        topNavbar.add(titleLabel, BorderLayout.WEST);

        // Logout Button (same style as sidebar)
        JButton logoutButton = new JButton("Logout");
        styleNavButton(logoutButton, new Color(220, 20, 60));
        logoutButton.addActionListener(e -> System.exit(0));
        topNavbar.add(logoutButton, BorderLayout.EAST);

        frame.add(topNavbar);

        // Sidebar Panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(30, 144, 255)); // DodgerBlue
        sidebarPanel.setBounds(0, 50, 200, 450);
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel userNameLabel = new JLabel("Jade (Student)", JLabel.CENTER);
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(userNameLabel);
        sidebarPanel.add(Box.createVerticalStrut(20));

        sidebarPanel.add(createSidebarButton("Dashboard"));
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(createSidebarButton("My Marks"));
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(createSidebarButton("Manage Course"));

        frame.add(sidebarPanel);

        // Main Content Area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(220, 60, 550, 380);
        contentPanel.setBackground(new Color(245, 248, 255));

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome! Jude Michael Payo");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBounds(20, 20, 400, 25);
        contentPanel.add(welcomeLabel);

        // Payment Panel
        JPanel paymentPanel = createCardPanel();
        paymentPanel.setBounds(20, 60, 300, 60);
        paymentPanel.setLayout(null);

        JLabel paymentLabel = new JLabel("Total Payment: ₱ 0.00");
        paymentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        paymentLabel.setBounds(10, 15, 180, 30);
        paymentPanel.add(paymentLabel);

        JButton payButton = new JButton("Pay");
        styleNavButton(payButton, new Color(34, 139, 34)); // Green
        payButton.setBounds(200, 15, 80, 30);
        paymentPanel.add(payButton);

        contentPanel.add(paymentPanel);

        // Personal Info
        JLabel statusLabel = new JLabel("Single");
        statusLabel.setBounds(20, 140, 200, 20);
        contentPanel.add(statusLabel);

        JLabel schoolLabel = new JLabel("University of Mindanao");
        schoolLabel.setBounds(20, 170, 250, 20);
        contentPanel.add(schoolLabel);

        JLabel yearLabel = new JLabel("2nd Year - BSIT");
        yearLabel.setBounds(20, 200, 200, 20);
        contentPanel.add(yearLabel);

        // Upcoming Exam Box jj
        JPanel examPanel = createCardPanel();
        examPanel.setLayout(new BoxLayout(examPanel, BoxLayout.Y_AXIS));
        examPanel.setBounds(300, 140, 230, 120);

        JLabel subjectLabel = new JLabel("Subject: Math Basics");
        JLabel dateLabel = new JLabel("Date: Nov 10, 2025");
        JLabel timeLabel = new JLabel("Time: 10:00 AM - 12:00 PM");

        subjectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        examPanel.add(subjectLabel);
        examPanel.add(Box.createVerticalStrut(5));
        examPanel.add(dateLabel);
        examPanel.add(Box.createVerticalStrut(5));
        examPanel.add(timeLabel);

        contentPanel.add(examPanel);

        // Proceed Button
        JButton proceedButton = new JButton("Proceed");
        styleNavButton(proceedButton, new Color(70, 130, 180)); // Match theme
        proceedButton.setBounds(200, 300, 120, 35);
        contentPanel.add(proceedButton);

        frame.add(contentPanel);
        frame.setVisible(true);
    }

    // Sidebar Button Helper
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(25, 25, 112)); // MidnightBlue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 35));
        return button;
    }

    // Apply uniform styling for top buttons (Logout, Pay, Proceed)
    private void styleNavButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    // Card-style panel creator (consistent theme)
    private JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return panel;
    }

    public static void main(String[] args) {
        new ExamEnrollmentSystem();
    }
}
