package com.dayofliberation.service;

import com.dayofliberation.domain.Bank;
import com.dayofliberation.domain.Loan;
import com.dayofliberation.domain.User;
import com.dayofliberation.dto.LoanDto;
import com.dayofliberation.repository.BankRepository;
import com.dayofliberation.repository.LoanRepository;
import com.dayofliberation.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("대출 조회 테스트")
class LoanInquiryServiceTest {

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
    void inquireLoan() {
        // given
        User user = User.builder()
                .id(1L)
                .UUID("test")
                .build();

        Loan loan = Loan.builder()
                .bank(new Bank(1L, "국민은행"))
                .gracePeriod(0)
                .interestRate(0.02)
                .repaymentMethod("원리금균등")
                .repaymentPeriod(12)
                .executionDate(java.sql.Date.valueOf("2021-01-01"))
                .name("대출")
                .purpose("대출 목적")
                .totalAmount(java.math.BigDecimal.valueOf(1000000))
                .user(user)
                .repaymentAmount(java.math.BigDecimal.valueOf(100000))
                .expirationDate(java.sql.Date.valueOf("2022-01-01"))
                .build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(loanRepository.findByUser(user)).thenReturn(Collections.singletonList(loan));

        // when
        List<LoanDto> loans = loanService.getLoans(1L);

        // then
        Assertions.assertThat(loans.get(0).getId()).isEqualTo(loan.getId());
    }
}