package com.dayofliberation.domain;

import com.dayofliberation.dto.LoanCreationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String name; // 대출 이름

    @Column
    private String purpose; // 대출 목적

    @Column
    private String repaymentMethod; // 상환 방식

    @Column
    private Date executionDate; // 대출 실행일

    @Column
    private Integer repaymentPeriod; // 상환 기간 (개월)

    @Column
    private Date expirationDate; // 만기일

    @Column
    private Integer gracePeriod; // 유예 기간 (개월)

    @Column
    private Double interestRate; // 이자율

    @Column
    private BigDecimal totalAmount; // 총 대출 금액

    @Column
    private BigDecimal repaymentAmount; // 상환 금액

    /**
     * 대출 생성 메서드
     *
     * @param bank                   은행 정보
     * @param loanCreationRequestDto 대출 생성 요청 DTO
     *                               return Loan
     */
    public static Loan from(Bank bank,
                            User user,
                            LoanCreationRequestDto loanCreationRequestDto) {
        return Loan.builder()
                .bank(bank) // 은행 정보
                .user(user) // 사용자 정보
                .name(loanCreationRequestDto.getName()) // 대출 상품명
                .purpose(loanCreationRequestDto.getPurpose()) // 대출 목적
                .repaymentMethod(loanCreationRequestDto.getRepaymentMethod().getName()) // 상환 방식
                .repaymentPeriod(loanCreationRequestDto.getRepaymentPeriod()) // 상환 기간
                .interestRate(loanCreationRequestDto.getInterestRate()) // 이자율
                .executionDate(loanCreationRequestDto.getExecutionDate()) // 대출 실행일
                .expirationDate(
                        Date.valueOf(
                                loanCreationRequestDto.getExecutionDate()
                                        .toLocalDate()
                                        .plusYears(loanCreationRequestDto.getRepaymentPeriod())
                        )
                ) // 만기일
                .gracePeriod(loanCreationRequestDto.getGracePeriod()) // 유예 기간
                .totalAmount(loanCreationRequestDto.getTotalAmount()) // 총 대출 금액
                .repaymentAmount(BigDecimal.ZERO) // 상환 금액
                .build();
    }
}
