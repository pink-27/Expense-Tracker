package com.example.MiniSplitwise.service;

import com.example.MiniSplitwise.dto.PersonalBillDTO;
import com.example.MiniSplitwise.model.PersonalBill;
import com.example.MiniSplitwise.repository.PersonalBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import  java.util.*;

@Service
public class PersonalBillService {
    private static final Logger logger = LogManager.getLogger(PersonalBillService.class);
    private final PersonalBillRepository personalBillRepository;

    PersonalBillService(PersonalBillRepository personalBillRepository){
        this.personalBillRepository = personalBillRepository;
    }

    public UUID addBills(PersonalBillDTO personalBillDTO, UUID user_id) {
        logger.info("Adding personal bills");
        System.out.println(personalBillDTO.getBillName()+" "+personalBillDTO.getAmount());
        PersonalBill bill = personalBillDTO.getBillFromDTO(user_id);
        return personalBillRepository.save(bill).getBillId();
    }

    public Optional<PersonalBill> getBillById(UUID bill_id){
        logger.info("Finding bill by ID");
        return personalBillRepository.findById(bill_id);
    }

    public Optional<List<PersonalBill>> getBillByUserId(UUID user_id){
        logger.info("Finding bills by user ID");
        Optional<List<PersonalBill>> bill = personalBillRepository.findByUserId(user_id);
        // System.out.println(bill.getBillId());
        return bill;
    }

    public void deleteByBillId(UUID bill_id) {
        personalBillRepository.deleteById(bill_id);
    }

    public void deleteByUserId(UUID userId) {
        personalBillRepository.deleteByUserId(userId);
    }
    @Transactional
    public void updateBillById(PersonalBillDTO personalBillDTO, UUID billId) {
        logger.info("Updating status of bill");
        Optional<PersonalBill> billEntity = personalBillRepository.findById(billId);
        billEntity.ifPresent(entity -> {
            entity.setBillName(personalBillDTO.getBillName());
            entity.setAmount(personalBillDTO.getAmount());
            personalBillRepository.save(entity);
        });
    }
}