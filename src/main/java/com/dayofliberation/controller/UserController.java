package com.dayofliberation.controller;

import com.dayofliberation.dto.UserDto;
import com.dayofliberation.dto.UserInfoResponseDto;
import com.dayofliberation.dto.UserUUIDDto;
import com.dayofliberation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Api(tags = "User API", description = "사용자 관련 API")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 사용자 정보 조회
     *
     * @param userUUIDDto 사용자 UUID DTO
     * @return ResponseEntity<Void>
     */
    @PostMapping
    @ApiOperation(value = "사용자 ID 조회 (or 가입)", notes = "사용자 정보 조회")
    public ResponseEntity<UserInfoResponseDto> getUserId(
            @Valid @RequestBody UserUUIDDto userUUIDDto) {

        UserDto userDto = userService.getUserInfo(userUUIDDto);

        return ResponseEntity.status(OK).body(UserInfoResponseDto.from(userDto));
    }
}
