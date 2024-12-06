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
@Table(name = "Bills_repo")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="billid")
    private UUID billId;

    @Column(name="billname")
    private String billName;

    @Column(name="creditorid")
    private UUID creditorId;

    @Column(name="amount", nullable = false)
    @NotNull
    private Float amount;

    @Column(name="completed")
    private Boolean completed = false;

    public UUID getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(UUID creditorId) {
        this.creditorId = creditorId;
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

    public Boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}