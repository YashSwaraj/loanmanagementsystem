package com.mobipay.loanmanagementsystem.model;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LoanAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id;
    private String loanAccountNumber;
    private LocalDate dueDate;
    private int emiAmount;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public int getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(int emiAmount) {
		this.emiAmount = emiAmount;
	}
	
	@Override
	public String toString() {
		return "LoanAccount [id=" + id + ", loanAccountNumber=" + loanAccountNumber + ", dueDate=" + dueDate
				+ ", emiAmount=" + emiAmount + "]";
	}
}
