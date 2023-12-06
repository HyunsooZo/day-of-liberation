package com.dayofliberation.service;

import com.dayofliberation.domain.Bank;
import com.dayofliberation.domain.Loan;
import com.dayofliberation.domain.User;
import com.dayofliberation.dto.LoanCreationRequestDto;
import com.dayofliberation.exception.CustomException;
import com.dayofliberation.exception.ErrorCode;
import com.dayofliberation.repository.BankRepository;
import com.dayofliberation.repository.LoanRepository;
import com.dayofliberation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    /**
     * 대출 정보 등록
     *
     * @param loanCreationRequestDto 대출 정보 등록 요청 객체
     */
    @Transactional
    public void createLoan(LoanCreationRequestDto loanCreationRequestDto) {
        Bank bank = bankRepository.findById(loanCreationRequestDto.getBankId())
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_NOT_FOUND));

        User user = userRepository.findById(loanCreationRequestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));

        Loan loan = Loan.from(bank, user, loanCreationRequestDto);

        loanRepository.save(loan);
    }
}
