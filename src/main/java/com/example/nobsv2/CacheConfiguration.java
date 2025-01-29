package com.example.nobsv2;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

    @Bean //return val gets injected into Spring Container using dependency injection
    //will talk more about after security section
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();
        manager.setAllowNullValues(false);
        manager.setCacheNames(Arrays.asList("searchSnowboardCache"));
        return manager;
    }

    @CacheEvict(value = "searchSnowboardCache", allEntries = true)
    @Scheduled(fixedDelay = 300000, initialDelay = 0)
    public void evictSearchSnowboardCache() {System.out.println("Evicting Search Snowboard Cache");}
}
