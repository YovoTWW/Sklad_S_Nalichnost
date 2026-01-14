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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQL Server JDBC Driver not found", e);
        }

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
    /*
    use SkladDb;

create table Storage(
Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY
);

create table Client(
Id UNIQUEIDENTIFIER NOT NULL DEFAULT NEWSEQUENTIALID() PRIMARY KEY,
Name NVARCHAR(100) NOT NULL
);

create table Supplier(
Id UNIQUEIDENTIFIER NOT NULL DEFAULT NEWSEQUENTIALID() PRIMARY KEY,
Name NVARCHAR(100) NOT NULL
);

create table Stock(
Id UNIQUEIDENTIFIER NOT NULL DEFAULT NEWSEQUENTIALID() PRIMARY KEY,
Name NVARCHAR(100) NOT NULL,
DeliveryPrice DECIMAL(10,2),
SellingPrice DECIMAL(10,2),
AvailableQuantity INT,
StorageId INT NOT NULL,
CONSTRAINT FK_Stock_Storage
FOREIGN KEY (StorageId) REFERENCES Storage(Id)
);

create table Invoice(
Id UNIQUEIDENTIFIER NOT NULL DEFAULT NEWSEQUENTIALID() PRIMARY KEY,
Text NVARCHAR(200) NOT NULL,
StorageId INT NOT NULL,
CONSTRAINT FK_Invoice_Storage
FOREIGN KEY (StorageId) REFERENCES Storage(Id),
SupplierId UNIQUEIDENTIFIER NOT NULL,
CONSTRAINT FK_Invoice_Supplier
FOREIGN KEY (SupplierId) REFERENCES Supplier(Id),
ClientId UNIQUEIDENTIFIER NOT NULL,
CONSTRAINT FK_Invoice_Client
FOREIGN KEY (ClientId) REFERENCES Client(Id),
StockId UNIQUEIDENTIFIER NOT NULL,
CONSTRAINT FK_Invoice_Stock
FOREIGN KEY (StockId) REFERENCES Stock(Id),
);


CREATE TABLE SupplierStock (
SupplierId UNIQUEIDENTIFIER NOT NULL,
StockId UNIQUEIDENTIFIER NOT NULL,
Quantity INT NOT NULL,

PRIMARY KEY (SupplierId, StockId),

FOREIGN KEY (SupplierId) REFERENCES Supplier(Id),
FOREIGN KEY (StockId) REFERENCES Stock(Id)
);

create table Paydesk(
StorageId INT NOT NULL PRIMARY KEY,
Balance DECIMAL(10,2)
CONSTRAINT FK_Paydesk_Storage
FOREIGN KEY (StorageId) REFERENCES Storage(Id)
);
     */