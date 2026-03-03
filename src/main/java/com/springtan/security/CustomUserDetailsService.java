package com.springtan.security;

import com.springtan.entity.User;
import com.springtan.exception.UserNotFoundException;
import com.springtan.repository.UserRepository;
import com.springtan.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> {
            log.error("User not found with email={}", email);
            return new UserNotFoundException(AppConstants.USER_NOT_FOUND);
        });

        return new CustomUserDetails(user);
    }
}
