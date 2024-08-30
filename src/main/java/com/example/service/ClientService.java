package com.example.service;

import com.example.config.Database;
import com.example.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public long create(String name) throws SQLException {
        if (name == null || name.length() < 3 || name.length() > 255) {
            throw new IllegalArgumentException("Client name must be between 3 and 255 characters.");
        }

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new SQLException("Failed to retrieve client ID.");
            }
        }
    }

    public String getById(long id) throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT name FROM client WHERE id = ?")) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            } else {
                throw new SQLException("Client with ID " + id + " not found.");
            }
        }
    }

    public void setName(long id, String name) throws SQLException {
        if (name == null || name.length() < 3 || name.length() > 255) {
            throw new IllegalArgumentException("Client name must be between 3 and 255 characters.");
        }

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?")) {

            stmt.setString(1, name);
            stmt.setLong(2, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Client with ID " + id + " not found.");
            }
        }
    }

    public void deleteById(long id) throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM client WHERE id = ?")) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Client with ID " + id + " not found.");
            }
        }
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM client")) {

            while (rs.next()) {
                clients.add(new Client(rs.getLong("id"), rs.getString("name")));
            }
        }
        return clients;
    }
}
