package com.example.sklad_s_nalichnost.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public  class Storage {

    public static final Storage instance = new Storage();
    private ObservableList<Stock> availableStock;

    public void addStock(Stock stock)
    {
        availableStock.add(stock);
    }

    public ObservableList<Stock> getAvailableStock()
    {
        return availableStock;
    }

    public void Initialize(){
        // Create storage and sample data
        availableStock = FXCollections.observableArrayList();
        availableStock.add(new Stock("Apples", 1.2, 2.0, 100));
        availableStock.add(new Stock("Bananas", 0.8, 1.5, 80));
    }
}
