package com.example.sklad_s_nalichnost.models;

public class Supplier {
    private String Name;

    public Supplier(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
