package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/exam_enrollment";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    // This ensures only one connection is created
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); // Important for MySQL
                } catch (ClassNotFoundException e) {
                    System.out.println("❌ MySQL JDBC Driver not found!");
                }
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("✅ Connected to MySQL database successfully!");
                } catch (SQLException e) {
                    System.out.println("❌ Database connection failed: " + e.getMessage());
                    connection = null;
                }
            }
        } catch (SQLException e) {
            // If checking isClosed() throws, reset connection so callers will attempt to
            // recreate
            System.out.println("❌ Error checking connection state: " + e.getMessage());
            connection = null;
        }
        return connection;
    }
}
