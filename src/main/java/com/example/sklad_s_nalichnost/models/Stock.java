package com.example.sklad_s_nalichnost.models;

public class Stock {
    private String Name;
    private double DeliveryPrice;
    private double SellingPrice;
    private int AvailableQuantity;

    public Stock(String name, double deliveryPrice, double sellingPrice, int availableQuantity) {
        Name = name;
        DeliveryPrice = deliveryPrice;
        SellingPrice = sellingPrice;
        AvailableQuantity = availableQuantity;
    }

    public int getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public double getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public double getDeliveryPrice() {
        return DeliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        DeliveryPrice = deliveryPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
