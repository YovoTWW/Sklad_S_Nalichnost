package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;
import java.util.UUID;

public class InvoiceRepository {

    public void insert(
            String text,
            int storageId,
            UUID supplierId,
            UUID clientId
    ) throws SQLException {

        String sql = """
            INSERT INTO Invoice (Text, StorageId, SupplierId, ClientId)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, text);
            ps.setInt(2, storageId);
            ps.setObject(3, supplierId);
            ps.setObject(4, clientId);
            ps.executeUpdate();
        }
    }
}