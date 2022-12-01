package com.example.demo.web.user;

import com.example.demo.domain.user.entity.Role;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Test
    public void createUser(){
        String encodedPassword = passwordEncoder.encode("1234!");

        user = User.builder()
                .email("hello@world.com")
                .nickname("helloworld")
                .password(encodedPassword)
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}
