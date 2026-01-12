package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;
import com.example.sklad_s_nalichnost.models.Stock;

import java.sql.*;
import java.util.*;

public class StockRepository {

    public List<Stock> findAllByStorage(int storageId) throws SQLException {
        List<Stock> stockList = new ArrayList<>();

        String sql = """
            SELECT Id, Name, DeliveryPrice, SellingPrice, AvailableQuantity
            FROM Stock
            WHERE StorageId = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, storageId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    stockList.add(new Stock(
                            rs.getObject("Id", UUID.class),
                            rs.getString("Name"),
                            rs.getDouble("DeliveryPrice"),
                            rs.getDouble("SellingPrice"),
                            rs.getInt("AvailableQuantity")
                    ));
                }
            }
        }
        return stockList;
    }

    public void updateQuantity(UUID stockId, int newQty) throws SQLException {
        String sql = "UPDATE Stock SET AvailableQuantity = ? WHERE Id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setObject(2, stockId);
            ps.executeUpdate();
        }
    }
}