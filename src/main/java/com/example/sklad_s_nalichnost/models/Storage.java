package com.example.sklad_s_nalichnost.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Storage {
    private ObservableList<Stock> availableStock;

    public Storage() {
        availableStock = FXCollections.observableArrayList();
    }

    public void addStock(Stock stock)
    {
        availableStock.add(stock);
    }

    public ObservableList<Stock> getAvailableStock()
    {
        return availableStock;
    }
}
