package com.dayofliberation.service;

import com.dayofliberation.domain.User;
import com.dayofliberation.dto.UserDto;
import com.dayofliberation.dto.UserUUIDDto;
import com.dayofliberation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * 사용자 정보 조회 (없을 경우 회원가입 후 아이디 반환)
     *
     * @param userUUIDDto 사용자 UUID DTO
     * @return UserDto
     */
    @Transactional
    public UserDto getUserInfo(UserUUIDDto userUUIDDto) {

        User user = userRepository.findByUUID(userUUIDDto.getUserUUID())
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .UUID(userUUIDDto.getUserUUID())
                            .build();

                    return userRepository.save(newUser);
                });

        return UserDto.from(user);
    }
}
