package com.dayofliberation.dto;

import com.dayofliberation.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private String uuid;
    private Long id;

    public static UserDto from(User user){
        return UserDto.builder()
                .uuid(user.getUUID())
                .id(user.getId())
                .build();
    }
}
