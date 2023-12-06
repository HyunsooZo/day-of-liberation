package com.dayofliberation.controller;

import com.dayofliberation.dto.LoanCreationRequestDto;
import com.dayofliberation.dto.LoanDto;
import com.dayofliberation.dto.LoanListResponseDto;
import com.dayofliberation.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

    /**
     * 대출 목록 조회
     *
     * @param userId 사용자 ID
     * @return ResponseEntity<List<LoanDto>>
     */
    @GetMapping
    @ApiOperation(value = "대출 목록 조회", notes = "대출 목록 조회")
    public ResponseEntity<LoanListResponseDto> getLoan(@RequestParam Long userId) {

        List<LoanDto> loans = loanService.getLoans(userId);

        return ResponseEntity.status(OK).body(LoanListResponseDto.from(loans));
    }
}
