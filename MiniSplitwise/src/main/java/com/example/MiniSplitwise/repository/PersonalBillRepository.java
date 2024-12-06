package com.example.MiniSplitwise.repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.MiniSplitwise.model.PersonalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface PersonalBillRepository extends JpaRepository<PersonalBill, UUID> {
    // You can add custom query methods if needed
    @Query("SELECT p from PersonalBill p WHERE p.user_id = ?1")
    Optional<List<PersonalBill>> findByUserId(UUID user_id);

    @Query("DELETE FROM PersonalBill pb WHERE pb.user_id = ?1")
    @Transactional
    void deleteByUserId(UUID user_id);
}

