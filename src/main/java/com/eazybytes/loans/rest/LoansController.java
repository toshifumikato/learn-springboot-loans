package com.eazybytes.loans.rest;

import com.eazybytes.loans.constant.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST API for Loans at Eazy Bank",
        description = "CRUD REST APIs in Eazy Bank"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    Logger logger = LoggerFactory.getLogger(LoansController.class);
    ILoansService iLoansService;

    public LoansController(ILoansService iLoansService) {
        this.iLoansService = iLoansService;
    }

    @Operation(
            summary = "Returns all loans",
            description = "Returns all loans"
    )
    @GetMapping(path = "/loans/all")
    public List<LoansDto> getAllLoans() {
        return iLoansService.getAllLoans();
    }

    @Operation(
            summary = "Returns a loan by mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = LoansConstants.MESSAGE_200
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Loans not found by the specified mobile number"
            )
    })
    @GetMapping(path = "/loans")
    public ResponseEntity<LoansDto> getLoansById(@RequestParam @Pattern(regexp = "^[0-9]{10}", message = "Mobile number must be 10 digits")
                                                 String mobileNumber) {
        logger.info("Mobile Number: " + mobileNumber);
        LoansDto loansDto = iLoansService.getLoansById(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Create a new loan by mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = LoansConstants.STATUS_201,
                    description = LoansConstants.MESSAGE_201
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = ""
            )
    })
    @PostMapping(path = "/loans")
    public ResponseEntity<ResponseDto> createLoans(@RequestParam
                                                       @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                       String mobileNumber) {
        iLoansService.createLoans(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Update an existing loan"
    )
    @PutMapping(path = "/loans")
    public ResponseEntity<LoansDto> updateLoans(@RequestBody LoansDto loansDto) {
        loansDto = iLoansService.updateLoans(loansDto);
        return  ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Delete a loan by a mobile number"
    )
    @DeleteMapping(path = "/loans")
    public ResponseEntity<LoansDto> deleteLoans(@RequestParam String mobileNumber) {
        LoansDto dto = iLoansService.deleteLoans(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
