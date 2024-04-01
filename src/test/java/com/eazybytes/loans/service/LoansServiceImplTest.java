package com.eazybytes.loans.service;

import com.eazybytes.loans.dao.LoansDao;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoansServiceImplTest {

    @Mock
    private LoansDao mockLoansDao;

    @InjectMocks
    LoansServiceImpl classUnderTest;

//    @BeforeTestMethod
//    public void setYp() {
//        classUnderTest = new LoansServiceImpl();
//    }

    @Test
    public void testGetAll() {
        List<Loans> loansList = new ArrayList<Loans>();
        Loans loans1 = new Loans(
                Long.valueOf(123456789),
                "0123456789",
                "1871263872168",
                "HOME",
                BigInteger.valueOf(98798790879L),
                BigInteger.valueOf(9879879879L),
                BigInteger.valueOf(98798798789L)
        );
        loansList.add(loans1);
        when(mockLoansDao.findAll()).thenReturn(loansList);

        List<LoansDto> results = classUnderTest.getAllLoans();

        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals(BigInteger.valueOf(98798790879L), results.get(0).getTotalLoan());
    }
}
