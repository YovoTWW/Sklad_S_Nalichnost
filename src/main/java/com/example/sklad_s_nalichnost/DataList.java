package com.example.sklad_s_nalichnost;

import com.example.sklad_s_nalichnost.controllers.ClientController;
import com.example.sklad_s_nalichnost.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.text.MessageFormat;

import java.util.UUID;

public class DataList {
    public static final DataList instance = new DataList();
    public ObservableList<Client> Clients;
    public ObservableList<Supplier> Suppliers;
    public ObservableList<Invoice> Invoices;
    public ObservableList<Stock> BuyableStock;

    public Client currentBuyer;
    public Supplier currentSeller;
    /*
    public void initialize(){
        Clients = FXCollections.observableArrayList();
        Suppliers = FXCollections.observableArrayList();
        Invoices = FXCollections.observableArrayList();
        BuyableStock = FXCollections.observableArrayList();

        //Clients.add(new Client("Misho"));
        //Clients.add(new Client("Ivan"));

        Suppliers.add(new Supplier("Fruits and More co."));

        BuyableStock.add(new Stock("Pears", 1.2, 2.0, 1000));
        BuyableStock.add(new Stock("Oranges", 0.8, 1.5, 1000));
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

    public void SellStock(UUID stockId,int Quantity){
        Stock stock = Storage.instance.getAvailableStock().stream().filter(s->s.getId().equals(stockId)).findFirst().orElse(null);
        if (stock == null) {
            throw new IllegalArgumentException("Stock with ID " + stockId + " not found.");
        }
        stock.setAvailableQuantity(stock.getAvailableQuantity() - Quantity);
        PayDesk.instance.AddBalance(stock.getSellingPrice()*Quantity);
        DataList.instance.Invoices.add(new Invoice(MessageFormat.format("Sold {0} {1} with stock Id : {2} to {3} with Client Id: {4} for {5}$ per item.",
                Quantity,stock.getName(),stockId, DataList.instance.currentBuyer.getName(),DataList.instance.currentBuyer.getId(),stock.getSellingPrice())));

        if(stock.getAvailableQuantity()==0)
        {
            Storage.instance.removeStock(stock);
        }
    }

    public void BuyStock(UUID stockId,int Quantity)
    {
        Stock stock = BuyableStock.stream().filter(s->s.getId().equals(stockId)).findFirst().orElse(null);
        if (stock == null) {
            throw new IllegalArgumentException("Stock with ID " + stockId + " not found.");
        }
        stock.setAvailableQuantity(stock.getAvailableQuantity() - Quantity);
        if(PayDesk.instance.getBalance()>= stock.getDeliveryPrice()*Quantity)
        {
            PayDesk.instance.TakeBalance(stock.getDeliveryPrice() * Quantity);
            DataList.instance.Invoices.add(new Invoice(MessageFormat.format("Bought {0} {1} with stock Id : {2} from {3} with Supplier Id: {4} for {5}$ per item.",
                    Quantity, stock.getName(), stockId, DataList.instance.currentSeller.getName(), DataList.instance.currentSeller.getId(), stock.getDeliveryPrice())));


            Stock StorageStock = Storage.instance.getAvailableStock().stream().filter(s->s.getId().equals(stockId)).findFirst().orElse(null);
            if (StorageStock == null) {
                Storage.instance.getAvailableStock().add(new Stock(stock.getId(),stock.getName(),stock.getDeliveryPrice(),stock.getSellingPrice(),Quantity));
            }
            else{
                StorageStock.setAvailableQuantity(StorageStock.getAvailableQuantity() + Quantity);
            }
        }
        else{
            throw new IllegalArgumentException("Not enough Balanace in Paydesk for Transaction.");
        }
    }
*/
}
