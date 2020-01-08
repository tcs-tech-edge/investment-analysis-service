package com.techedge.ias.test.config;

import com.techedge.ias.mapper.UserMapper;
import com.techedge.ias.service.UserService;
import com.techedge.ias.service.impl.UserServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }
}
