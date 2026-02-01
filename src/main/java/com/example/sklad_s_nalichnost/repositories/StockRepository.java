package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;
import com.example.sklad_s_nalichnost.models.Stock;

import java.sql.*;
import java.util.*;

public class StockRepository {

    public List<Stock> findAllByStorage(int storageId){
        List<Stock> stockList = new ArrayList<>();

        String sql = """
            SELECT stock_id, name, delivery_price, selling_price, available_quantity
            FROM Stock
            WHERE storage_id = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, storageId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    stockList.add(new Stock(
                            rs.getObject("stock_id", UUID.class),
                            rs.getString("name"),
                            rs.getDouble("delivery_price"),
                            rs.getDouble("selling_price"),
                            rs.getInt("available_quantity")
                    ));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    public int getQuantity(int stockId) {
        String sql = "SELECT available_quantity FROM Stock WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("available_quantity");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getDeliveryPrice(int stockId){
        String sql = "SELECT delivery_price FROM Stock WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("delivery_price");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSellingPrice(int stockId){
        String sql = "SELECT selling_price FROM Stock WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("selling_price");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateQuantity(UUID stockId, int newQty){
        String sql = "UPDATE Stock SET available_quantity = (available_quantity + ?) WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setObject(2, stockId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}