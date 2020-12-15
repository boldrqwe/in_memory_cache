package com.cache.in_memory_cache;

import com.cache.in_memory_cache.CacheServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    @Bean
    public CacheService<String, Object> cacheService() {
        return new CacheServiceImpl<>(100);
    }
}
