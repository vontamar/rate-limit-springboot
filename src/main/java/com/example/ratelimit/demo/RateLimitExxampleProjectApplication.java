package com.example.ratelimit.demo;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RateLimitExxampleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateLimitExxampleProjectApplication.class, args);
    }

}
