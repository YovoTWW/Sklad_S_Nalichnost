package com.example.sklad_s_nalichnost.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.UUID;

public class Supplier {
    private UUID Id;
    private String Name;
    private ObservableList<Stock> supplierStock;

    public Supplier(String name)
    {
        Id = UUID.randomUUID();
        Name = name;
        supplierStock = FXCollections.observableArrayList();
    }
    public Supplier(UUID id,String name) {
        //Id = UUID.randomUUID();
        Id = id;
        Name = name;
    }

    public UUID getId(){
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ObservableList<Stock> getSupplierStock() {
        return supplierStock;
    }

    public void addSupplierStock(Stock stock) {
        supplierStock.add(stock);
    }
}
