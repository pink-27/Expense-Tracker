package com.example.MiniSplitwise.repository;

import com.example.MiniSplitwise.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface BillRepository extends JpaRepository<Bill, UUID> {
    // You can add custom query methods if needed

}

