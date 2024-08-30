package com.example.service;

import com.example.config.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get("sql/V1__init_db.sql"))))) {

            statement.execute();
            System.out.println("Database initialized successfully.");

        } catch (SQLException | IOException e) {
            System.err.println("Failed to initialize the database.");
            e.printStackTrace();
        }
    }
}
