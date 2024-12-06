package com.example.MiniSplitwise.dto;
import com.example.MiniSplitwise.model.Bill;
import com.example.MiniSplitwise.model.BillMapping;
import com.example.MiniSplitwise.Helper.BillItem;
import lombok.Data;

import java.util.*;

public class BillDTO{
    private String billName;
    private String creditorEmail;
    private Float amount;
    private List<BillItem> debitors;

    public Bill getBillFromDTO(UUID creditorId){
        Bill bill = new Bill();
        bill.setBillName(billName);
        bill.setCreditorId(creditorId);
        bill.setAmount(amount);
        System.out.println(amount);
        return bill;
    }
    public List<BillMapping> getBillMappingFromDTO(UUID bId, List<UUID> debitorIds){
        List<BillMapping> maps = new ArrayList<>();
        for(int i=0; i<debitorIds.size(); i++){
            BillMapping billMapping = new BillMapping();
            billMapping.setUserId(debitorIds.get(i));
            billMapping.setBillId(bId);
            Float amount = debitors.get(i).getAmount();
            billMapping.setDueAmount(amount);
            maps.add(billMapping);
        }
        return maps;
    }

    public Float getAmount() {
        return amount;
    }

    public List<BillItem> getDebitors(){
        return debitors;
    }

    public String getBillName() {
        return billName;
    }

    public String getCreditorEmail() {
        return creditorEmail;
    }
}