package com.github.zillow.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="spring.datasource")
public record DataSourceProperties(
        String username,
        String password,
        String driverClassName,
        String url
) {
}

//@Getter
//@Setter
//public class DataSourceProperties {
//    private String username;
//    private String password;
//    private String driverClassName;
//    private String url;
//    }