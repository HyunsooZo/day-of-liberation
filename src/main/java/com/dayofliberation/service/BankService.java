package com.dayofliberation.service;

import com.dayofliberation.dto.BankDto;
import com.dayofliberation.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BankService {
    private final BankRepository bankRepository;

    /**
     * 은행 목록 조회
     *
     * @return List<BankDto>
     */
    @Transactional(readOnly = true)
    public List<BankDto> getBanks() {
        return bankRepository.findAll().stream()
                .map(BankDto::from)
                .collect(Collectors.toList());
    }
}
