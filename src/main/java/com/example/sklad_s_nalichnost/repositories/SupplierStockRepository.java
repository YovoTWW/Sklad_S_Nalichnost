package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;
import java.util.UUID;

public class SupplierStockRepository {

    public void insert(UUID supplierId, UUID stockId, int quantity)
            throws SQLException {

        String sql = """
            INSERT INTO SupplierStock (SupplierId, StockId, Quantity)
            VALUES (?, ?, ?)
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, supplierId);
            ps.setObject(2, stockId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }
}