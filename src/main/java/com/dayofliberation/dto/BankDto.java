package com.dayofliberation.dto;

import com.dayofliberation.domain.Bank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BankDto {
    private Long id;
    private String name;

    /**
     * BankDto 객체로 변환
     *
     * @param bank
     * @return BankDto
     */
    public static BankDto from(Bank bank) {
        return BankDto.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();
    }
}
