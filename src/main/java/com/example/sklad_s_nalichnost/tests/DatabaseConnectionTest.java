package com.example.sklad_s_nalichnost.tests;

import com.example.sklad_s_nalichnost.Database;

import java.sql.*;

public class DatabaseConnectionTest {
    public static void test(String url, String user, String password, String sqlQuery) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlQuery);

        int columns = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getString(i) + " | ");
            }
            System.out.println();
        }

        con.close();
        st.close();
        rs.close();
    }
}
