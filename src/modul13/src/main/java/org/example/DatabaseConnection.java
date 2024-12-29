package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {

                String url = "jdbc:mysql://localhost:3306/konkurensi";
                String username = "root";
                String password = "";
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println("Error connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }
}
