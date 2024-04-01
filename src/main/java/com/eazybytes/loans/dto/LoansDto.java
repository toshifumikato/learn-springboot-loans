package com.eazybytes.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigInteger;

public class LoansDto {
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    @Positive(message = "Total loan must be a positive integer.")
    private BigInteger totalLoan;
    @Positive(message = "Amount paid must be a positive integer.")
    private BigInteger amountPaid;
    private BigInteger outstandingAmount;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigInteger getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(BigInteger totalLoan) {
        this.totalLoan = totalLoan;
    }

    public BigInteger getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigInteger amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigInteger getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigInteger outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}
