package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;
import com.example.sklad_s_nalichnost.models.Client;
import com.example.sklad_s_nalichnost.models.Supplier;

import java.sql.*;
import java.util.*;

public class SupplierRepository {

    public List<Supplier> findAll() {
        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM Supplier";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                suppliers.add(new Supplier(
                        UUID.fromString(rs.getString("supplier_id")),
                        rs.getString("name")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public void add(Supplier supplier) {
        String sql = "INSERT INTO Supplier VALUES (?, ?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, supplier.getId().toString());
            ps.setString(2, supplier.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}