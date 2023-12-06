package com.dayofliberation.dto;

import com.dayofliberation.domain.Bank;
import com.dayofliberation.domain.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDto {
    private Long id;
    private Bank bank;
    private Long userId;
    private String name; // 대출 이름
    private String purpose; // 대출 목적
    private String repaymentMethod; // 상환 방식
    private Date executionDate; // 대출 실행일
    private Integer repaymentPeriod; // 상환 기간 (개월)
    private Date expirationDate; // 만기일
    private Integer gracePeriod; // 유예 기간 (개월)
    private Double interestRate; // 이자율
    private BigDecimal totalAmount; // 총 대출 금액
    private BigDecimal repaymentAmount; // 상환 금액
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일

    public static LoanDto from(Loan loan) {
        return LoanDto.builder()
                .id(loan.getId())
                .bank(loan.getBank())
                .userId(loan.getUser().getId())
                .name(loan.getName())
                .purpose(loan.getPurpose())
                .repaymentMethod(loan.getRepaymentMethod())
                .executionDate(loan.getExecutionDate())
                .repaymentPeriod(loan.getRepaymentPeriod())
                .expirationDate(loan.getExpirationDate())
                .gracePeriod(loan.getGracePeriod())
                .interestRate(loan.getInterestRate())
                .totalAmount(loan.getTotalAmount())
                .repaymentAmount(loan.getRepaymentAmount())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .build();
    }
}
