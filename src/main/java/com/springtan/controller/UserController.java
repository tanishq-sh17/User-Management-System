package com.springtan.controller;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;
import com.springtan.entity.User;
import com.springtan.mapper.UserMapper;
import com.springtan.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userServiceImpl, UserMapper userMapper) {
        this.userServiceImpl = userServiceImpl;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto){
        User user = userServiceImpl.saveUser(userRequestDto);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userResponseDtoList =
                userServiceImpl
                        .getAllUsers()
                        .stream()
                        .map(userMapper::toResponseDto)
                        .toList();

        return ResponseEntity.ok(userResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.toResponseDto(userServiceImpl.getUserById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userServiceImpl.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto userRequestDto,
            @PathVariable Long id
    )
    {
        User user = userServiceImpl.updateUser(userRequestDto, id);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.ok(userResponseDto);

    }



}
