package com.mobipay.loanmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobipay.loanmanagementsystem.model.LoanAccount;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {
    Optional<LoanAccount> findByLoanAccountNumber(String loanAccountNumber);
}
