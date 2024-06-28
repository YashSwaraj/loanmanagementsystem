package com.mobipay.loanmanagementsystem.service;

import java.util.Comparator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mobipay.loanmanagementsystem.model.LoanAccount;
import com.mobipay.loanmanagementsystem.model.LoanAccountApiResponse;
import com.mobipay.loanmanagementsystem.repository.LoanAccountRepository;

@Service
public class LoanAccountService {
    private final LoanAccountRepository loanAccountRepository;
    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    public LoanAccountService(LoanAccountRepository loanAccountRepository, RestTemplate restTemplate) {
        this.loanAccountRepository = loanAccountRepository;
        this.restTemplate = restTemplate;
    }

    public LoanAccount getLoanAccountDetails(String loanAccountNumber) {
        Optional<LoanAccount> loanAccountOpt = loanAccountRepository.findByLoanAccountNumber(loanAccountNumber);
        if (loanAccountOpt.isPresent()) {
            return loanAccountOpt.get();
        }
        else {
            ResponseEntity<LoanAccountApiResponse> response = restTemplate.getForEntity(externalApiUrl + loanAccountNumber, LoanAccountApiResponse.class);
            LoanAccountApiResponse apiResponse = response.getBody();
            if (apiResponse != null) {
                LoanAccountApiResponse.EmiDetails latestEmi = apiResponse.getEmiDetails().stream()
                        .max(Comparator.comparingInt(LoanAccountApiResponse.EmiDetails::getEmiNumber))
                        .orElseThrow(() -> new RuntimeException("No EMI details found"));

                LoanAccount loanAccount = new LoanAccount();
                loanAccount.setLoanAccountNumber(apiResponse.getLoanAccountNumber());
                loanAccount.setDueDate(latestEmi.getDueDate());
                loanAccount.setEmiAmount(latestEmi.getEmiAmount());
                loanAccountRepository.save(loanAccount);
                return loanAccount;
            }
            return null;
        }
    }
}
