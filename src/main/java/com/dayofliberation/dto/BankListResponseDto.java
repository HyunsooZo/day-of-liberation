package com.dayofliberation.dto;

import com.dayofliberation.domain.Bank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BankListResponseDto {
    private List<BankDto> banks;

    /**
     * BankListResponseDto 객체로 변환
     *
     * @param banks
     * @return BankListResponseDto
     */
    public static BankListResponseDto from(List<BankDto> banks) {
        return BankListResponseDto.builder()
                .banks(banks)
                .build();
    }
}
