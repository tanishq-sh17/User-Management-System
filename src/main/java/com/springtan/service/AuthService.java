package com.springtan.service;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;
import com.springtan.entity.User;
import com.springtan.mapper.UserMapper;
import com.springtan.repository.UserRepository;
import com.springtan.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponseDto register(UserRequestDto dto){

        User user = userMapper.toEntity(dto);
        user.setRole(Role.ROLE_USER);
        user.setPassword(
                passwordEncoder.encode(dto.password())
        );

        return userMapper.toResponseDto(
                userRepository.save(user));
    }
}
