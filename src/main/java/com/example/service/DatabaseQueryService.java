package com.example.service;

import com.example.config.Database;
import com.example.models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private <T> List<T> executeQuery(String sqlFilePath, ResultSetProcessor<T> processor) {
        List<T> results = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(new String(Files.readAllBytes(Paths.get(sqlFilePath))))) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                results.add(processor.process(rs));
            }

        } catch (SQLException | IOException e) {
            System.err.println("Failed to execute query: " + sqlFilePath);
            e.printStackTrace();
        }
        return results;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        return executeQuery("sql/find_max_projects_client.sql", rs ->
                new MaxProjectCountClient(rs.getString("NAME"), rs.getInt("PROJECT_COUNT")));
    }

    public List<ProjectPrice> getProjectPrices() {
        return executeQuery("sql/print_project_prices.sql", rs ->
                new ProjectPrice(rs.getString("NAME"), rs.getInt("DURATION"), rs.getInt("PRICE")));
    }

    public List<Worker> findYoungestEldestWorkers() {
        return executeQuery("sql/find_youngest_eldest_workers.sql", rs ->
                new Worker(rs.getString("TYPE"), rs.getString("NAME"), rs.getDate("BIRTHDAY")));
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        return executeQuery("sql/find_max_salary_worker.sql", rs ->
                new MaxSalaryWorker(rs.getString("NAME"), rs.getInt("SALARY")));
    }

    public List<LongestProject> findLongestProject() {
        return executeQuery("sql/find_longest_project.sql", rs ->
                new LongestProject(rs.getString("NAME"), rs.getInt("DURATION")));
    }

    @FunctionalInterface
    private interface ResultSetProcessor<T> {
        T process(ResultSet rs) throws SQLException;
    }
}
