package com.example.service;

import com.example.config.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get("sql/V2__populate_db.sql"))))) {

            statement.execute();
            System.out.println("Database populated successfully.");

        } catch (SQLException | IOException e) {
            System.err.println("Failed to populate the database.");
            e.printStackTrace();
        }
    }
}
