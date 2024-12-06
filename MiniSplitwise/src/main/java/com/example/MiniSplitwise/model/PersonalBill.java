package com.example.MiniSplitwise.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.Entity;
import java.util.*;

@Data
@Builder
@Getter
@Setter
@Table(name = "personal_bills_repo")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="billid")
    private UUID billId;

    @Column(name="billname")
    private String billName;

    @Column(name="user_id")
    private UUID user_id;

    @Column(name="amount", nullable = false)
    @NotNull
    private Float amount;

    public UUID getUserId() {
        return user_id;
    }

    public void setUserId(UUID user_id) {
        this.user_id = user_id;
    }
    public UUID getBillId() {
        return this.billId;
    }

    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return this.billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}