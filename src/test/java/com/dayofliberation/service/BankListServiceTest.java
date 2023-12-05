package com.dayofliberation.service;

import com.dayofliberation.domain.Bank;
import com.dayofliberation.dto.BankDto;
import com.dayofliberation.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("은행 목록 조회 테스트")
class BankListServiceTest {
    @Mock
    private BankRepository bankRepository;

    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bankService = new BankService(bankRepository);
    }

    @Test
    @DisplayName("성공")
    public void testGetBanks() {
        // Given
        Bank bank1 = Bank.builder()
                .id(11L)
                .name("국민은행")
                .build();
        Bank bank2 = Bank.builder()
                .id(12L)
                .name("신한은행")
                .build();
        when(bankRepository.findAll()).thenReturn(List.of(bank1, bank2));
        // When
        List<BankDto> result = bankService.getBanks();
        // Then
        assertEquals(2, result.size());
        assertEquals("국민은행", result.get(0).getName());
        assertEquals("신한은행", result.get(1).getName());
    }
}