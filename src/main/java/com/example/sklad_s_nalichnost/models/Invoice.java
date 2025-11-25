package com.example.sklad_s_nalichnost.models;

import java.util.UUID;

public class Invoice {
    public UUID Id;
    public String InvoiceText;

    public Invoice(String invoiceText) {
        Id = UUID.randomUUID();
        InvoiceText = invoiceText;
    }

    public UUID getId() {
        return Id;
    }

    public String getInvoiceText() {
        return InvoiceText;
    }

    public void setInvoiceText(String invoiceText) {
        InvoiceText = invoiceText;
    }
}


