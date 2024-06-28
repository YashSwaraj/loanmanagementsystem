package com.mobipay.loanmanagementsystem.service;

import com.mobipay.loanmanagementsystem.model.LoanAccount;

public interface LoanAccountService {
    LoanAccount getLoanAccountDetails(String loanAccountNumber);
}
