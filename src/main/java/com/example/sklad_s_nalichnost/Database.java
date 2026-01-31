package com.example.sklad_s_nalichnost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:sqlserver://localhost;databaseName=SkladDb;encrypt=true;trustServerCertificate=true";

    private static final String USER = "";      // or your SQL user
    private static final String PASSWORD = "";  // or your password

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Oracle JDBC Driver not found", e);
        }

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
    /*
CREATE TABLE "Storage" (
    storage_id INTEGER NOT NULL,

    PRIMARY KEY (storage_id)
);

CREATE TABLE "Client" (
    client_id INTEGER NOT NULL,
    "name" VARCHAR2(100) NOT NULL,

    PRIMARY KEY (client_id)
);

CREATE TABLE Supplier (
    supplier_id INTEGER NOT NULL,
    "name" VARCHAR2(100) NOT NULL,

    PRIMARY KEY (supplier_id)
);

CREATE TABLE Stock (
    stock_id INTEGER NOT NULL,
    "name" VARCHAR2(100) NOT NULL,
    delivery_price DECIMAL(10,2),
    selling_price DECIMAL(10,2),
    available_quantity INTEGER,
    storage_id INTEGER NOT NULL,

    PRIMARY KEY (stock_id),

    CONSTRAINT FK_Stock_Storage
        FOREIGN KEY (storage_id) REFERENCES "Storage"(storage_id)
);

CREATE TABLE Invoice (
    invoice_id INTEGER NOT NULL,
    "text" VARCHAR2(200) NOT NULL,
    storage_id INTEGER NOT NULL,
    supplier_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    stock_id INTEGER NOT NULL,

    PRIMARY KEY (invoice_id),

    CONSTRAINT FK_Invoice_Storage
        FOREIGN KEY (storage_id) REFERENCES "Storage"(storage_id),

    CONSTRAINT FK_Invoice_Supplier
        FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id),

    CONSTRAINT FK_Invoice_Client
        FOREIGN KEY (client_id) REFERENCES "Client"(client_id),

    CONSTRAINT FK_Invoice_Stock
        FOREIGN KEY (stock_id) REFERENCES Stock(stock_id)
);


CREATE TABLE SupplierStock (
    supplier_id INTEGER NOT NULL,
    stock_id INTEGER NOT NULL,
    Quantity INTEGER NOT NULL,

    PRIMARY KEY (supplier_id, stock_id),

    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id),
    FOREIGN KEY (stock_id) REFERENCES Stock(stock_id)
);

CREATE TABLE Paydesk (
    storage_id INTEGER NOT NULL,
    balance DECIMAL(10,2),

    PRIMARY KEY (storage_id),

    CONSTRAINT FK_Paydesk_Storage
        FOREIGN KEY (storage_id) REFERENCES "Storage"(storage_id)
);
     */