package com.dayofliberation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUUIDDto {
    @NotBlank(message = "uuid는 필수 입력 값입니다.")
    private String userUUID;
}
