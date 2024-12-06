package com.example.MiniSplitwise.Helper;
import java.util.UUID;

public class BillItem {
    private String email;
    private Float amount;

    public BillItem(String email, Float amount) {
        this.email = email;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
