package com.dayofliberation.controller;

import com.dayofliberation.dto.BankDto;
import com.dayofliberation.dto.BankListResponseDto;
import com.dayofliberation.service.BankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Bank API", description = "은행 관련 API")
@RequestMapping("/api/v1/banks")
@RequiredArgsConstructor
@RestController
public class BankController {
    private final BankService bankService;

    /**
     * 은행 목록 조회
     *
     * @return ResponseEntity<BankListResponseDto>
     */
    @GetMapping
    @ApiOperation(value = "은행 목록 조회", notes = "은행 목록 조회")
    public ResponseEntity<BankListResponseDto> getBanks() {
        List<BankDto> banks = bankService.getBanks();
        return ResponseEntity.ok(BankListResponseDto.from(banks));
    }
}
