package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

import java.util.List;

public interface ILoansService {
    void createLoans(String mobileNumber);
    LoansDto getLoansById(String mobileNumber);

    LoansDto updateLoans(LoansDto loansDto);

    LoansDto deleteLoans(String mobileNumber);

    List<LoansDto> getAllLoans();
}
