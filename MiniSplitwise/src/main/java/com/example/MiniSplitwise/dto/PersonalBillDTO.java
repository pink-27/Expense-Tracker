package com.example.MiniSplitwise.dto;
import com.example.MiniSplitwise.model.PersonalBill;
// import com.example.MiniSplitwise.model.BillMapping;
// import com.example.MiniSplitwise.Helper.BillItem;
import lombok.Data;

import java.util.*;

public class PersonalBillDTO{
    private String billName;
    private String email;
    private Float amount;

    public PersonalBill getBillFromDTO(UUID user_id){
        PersonalBill bill = new PersonalBill();
        System.out.println("Okay see");
        bill.setBillName(billName);
        bill.setUserId(user_id);
        bill.setAmount(amount);
        return bill;
    }

    public Float getAmount() {
        return amount;
    }


    public String getBillName() {
        return billName;
    }

    public String getEmail() {
        return email;
    }
}