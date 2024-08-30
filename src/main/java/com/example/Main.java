package com.example;

import com.example.models.Client;
import com.example.service.ClientService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        try {
            long clientId = clientService.create("John Doe");
            System.out.println("Created client with ID: " + clientId);

            String name = clientService.getById(clientId);
            System.out.println("Client name: " + name);

            clientService.setName(clientId, "Jane Doe");
            System.out.println("Updated client name to: " + clientService.getById(clientId));

            List<Client> clients = clientService.listAll();
            clients.forEach(client -> System.out.println(client.getId() + ": " + client.getName()));

            clientService.deleteById(clientId);
            System.out.println("Deleted client with ID: " + clientId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
