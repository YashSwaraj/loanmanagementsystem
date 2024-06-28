package com.mobipay.loanmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobipay.loanmanagementsystem.model.LoanAccount;
import com.mobipay.loanmanagementsystem.service.LoanAccountService;

@RestController
@RequestMapping("/api")
public class LoanAccountController {
    private final LoanAccountService loanAccountService;

    public LoanAccountController(LoanAccountService loanAccountService) {
        this.loanAccountService = loanAccountService;
    }

    @GetMapping("/loanaccount/{loanAccountNumber}")
    public ResponseEntity<LoanAccount> getLoanAccount(@PathVariable String loanAccountNumber) {
        LoanAccount loanAccount = loanAccountService.getLoanAccountDetails(loanAccountNumber);
        return ResponseEntity.ok(loanAccount);
    }
}
