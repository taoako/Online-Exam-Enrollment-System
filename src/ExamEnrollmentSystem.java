import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamEnrollmentSystem {

    // main frame
    JFrame frame;

    public ExamEnrollmentSystem() {
        showDashboard();
    }

    // Dashboard Page
    public void showDashboard() {
        frame = new JFrame("Exam Enrollment - Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome! Jude Michael Payo");
        welcomeLabel.setBounds(20, 20, 300, 25);
        frame.add(welcomeLabel);

        // Total Payment Section
        JLabel paymentLabel = new JLabel("Total Payment: ₱ 0.00");
        paymentLabel.setBounds(20, 60, 200, 25);
        frame.add(paymentLabel);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(250, 60, 80, 25);
        frame.add(payButton);

        // Student Info
        JLabel statusLabel = new JLabel("Single");
        statusLabel.setBounds(20, 100, 200, 25);
        frame.add(statusLabel);

        JLabel schoolLabel = new JLabel("University of Mindanao");
        schoolLabel.setBounds(20, 130, 250, 25);
        frame.add(schoolLabel);

        JLabel yearLabel = new JLabel("2nd Year - BSIT");
        yearLabel.setBounds(20, 160, 200, 25);
        frame.add(yearLabel);

        // Upcoming Exam Panel
        JPanel examPanel = new JPanel();
        examPanel.setBorder(BorderFactory.createTitledBorder("Upcoming Exam"));
        examPanel.setBounds(250, 100, 200, 100);
        examPanel.setLayout(new GridLayout(3, 1));

        examPanel.add(new JLabel("Subject: Math Basics"));
        examPanel.add(new JLabel("Date: Nov 10, 2025"));
        examPanel.add(new JLabel("Time: 10:00 AM - 12:00 PM"));

        frame.add(examPanel);

        // Proceed Button
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setBounds(180, 250, 120, 30);
        frame.add(proceedButton);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showExamSelection();
            }
        });

        frame.setVisible(true);
    }

    // Exam Selection Page
    public void showExamSelection() {
        frame = new JFrame("Exam Enrollment - Select Exam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(null);

        // Student Name
        JLabel nameLabel = new JLabel("Jude Michael Payo");
        nameLabel.setBounds(20, 20, 200, 25);
        frame.add(nameLabel);

        // Choose Exam
        JLabel chooseLabel = new JLabel("Choose an exam to take:");
        chooseLabel.setBounds(20, 60, 200, 25);
        frame.add(chooseLabel);

        // Dropdown list of exams
        String[] exams = {"Math Basics", "English Grammar", "Computer Programming", "Data Structures"};
        JComboBox<String> examDropdown = new JComboBox<>(exams);
        examDropdown.setBounds(20, 100, 200, 25);
        frame.add(examDropdown);

        // Proceed Button
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setBounds(180, 180, 120, 30);
        frame.add(proceedButton);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedExam = (String) examDropdown.getSelectedItem();
                frame.dispose();
                new ExamDetailsPage(selectedExam); // go to Exam Details Page
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ExamEnrollmentSystem();
    }
}
