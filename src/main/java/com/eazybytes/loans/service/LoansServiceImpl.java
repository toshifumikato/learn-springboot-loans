package com.eazybytes.loans.service;

import com.eazybytes.loans.dao.LoansDao;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoansAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {
    private LoansDao loansDao;

    public LoansServiceImpl(LoansDao loansDao) {
        this.loansDao = loansDao;
    }

    public LoansServiceImpl() {
    }

    @Override
    public void createLoans(String mobileNumber) {
        Optional<Loans> optionalLoans = loansDao.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoansAlreadyExistsException("asdfsdafsdafsdafdsafsadfadsf" + mobileNumber);
        }

        Loans loans = new Loans();
        loans.setMobileNumber(mobileNumber);
        loans.setTotalLoan(BigInteger.valueOf(0));
        loans.setLoanType("blah");
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomLoanNumber));
        loans.setAmountPaid(BigInteger.valueOf(0));
        loans.setOutstandingAmount(BigInteger.valueOf(0));
        loansDao.save(loans);
    }

    @Override
    public LoansDto getLoansById(String mobileNumber) {
        Loans loans = loansDao.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("aaa")
        );
        LoansDto dto = new LoansDto();
        LoansMapper.mapToLoansDto(loans, dto);
        return dto;
    }

    @Override
    public LoansDto updateLoans(LoansDto loansDto) {
        LoansDto newDto = new LoansDto();
        Loans loans = loansDao.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("update failed. target loan not found.")
        );
        LoansMapper.mapToLoans(loansDto, loans);
        Loans newLoans = loansDao.save(loans);
        LoansMapper.mapToLoansDto(newLoans, loansDto);
        return loansDto;
    }

    @Override
    public LoansDto deleteLoans(String mobileNumber) {
        Loans loans = loansDao.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Delete failed.")
        );
        loansDao.delete(loans);
        LoansDto dto =  new LoansDto();
        LoansMapper.mapToLoansDto(loans, dto);
        return dto;
    }

    @Override
    public List<LoansDto> getAllLoans() {
        List<Loans> result = new ArrayList<Loans>();
        result = loansDao.findAll();

        List<LoansDto> listToReturn = new ArrayList<LoansDto>();
        result.stream().forEach(
                (entity) -> {
                    LoansDto dto = new LoansDto();
                    LoansMapper.mapToLoansDto(entity, dto);
                    listToReturn.add(dto);
                }
        );
        return listToReturn;
    }
}
