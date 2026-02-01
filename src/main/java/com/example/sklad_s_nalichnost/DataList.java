package com.example.sklad_s_nalichnost;

import com.example.sklad_s_nalichnost.controllers.ClientController;
import com.example.sklad_s_nalichnost.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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

    public void initialize(){
        Clients = FXCollections.observableArrayList();
        Suppliers = FXCollections.observableArrayList();
        Invoices = FXCollections.observableArrayList();
        //BuyableStock = FXCollections.observableArrayList();

        Clients.add(new Client("Misho"));
        Clients.add(new Client("Ivan"));

        Supplier s1 = new Supplier("Fruits and More co.");
        s1.addSupplierStock(new Stock("Pears", 1.2, 2.0, 1000));
        s1.addSupplierStock(new Stock("Oranges", 0.8, 1.5, 1000));
        Suppliers.add(s1);

        Supplier s2 = new Supplier("Best Furniture");
        s2.addSupplierStock(new Stock("Standard Chair", 20, 25, 80));
        s2.addSupplierStock(new Stock("Wooden Table", 40, 50, 50));
        s2.addSupplierStock(new Stock("Wooden Wardrobe", 200, 230, 44));
        s2.addSupplierStock(new Stock("Glass Table", 50, 65, 48));
        Suppliers.add(s2);

        Supplier s3 = new Supplier("Sports Wear");
        s3.addSupplierStock(new Stock("Green Rashguard", 25, 29, 890));
        s3.addSupplierStock(new Stock("Jogging Shoes", 44, 49, 410));
        s3.addSupplierStock(new Stock("Swiming Glasses", 15, 17, 600));
        Suppliers.add(s3);

    }

    public void setCurrentBuyer(Client client){
        currentBuyer = client;
    }

    public void setCurrentSeller(Supplier supplier)
    {
        currentSeller = supplier;
        BuyableStock = supplier.getSupplierStock();
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
        //stock.setAvailableQuantity(stock.getAvailableQuantity() - Quantity);
        if(PayDesk.instance.getBalance() >= stock.getDeliveryPrice()*Quantity)
        {
            stock.setAvailableQuantity(stock.getAvailableQuantity() - Quantity);
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

            showInfo("You selected " + Quantity + " of " + stock.getName());
        }
        else{
            showError("Not enough Balanace in Paydesk for Transaction.");
            throw new IllegalArgumentException("Not enough Balanace in Paydesk for Transaction.");
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
