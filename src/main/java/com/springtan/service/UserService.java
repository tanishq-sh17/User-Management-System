package com.springtan.service;

import com.springtan.dto.UserRequestDto;
import com.springtan.entity.User;
import com.springtan.exception.UserNotFoundException;
import com.springtan.util.AppConstants;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User saveUser(UserRequestDto userRequestDto);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User updateUser(UserRequestDto userRequestDto, Long id);
}
