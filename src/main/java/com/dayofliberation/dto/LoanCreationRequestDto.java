package com.dayofliberation.dto;

import com.dayofliberation.enums.RepaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanCreationRequestDto {
    private Long userId;
    private Long bankId;
    private String name; // 대출 이름
    private String purpose; // 대출 목적
    private RepaymentMethod repaymentMethod; // 상환 방식
    private Date executionDate; // 대출 실행일
    private Integer repaymentPeriod; // 상환 기간 (개월)
    private Integer gracePeriod; // 유예 기간 (개월)
    private Double interestRate; // 이자율
    private BigDecimal totalAmount; // 총 대출 금액
}
