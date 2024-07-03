package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection=null;
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atto_spring_core", "postgres", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
