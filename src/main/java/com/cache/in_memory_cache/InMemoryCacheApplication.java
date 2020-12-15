package com.cache.in_memory_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InMemoryCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(InMemoryCacheApplication.class, args);
    }

}
