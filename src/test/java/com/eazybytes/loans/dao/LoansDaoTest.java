package com.eazybytes.loans.dao;

import com.eazybytes.loans.LoansApplication;
import com.eazybytes.loans.entity.Loans;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Optional;

//@DataJpaTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LoansApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LoansDaoTest {

    @Autowired
    private LoansDao classUnderTest;

    @Test
    public void LoansDao_findByMobileNumber_Success() {
        Loans loans1 = new Loans(
                Long.valueOf(123),
                "0123456789",
                "123456",
                "HOME",
                BigInteger.valueOf(123456789),
                BigInteger.valueOf(1232),
                BigInteger.valueOf(89837987)
        );
        classUnderTest.save(loans1);

        Optional<Loans> result = classUnderTest.findByMobileNumber("0123456789");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(123456789, result.get().getTotalLoan().intValue());
        Assertions.assertNotNull(result.get().getCreatedBy());
        Assertions.assertNotNull(result.get().getCreatedAt());
        Assertions.assertNull(result.get().getUpdatedAt());
    }
}
