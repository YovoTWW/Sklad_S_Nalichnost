package com.example.sklad_s_nalichnost.models;

public class PayDesk {

    public static final PayDesk instance = new PayDesk();
    private double Balance;

    public double getBalance() {
        return Balance;
    }

    public void AddBalance(double addedAmount){
        this.Balance += addedAmount;
    }

    public void TakeBalance(double takenAmount)
    {
        if(this.Balance >= takenAmount)
        {
            this.Balance -= takenAmount;
        }
    }

    public void Initialize(){
        Balance = 10000;
    }
}
