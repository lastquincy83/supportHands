package com.supporthands.supportHands.controller;

import com.supporthands.supportHands.model.BankingDetails;
import com.supporthands.supportHands.repository.BankingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/banking")
public class BankingDetailsController {

    @Autowired
    BankingDetailsRepository bankingDetailsRepository;

    @GetMapping("/{userId}")
    public BankingDetails getUserBankingDetails(@PathVariable int userId) {
        return bankingDetailsRepository.getUserBankingDetails(userId);
    }

    @PutMapping("/{userId}/{bank}/{accountType}/{accountNumber}")
    public int createBankingDetails(@PathVariable int userId, @PathVariable String bank, @PathVariable String accountType, @PathVariable String accountNumber) {
        return bankingDetailsRepository.createBankingDetails(userId, bank, accountType, accountNumber);
    }

    @PutMapping("/update/{userId}/{bank}/{accountType}/{accountNumber}")
    public int updateBankingDetails(@PathVariable int userId, @PathVariable String bank, @PathVariable String accountType, @PathVariable String accountNumber) {
        return bankingDetailsRepository.createBankingDetails(userId, bank, accountType, accountNumber);
    }
}
