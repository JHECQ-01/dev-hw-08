package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static volatile Database instance;
    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private Database() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Failed to create the database connection.", e);
        }
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        } else if (instance.getConnection().isClosed()) {
            synchronized (Database.class) {
                if (instance.getConnection().isClosed()) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
