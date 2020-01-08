package com.techedge.ias;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class IASApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IASApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IASApplication.class, args);
        System.getenv().forEach((key, value) -> LOGGER.debug("{} -> {}", key, value));
    }

}
