package com.example.MiniSplitwise.repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.MiniSplitwise.model.BillMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.*;

public interface BillMappingRepository extends JpaRepository<BillMapping, UUID> {
    // You can add custom query methods if needed
    @Modifying
    @Query("DELETE FROM BillMapping bm WHERE bm.billId = ?1")
    @Transactional
    void deleteByBillId(UUID billId);
}

