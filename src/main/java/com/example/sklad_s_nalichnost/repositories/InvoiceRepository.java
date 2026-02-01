package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;
import java.util.UUID;

public class InvoiceRepository {

    public void insert(
            String text,
            UUID supplierId,
            UUID clientId
    ) throws SQLException {

        String sql = "INSERT INTO invoice (\"text\", storage_id, supplier_id, client_id) VALUES (?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, text);
            ps.setString(2, StorageUUID.StorageId.toString());
            ps.setString(3, supplierId.toString());
            ps.setString(4, clientId.toString());
            ps.executeUpdate();
        }
    }
}