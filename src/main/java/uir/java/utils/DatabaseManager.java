package uir.java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java_swing_db"; // Replace with your DB URL
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "kun123456789+";

    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}