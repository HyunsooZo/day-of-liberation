package com.dayofliberation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RepaymentMethod {
    ANNUITY("원리금균등상환"),
    INTEREST("원금균등상환"),
    PRINCIPAL("원금균등분할상환"),
    ;

    private final String name;
}
