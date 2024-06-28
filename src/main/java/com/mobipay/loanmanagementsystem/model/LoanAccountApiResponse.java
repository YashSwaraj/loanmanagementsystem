package com.mobipay.loanmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanAccountApiResponse {
    private String loanAccountNumber;
    private LoanDetails loanDetails;
    private List<EmiDetails> emiDetails;
    private int totalDue;
    private LocalDate nextDueDate;

    public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public LoanDetails getLoanDetails() {
		return loanDetails;
	}

	public void setLoanDetails(LoanDetails loanDetails) {
		this.loanDetails = loanDetails;
	}

	public List<EmiDetails> getEmiDetails() {
		return emiDetails;
	}

	public void setEmiDetails(List<EmiDetails> emiDetails) {
		this.emiDetails = emiDetails;
	}

	public int getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(int totalDue) {
		this.totalDue = totalDue;
	}

	public LocalDate getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(LocalDate nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
    public static class LoanDetails {
        private String borrowerName;
        private String loanType;
        private int loanAmount;
        private double interestRate;
        private LocalDate loanStartDate;
        private LocalDate loanEndDate;
        
		public String getBorrowerName() {
			return borrowerName;
		}
		public void setBorrowerName(String borrowerName) {
			this.borrowerName = borrowerName;
		}
		public String getLoanType() {
			return loanType;
		}
		public void setLoanType(String loanType) {
			this.loanType = loanType;
		}
		public int getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(int loanAmount) {
			this.loanAmount = loanAmount;
		}
		public double getInterestRate() {
			return interestRate;
		}
		public void setInterestRate(double interestRate) {
			this.interestRate = interestRate;
		}
		public LocalDate getLoanStartDate() {
			return loanStartDate;
		}
		public void setLoanStartDate(LocalDate loanStartDate) {
			this.loanStartDate = loanStartDate;
		}
		public LocalDate getLoanEndDate() {
			return loanEndDate;
		}
		public void setLoanEndDate(LocalDate loanEndDate) {
			this.loanEndDate = loanEndDate;
		}
		@Override
		public String toString() {
			return "LoanDetails [borrowerName=" + borrowerName + ", loanType=" + loanType + ", loanAmount=" + loanAmount
					+ ", interestRate=" + interestRate + ", loanStartDate=" + loanStartDate + ", loanEndDate="
					+ loanEndDate + "]";
		}
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmiDetails {
    	
    	private int emiNumber;
        private LocalDate dueDate;
        private int emiAmount;
        private String status;
        private LocalDate paymentDate;

        public int getEmiNumber() {
			return emiNumber;
		}
		public void setEmiNumber(int emiNumber) {
			this.emiNumber = emiNumber;
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDate getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}
		@Override
		public String toString() {
			return "EmiDetails [emiNumber=" + emiNumber + ", dueDate=" + dueDate + ", emiAmount=" + emiAmount
					+ ", status=" + status + ", paymentDate=" + paymentDate + "]";
		}
    }

	@Override
	public String toString() {
		return "LoanAccountApiResponse [loanAccountNumber=" + loanAccountNumber + ", loanDetails=" + loanDetails
				+ ", emiDetails=" + emiDetails + ", totalDue=" + totalDue + ", nextDueDate=" + nextDueDate + "]";
	}
}
