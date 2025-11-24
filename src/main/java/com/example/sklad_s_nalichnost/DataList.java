package com.example.sklad_s_nalichnost;

import com.example.sklad_s_nalichnost.controllers.ClientController;
import com.example.sklad_s_nalichnost.models.Client;
import com.example.sklad_s_nalichnost.models.Supplier;
import com.example.sklad_s_nalichnost.models.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataList {
    public static final DataList instance = new DataList();
    public ObservableList<Client> Clients;
    public ObservableList<Supplier> Suppliers;
    public ObservableList<Invoice> Invoices;

    public Client currentBuyer;
    public Supplier currentSeller;

    public void initialize(){
        Clients = FXCollections.observableArrayList();
        Suppliers = FXCollections.observableArrayList();
        Invoices = FXCollections.observableArrayList();

        Clients.add(new Client("Misho"));
        Clients.add(new Client("Ivan"));

        Suppliers.add(new Supplier("Fruits and More co."));
    }

    public void setCurrentBuyer(Client client){
        currentBuyer = client;
    }

    public void setCurrentSeller(Supplier supplier)
    {
        currentSeller = supplier;
    }

    public void resetCurrents(){
        currentBuyer = null;
        currentSeller = null;
    }
}
