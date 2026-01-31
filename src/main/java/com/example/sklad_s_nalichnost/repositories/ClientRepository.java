package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;
import com.example.sklad_s_nalichnost.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.UUID;

public class ClientRepository {

    public ObservableList<Client> getAll() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        String sql = "SELECT * FROM \"Clients\"";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clients.add(new Client(
                        UUID.fromString(rs.getString("Id")),
                        rs.getString("Name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public void add(Client client) {
        String sql = "INSERT INTO Clients (Id, Name) VALUES (?, ?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, client.getId().toString());
            ps.setString(2, client.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}