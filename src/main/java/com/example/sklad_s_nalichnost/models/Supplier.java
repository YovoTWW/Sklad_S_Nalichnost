package com.example.sklad_s_nalichnost.models;

import java.util.UUID;

public class Supplier {
    private UUID Id;
    private String Name;

    public Supplier(String name) {
        Id = UUID.randomUUID();
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
}
