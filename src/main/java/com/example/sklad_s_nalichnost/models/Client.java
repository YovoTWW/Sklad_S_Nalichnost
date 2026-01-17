package com.example.sklad_s_nalichnost.models;

import java.util.UUID;

public class Client {
    private UUID Id;
    private String Name;

    public Client(String name)
    {
        Id = UUID.randomUUID();
        Name = name;
    }
    public Client(UUID id,String name) {
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
}
