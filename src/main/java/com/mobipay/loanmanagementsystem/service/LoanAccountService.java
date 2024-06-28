package com.mobipay.loanmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.mobipay.loanmanagementsystem.model.LoanAccount;
import com.mobipay.loanmanagementsystem.model.LoanAccountApiResponse;
import com.mobipay.loanmanagementsystem.repository.LoanAccountRepository;
import com.mobipay.loanmanagementsystem.integration.LoanAccountApiIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Comparator;
import java.util.Optional;

@Service
public class LoanAccountService {
	 private static final Logger LOGGER = LoggerFactory.getLogger(LoanAccountService.class);

    private final LoanAccountRepository loanAccountRepository;
    private final LoanAccountApiIntegration loanAccountApiIntegration;

    public LoanAccountService(LoanAccountRepository loanAccountRepository, LoanAccountApiIntegration loanAccountApiIntegration) {
        this.loanAccountRepository = loanAccountRepository;
        this.loanAccountApiIntegration = loanAccountApiIntegration;
    }

    public LoanAccount getLoanAccountDetails(String loanAccountNumber) {
    	LOGGER.info("Fetching loan account details for account number: {}", loanAccountNumber);

        Optional<LoanAccount> loanAccountOpt = loanAccountRepository.findByLoanAccountNumber(loanAccountNumber);
        if (loanAccountOpt.isPresent()) {
            LOGGER.debug("Loan account found in database for account number: {}", loanAccountNumber);
            return loanAccountOpt.get();
        } else {
            LOGGER.info("Loan account not found in database, calling external API for account number: {}", loanAccountNumber);
            LoanAccountApiResponse apiResponse = loanAccountApiIntegration.fetchLoanAccountDetails(loanAccountNumber);
            if (apiResponse != null) {
                LOGGER.debug("External API response received for account number: {}", loanAccountNumber);
                LoanAccountApiResponse.EmiDetails latestEmi = apiResponse.getEmiDetails().stream()
                        .max(Comparator.comparingInt(LoanAccountApiResponse.EmiDetails::getEmiNumber))
                        .orElseThrow(() -> new RuntimeException("No EMI details found"));
          
                LoanAccount loanAccount = new LoanAccount();
                loanAccount.setLoanAccountNumber(apiResponse.getLoanAccountNumber());
                loanAccount.setDueDate(latestEmi.getDueDate());
                loanAccount.setEmiAmount(latestEmi.getEmiAmount());
                loanAccountRepository.save(loanAccount);
                LOGGER.info("Loan account details saved to database for account number: {}", loanAccountNumber);
                return loanAccount;
            }
            LOGGER.warn("No data received from external API for account number: {}", loanAccountNumber);
            return null;
        }
    }
}
