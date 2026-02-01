package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;
import java.util.UUID;

public class SupplierStockRepository {
    public int getSupplierStockQuantity(UUID supplierId, UUID stockId) throws SQLException {

        String sql = """
            SELECT quantity
            FROM SupplierStock
            WHERE supplier_id = ? AND stock_id = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, supplierId.toString());
            ps.setString(2, stockId.toString());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("quantity");
            }
        }
    }

    public void insert(UUID supplierId, UUID stockId, int quantity) throws SQLException {
        String sql = "INSERT INTO SupplierStock VALUES (?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, supplierId);
            ps.setObject(2, stockId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }

    public void updateSupplierStock(UUID supplierId, UUID stockId, int newQuantity) throws SQLException {
        String sql = """
            UPDATE SupplierStock
            SET quantity = ?
            WHERE supplier_id = ? AND stock_id = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newQuantity);
            ps.setString(2, supplierId.toString());
            ps.setString(3, stockId.toString());
            ps.executeUpdate();
        }
    }
}