package views;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PaymentForm extends JDialog {

    private final Connection conn;
    private final int studentId;
    private final int amount;

    public PaymentForm(Frame owner, Connection conn, int studentId, int defaultAmount, int examId, String examName,
            int examFee) {
        super(owner, "Add Balance", true);
        this.conn = conn;
        this.studentId = studentId;
        this.amount = defaultAmount;
        initUI();
    }

    private void initUI() {
        setSize(420, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        JPanel main = new JPanel(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        main.add(new JLabel("Add Balance (₱):"), gbc);
        gbc.gridx = 1;
        JTextField amountField = new JTextField(String.valueOf(amount));
        main.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(new JLabel("Payment Method:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> methodBox = new JComboBox<>(
                new String[] { "GCash", "Credit Card", "PayMaya", "Bank Transfer" });
        main.add(methodBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        main.add(new JLabel("Reference #:"), gbc);
        gbc.gridx = 1;
        JTextField refField = new JTextField();
        main.add(refField, gbc);

        JButton btnConfirm = new JButton("Confirm Payment");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        main.add(btnConfirm, gbc);

        add(main, BorderLayout.CENTER);

        btnConfirm.addActionListener(e -> processTopUp(amountField, methodBox, refField));
    }

    private void processTopUp(JTextField amountField, JComboBox<String> methodBox, JTextField refField) {
        String method = (String) methodBox.getSelectedItem();
        String ref = refField.getText().trim();

        if (ref.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a reference number.", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double topUpAmount;
        try {
            topUpAmount = Double.parseDouble(amountField.getText().trim());
            if (topUpAmount <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            conn.setAutoCommit(false);

            // 1️⃣ Insert into payments table
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO payments (student_id, amount, payment_method, reference_no) VALUES (?, ?, ?, ?)");
            ps.setInt(1, studentId);
            ps.setDouble(2, topUpAmount);
            ps.setString(3, method);
            ps.setString(4, ref);
            ps.executeUpdate();

            // 2️⃣ Update student's balance
            PreparedStatement update = conn.prepareStatement(
                    "UPDATE students SET balance = balance + ? WHERE id = ?");
            update.setDouble(1, topUpAmount);
            update.setInt(2, studentId);
            update.executeUpdate();

            conn.commit();

            JOptionPane.showMessageDialog(this,
                    "✅ Payment of ₱" + topUpAmount + " added to your balance.",
                    "Balance Updated",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ignored) {
            }
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }
}
