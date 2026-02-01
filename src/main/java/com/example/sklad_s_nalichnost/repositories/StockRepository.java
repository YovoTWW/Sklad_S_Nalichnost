package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.DataList;
import com.example.sklad_s_nalichnost.Database;
import com.example.sklad_s_nalichnost.models.Client;
import com.example.sklad_s_nalichnost.models.Stock;
import com.example.sklad_s_nalichnost.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

public class StockRepository {
    public ObservableList<Stock> getAll() {
        ObservableList<Stock> stock = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Stock";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                stock.add(new Stock(
                        UUID.fromString(rs.getString("stock_id")),
                        rs.getString("name"),
                        rs.getDouble("delivery_price"),
                        rs.getDouble("selling_price"),
                        rs.getInt("available_quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stock;
    }

    public List<Stock> findAllByStorage(int storageId) {
        List<Stock> stockList = new ArrayList<>();

        String sql = """
            SELECT stock_id, name, delivery_price, selling_price, available_quantity
            FROM Stock
            WHERE storage_id = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, StorageUUID.StorageId.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    stockList.add(new Stock(
                            UUID.fromString(rs.getString("stock_id")),
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

    public List<Stock> findAllBySupplier(Supplier supplier) {
        List<Stock> stockList = new ArrayList<>();

        String sql = """
            SELECT Stock.stock_id, Stock."name", Stock.delivery_price, Stock.selling_price, SupplierStock.quantity FROM Stock
            JOIN SupplierStock ON Stock.stock_id = SupplierStock.stock_id
            JOIN Supplier ON SupplierStock.supplier_id = Supplier.supplier_id
            WHERE Supplier.supplier_id = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, supplier.getId().toString());

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        stockList.add(new Stock(
                                UUID.fromString(rs.getString("stock_id")),
                                rs.getString("name"),
                                rs.getDouble("delivery_price"),
                                rs.getDouble("selling_price"),
                                rs.getInt("quantity")
                        ));
                    }
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    public int getQuantity(UUID stockId) {
        String sql = "SELECT available_quantity FROM Stock WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, stockId.toString());

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

    public int getDeliveryPrice(UUID stockId) {
        String sql = "SELECT delivery_price FROM Stock WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, stockId.toString());

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

    public int getSellingPrice(UUID stockId) {
        String sql = "SELECT selling_price FROM Stock WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, stockId.toString());

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
        String sql = "UPDATE Stock SET available_quantity = ? WHERE stock_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setString(2, stockId.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}