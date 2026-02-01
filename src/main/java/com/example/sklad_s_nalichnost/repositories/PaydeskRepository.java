package com.example.sklad_s_nalichnost.repositories;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;

public class PaydeskRepository {

    public double getBalance(int storageId){
        String sql = "SELECT balance FROM Paydesk WHERE storage_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, storageId);

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

    public void updateBalance(int storageId, double newBalance){
        String sql = "UPDATE Paydesk SET balance = ? WHERE storage_id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, storageId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}