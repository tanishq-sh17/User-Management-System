package com.springtan.controller;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;
import com.springtan.entity.User;
import com.springtan.mapper.UserMapper;
import com.springtan.service.UserService;
import com.springtan.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto){
        User user = userService.saveUser(userRequestDto);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userResponseDtoList =
                userService
                        .getAllUsers()
                        .stream()
                        .map(userMapper::toResponseDto)
                        .toList();

        return ResponseEntity.ok(userResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.toResponseDto(userService.getUserById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.ok(
                AppConstants.USER_DELETED_SUCCESSFULLY
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto,
            @PathVariable Long id
    )
    {
        User user = userService.updateUser(userRequestDto, id);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.ok(userResponseDto);

    }


}
