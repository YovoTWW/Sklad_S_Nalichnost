package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;

public class PaydeskRepository {
    public double getBalance() {
        String sql = "SELECT balance FROM Paydesk WHERE storage_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, StorageUUID.StorageId.toString());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("balance");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateBalance(double newBalance){
        String sql = "UPDATE Paydesk SET balance = ? WHERE storage_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, StorageUUID.StorageId.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}