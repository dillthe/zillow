package com.github.zillow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackages = "com.github.zillow")
public class AppConfig {
//        @Bean
//        public RestTemplate restTemplate() {
//            return new RestTemplate();
//        }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
