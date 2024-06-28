package com.mobipay.loanmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mobipay.loanmanagementsystem.*")
@ComponentScan(basePackages = { "com.mobipay.loanmanagementsystem.*" })
@EntityScan("com.mobipay.loanmanagementsystem.*")
public class LoanmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanmanagementsystemApplication.class, args);
	}

}
