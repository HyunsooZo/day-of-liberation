package com.dayofliberation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanListResponseDto {
    private List<LoanDto> loans;

    public static LoanListResponseDto from(List<LoanDto> loans) {
        return LoanListResponseDto.builder()
                .loans(loans)
                .build();
    }
}
