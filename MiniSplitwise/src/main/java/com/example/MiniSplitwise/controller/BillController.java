package com.example.MiniSplitwise.controller;

import com.example.MiniSplitwise.service.BillService;
import com.example.MiniSplitwise.dto.BillDTO;
import com.example.MiniSplitwise.model.Bill;
import com.example.MiniSplitwise.Helper.BillItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.io.*;

@RestController
@RequestMapping("/bills")
public class BillController {
    private static final Logger logger = LogManager.getLogger(BillController.class);
    private final BillService billService;
    BillController(BillService billService){
        this.billService = billService;
    }

    @PostMapping("/addBills")
    public ResponseEntity<Map<UUID, String>> addBills (@RequestBody BillDTO billDTO) {
        logger.info("Adding bill");
        // Assuming yourService.addDebitors returns a Map<UUID, String>
        System.out.println(billDTO.getAmount());
        Map<UUID, String> insertedData = new HashMap<>();
        UUID result = billService.addBills(billDTO);
        insertedData.put(result, "Added to Bills");
        List<BillItem> bills = billDTO.getDebitors();
        for(int i=0; i<bills.size(); i++){
            System.out.println(bills.get(i).getEmail());
            System.out.println(bills.get(i).getAmount());
        }
        List<UUID> maps = billService.addMappings(billDTO, result);
        for(int i=0; i<maps.size(); i++){
            insertedData.put(maps.get(i), "Added to mapping");
        }

        if (insertedData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(insertedData);
    }

    @GetMapping("/getBillById/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable UUID id){
        logger.info("Finding bill by Id");
        System.out.println(id);
        Optional<Bill> optionalBill = billService.getBillById(id);
        if(optionalBill.isPresent()){
            Bill bill = optionalBill.get();
            return ResponseEntity.ok(bill);
        }
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateStatus/{id}")
    public void updateStatusById(@PathVariable UUID id){
        logger.info("Updating bill by id");
        billService.updateStatusToTrue(id);
    }

    @DeleteMapping("/deleteBillsById/{id}")
    public void deleteBillsById(@PathVariable UUID id){
        logger.info("Deleting shared bills by bill id");
        billService.deleteBillsById(id);
    }
}
