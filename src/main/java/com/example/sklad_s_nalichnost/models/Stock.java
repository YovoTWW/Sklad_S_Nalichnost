package com.example.sklad_s_nalichnost.models;

import java.util.UUID;

public class Stock {
    private UUID id;
    private String name;
    private double deliveryPrice;
    private double sellingPrice;
    private int availableQuantity;

    public Stock(String name, double deliveryPrice, double sellingPrice, int availableQuantity) {
        id = UUID.randomUUID();
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.sellingPrice = sellingPrice;
        this.availableQuantity = availableQuantity;
    }

    public Stock(UUID id,String name, double deliveryPrice, double sellingPrice, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.sellingPrice = sellingPrice;
        this.availableQuantity = availableQuantity;
    }

    public UUID getId() {
        return id;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
