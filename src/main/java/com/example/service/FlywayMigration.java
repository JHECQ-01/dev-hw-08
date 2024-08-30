package com.example.service;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/database", "user", "password")
                .load();
        flyway.migrate();
        System.out.println("Migrations applied successfully.");
    }
}
