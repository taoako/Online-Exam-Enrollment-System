import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Exam Details Page Class
public class ExamDetailsPage {

    JFrame frame;

    public ExamDetailsPage(String examName) {
        showExamDetails(examName);
    }

    public void showExamDetails(String examName) {
        frame = new JFrame("Exam Enrollment - Exam Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Start Exam");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 20, 200, 30);
        frame.add(titleLabel);

        // Exam Name
        JLabel examLabel = new JLabel(examName);
        examLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        examLabel.setBounds(20, 70, 300, 25);
        frame.add(examLabel);

        // Assigned Schedule (mock example)
        JLabel scheduleLabel = new JLabel("Schedule: Nov 15, 2025 - 9:00 AM, Room 204");
        scheduleLabel.setBounds(20, 120, 400, 25);
        frame.add(scheduleLabel);

        // Start Exam Button
        JButton startButton = new JButton("Start Exam");
        startButton.setBounds(180, 180, 120, 30);
        frame.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, 
                        "Exam Started for: " + examName + "\nGood Luck!");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
