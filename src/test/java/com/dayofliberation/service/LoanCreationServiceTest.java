package com.dayofliberation.service;

import com.dayofliberation.domain.Bank;
import com.dayofliberation.domain.Loan;
import com.dayofliberation.domain.User;
import com.dayofliberation.dto.LoanCreationRequestDto;
import com.dayofliberation.enums.RepaymentMethod;
import com.dayofliberation.repository.BankRepository;
import com.dayofliberation.repository.LoanRepository;
import com.dayofliberation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@DisplayName("대출 생성 테스트")
class LoanCreationServiceTest {

    @Mock
    private LoanRepository loanRepository;
    @Mock
    private BankRepository bankRepository;
    @Mock
    private UserRepository userRepository;

    private LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        loanService = new LoanService(loanRepository, bankRepository, userRepository);
    }

    @Test
    @DisplayName("성공")
    void createLoan() {
        // given
        LoanCreationRequestDto loanCreationRequestDto = LoanCreationRequestDto.builder()
                .bankId(1L)
                .gracePeriod(0)
                .interestRate(0.02)
                .repaymentMethod(RepaymentMethod.ANNUITY)
                .repaymentPeriod(12)
                .executionDate(java.sql.Date.valueOf("2021-01-01"))
                .name("대출")
                .purpose("대출 목적")
                .totalAmount(java.math.BigDecimal.valueOf(1000000))
                .userId(1L)
                .build();
        Mockito.when(bankRepository.findById(any())).thenReturn(Optional.of(new Bank()));
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        // when
        loanService.createLoan(loanCreationRequestDto);

        // then
        Mockito.verify(loanRepository, times(1)).save(any(Loan.class));
    }

}