package com.example.MiniSplitwise.controller;

import com.example.MiniSplitwise.service.PersonalBillService;
import com.example.MiniSplitwise.service.UserService;
import com.example.MiniSplitwise.dto.PersonalBillDTO;
import com.example.MiniSplitwise.model.PersonalBill;
import com.example.MiniSplitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.io.*;

@RestController
@RequestMapping("/personalBills")
@CrossOrigin(origins="*")
public class PersonalBillController {
    private static final Logger logger = LogManager.getLogger(PersonalBillController.class);
    private final PersonalBillService personalBillService;
    private final UserService userService;

    PersonalBillController(PersonalBillService personalBillService, UserService userService){
        this.personalBillService = personalBillService;
        this.userService = userService;
    }

    @PostMapping("/addBills")
    public ResponseEntity<UUID> addBills (@RequestBody PersonalBillDTO personalBillDTO) {
        User user = userService.findUserByEmail(personalBillDTO.getEmail());
        logger.info("Adding personal bill");
        UUID result = personalBillService.addBills(personalBillDTO, user.getUserId());       
        if (result == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getBillById/{id}")
    public ResponseEntity<PersonalBill> getBillById(@PathVariable UUID id){
        logger.info("Finding bill by Id");
        Optional<PersonalBill> optionalBill = personalBillService.getBillById(id);
        if(optionalBill.isPresent()){
            PersonalBill bill = optionalBill.get();
            return ResponseEntity.ok(bill);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("getBillByUser/{email}")
    public ResponseEntity<List<PersonalBill>> getBillByUserId(@PathVariable String email){
        logger.info("Finding Bills by User");
        System.out.println(email);
        User user = userService.findUserByEmail(email);
        Optional<List<PersonalBill>> optionalBill = personalBillService.getBillByUserId(user.getUserId());
        if(optionalBill!=null){
            List<PersonalBill> bill = optionalBill.get();
            return ResponseEntity.ok(bill);
        }
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("updateBillById/{id}")
    public ResponseEntity<String> updateBillById(@RequestBody PersonalBillDTO personalBillDTO, @PathVariable UUID id){
        logger.info("Updating bill by id");
        System.out.println(id);
        personalBillService.updateBillById(personalBillDTO, id);
        return ResponseEntity.ok("Personal Bill Changed Successfully");
    }

    @GetMapping("/getTotalExpense/{email}")
    public ResponseEntity<Float> getTotalExpense(@PathVariable String email) {
        logger.info("Getting total expense for an id");
        User user = userService.findUserByEmail(email);
        Optional<List<PersonalBill>> optionalBill = personalBillService.getBillByUserId(user.getUserId());
        System.out.println("Calculating sum...");
        if(optionalBill!=null){
            List<PersonalBill> bill = optionalBill.get();
            Float sum = 0f;
            for(int i=0; i<bill.size(); i++){
                sum += bill.get(i).getAmount();
            }
            return ResponseEntity.ok(sum);
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByUser/{email}")
    public void deleteByUserId(@PathVariable String email){
        logger.info("Deleting All bills for user");
        User user = userService.findUserByEmail(email);
        personalBillService.deleteByUserId(user.getUserId());
    }

    @DeleteMapping("/deleteByBillId/{id}")
    public ResponseEntity<String> deleteByBillId(@PathVariable UUID id) {
        personalBillService.deleteByBillId(id);
        return ResponseEntity.ok("Bill deleted successfully!");
    }
    // @PutMapping("/updateStatus/{id}")
    // public void updateStatusById(@PathVariable UUID id){
    //     logger.info("Updating bill by id");
    //     billService.updateStatusToTrue(id);
    // }
}
