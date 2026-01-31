package com.example.sklad_s_nalichnost.tests;

public class Tester {
    public static void testAll() {
        try {
            DatabaseConnectionTest.test(
                    "jdbc:oracle:thin:@//localhost:1521/XEPDB1",
                    "QX",
                    "bunny77",
                    "SELECT * FROM \"Client\""
            );
        } catch (Exception e) {
            System.out.println("Connection failed: ");
            e.printStackTrace();
            return;
        }

        System.out.println("No errors found.");
    }
}