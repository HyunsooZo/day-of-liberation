package com.dayofliberation.controller;

import com.dayofliberation.dto.LoanCreationRequestDto;
import com.dayofliberation.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Api(tags = "Loan API", description = "대출 관련 API")
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
@RestController
public class LoanController {

    private final LoanService loanService;

    /**
     * 대출 정보 등록
     *
     * @param loanCreationRequestDto 대출 정보 등록 요청 객체
     * @return ResponseEntity<Void>
     */
    @PostMapping
    @ApiOperation(value = "대출 정보 등록", notes = "대출 정보 등록")
    public ResponseEntity<Void> createLoan(
            @Valid @RequestBody LoanCreationRequestDto loanCreationRequestDto) {

        loanService.createLoan(loanCreationRequestDto);

        return ResponseEntity.status(CREATED).build();
    }
}
