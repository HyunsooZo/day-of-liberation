package com.dayofliberation.service;

import com.dayofliberation.domain.User;
import com.dayofliberation.dto.UserDto;
import com.dayofliberation.dto.UserUUIDDto;
import com.dayofliberation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("사용자 정보 조회/가입 테스트")
class UserInfoServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("사용자 존재 -> 사용자 정보 조회")
    public void testGetUserInfo_UserExists() {
        // Given
        String userUUID = "existingUUID";
        UserUUIDDto userUUIDDto = new UserUUIDDto(userUUID);
        User existingUser = User.builder()
                .UUID(userUUID)
                .build();

        when(userRepository.findByUUID(userUUID)).thenReturn(Optional.of(existingUser));

        // When
        UserDto result = userService.getUserInfo(userUUIDDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUuid()).isEqualTo(userUUID);
    }

    @Test
    @DisplayName("사용자 없음 -> 사용자 가입")
    public void testGetUserInfo_UserDoesNotExist() {
        // Given
        String userUUID = "nonExistingUUID";
        UserUUIDDto userUUIDDto = new UserUUIDDto(userUUID);

        when(userRepository.findByUUID(userUUID)).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            return User.builder()
                    .id(1L)
                    .UUID(userUUID)
                    .build();
        });

        // When
        UserDto result = userService.getUserInfo(userUUIDDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUuid()).isEqualTo(userUUID);
    }
}
