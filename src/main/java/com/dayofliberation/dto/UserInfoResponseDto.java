package com.dayofliberation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDto {
    private Long id;

    public static UserInfoResponseDto from(UserDto userDto) {
        return UserInfoResponseDto.builder()
                .id(userDto.getId())
                .build();
    }
}
