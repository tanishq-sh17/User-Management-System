package com.springtan.service;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByName(String name);

    UserResponseDto getUserByEmail(String email);

    void deleteUser(Long id);

    UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);
}
