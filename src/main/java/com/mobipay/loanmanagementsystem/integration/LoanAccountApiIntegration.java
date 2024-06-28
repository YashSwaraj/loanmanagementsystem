package com.mobipay.loanmanagementsystem.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mobipay.loanmanagementsystem.model.LoanAccountApiResponse;

@Component
public class LoanAccountApiIntegration {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanAccountApiIntegration.class);

    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    public LoanAccountApiIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LoanAccountApiResponse fetchLoanAccountDetails(String loanAccountNumber) {
    	//changing loanAccountNumber for mocked api which only takes the value "1"
    	loanAccountNumber = "1";
    	String url = externalApiUrl + "/" + loanAccountNumber;
        LOGGER.info("Calling external API for loan account details: {}", url);
        try {
            LoanAccountApiResponse response = restTemplate.getForObject(url, LoanAccountApiResponse.class);
            LOGGER.info("External API response: {}", response);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error calling external API for loan account number: {}", loanAccountNumber, e);
            return null;
        }
    }
}
