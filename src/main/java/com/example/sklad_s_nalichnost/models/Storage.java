package com.example.sklad_s_nalichnost.models;

import java.util.List;

public class Storage {
    private List<Stock> AvailableStock;

    public Storage(List<Stock> availableStock) {
        AvailableStock = availableStock;
    }
}
