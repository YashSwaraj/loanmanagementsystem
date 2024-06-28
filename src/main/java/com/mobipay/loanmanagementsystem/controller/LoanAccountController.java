package com.mobipay.loanmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mobipay.loanmanagementsystem.model.LoanAccount;
import com.mobipay.loanmanagementsystem.service.LoanAccountService;

@RestController
@RequestMapping("/api")
public class LoanAccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanAccountController.class);

    private final LoanAccountService loanAccountService;

    public LoanAccountController(LoanAccountService loanAccountService) {
        this.loanAccountService = loanAccountService;
    }

    @GetMapping("/loanaccount")
    public ResponseEntity<LoanAccount> getLoanAccount(@RequestParam("loanAccountNumber") String loanAccountNumber) {
        LOGGER.info("Received request to get loan account details for account number: {}", loanAccountNumber);
        LoanAccount loanAccount = loanAccountService.getLoanAccountDetails(loanAccountNumber);
        if (loanAccount != null) {
            LOGGER.info("Returning loan account details for account number: {}", loanAccountNumber);
            return ResponseEntity.ok(loanAccount);
        } else {
            LOGGER.warn("Loan account details not found for account number: {}", loanAccountNumber);
            return ResponseEntity.notFound().build();
        }
    }
}
