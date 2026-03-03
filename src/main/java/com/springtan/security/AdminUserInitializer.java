package com.springtan.security;

import com.springtan.entity.User;
import com.springtan.repository.UserRepository;
import com.springtan.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminUserInitializer {

    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        log.info("Admin initializer running...");
        return args -> {
            if(userRepository.findUserByEmail("admin@email.com").isEmpty()){
                User admin = User.builder()
                        .name("admin")
                        .email("admin@email.com")
                        .role(Role.ROLE_ADMIN)
                        .about("I'm the admin")
                        .password(passwordEncoder.encode("admin1234"))
                        .build();

                userRepository.save(admin);

                log.info("Default admin user created..!");
            }
        };
    }
}
