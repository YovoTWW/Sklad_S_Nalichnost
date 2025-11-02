package com.example.sklad_s_nalichnost.models;

public class PayDesk {
    private double Balance;

    public PayDesk(double balance) {
        Balance = balance;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }
}
