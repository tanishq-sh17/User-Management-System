package com.springtan.service;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;
import com.springtan.entity.User;
import com.springtan.exception.UserNotFoundException;
import com.springtan.mapper.UserMapper;
import com.springtan.repository.UserRepository;
import com.springtan.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponseDto saveUser(UserRequestDto userRequestDto){
        User user = userMapper.toEntity(userRequestDto);
        return userMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id){
        return userRepository
                .findById(id)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> {
                        log.error("User not found with id={}", id);
                        return new UserNotFoundException(
                                AppConstants.USER_NOT_FOUND);
                });
    }

    @Override
    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                        log.error("User not found with id={} while deleting the user", id);
                        return new UserNotFoundException(
                                AppConstants.USER_NOT_FOUND);
                });

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(UserRequestDto userRequestDto, Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id={} while updating the user", id);
                    return new UserNotFoundException(
                            AppConstants.USER_NOT_FOUND);
                });

        userMapper.updateEntityFromRequestDto(userRequestDto, user);
        return userMapper.toResponseDto(
                userRepository.save(user)
        );

    }
}
