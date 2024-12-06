package com.example.MiniSplitwise.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Entity;
import java.util.*;

@Data
@Builder
@Getter
@Setter
@Table(name = "BillUserMapping")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="mappingid")
    private UUID mappingId;

    @Column(name="user_id")
    private UUID userId;

    @Column(name="billid")
    private UUID billId;

    @Column(name="dueamount")
    private Float dueAmount;

    public UUID getMappingId() {
        return mappingId;
    }

    public void setMappingId(UUID mappingId) {
        this.mappingId = mappingId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBillId() {
        return billId;
    }

    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    public Float getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Float dueAmount) {
        this.dueAmount = dueAmount;
    }
}