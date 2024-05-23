package com.github.zillow.config;

import com.github.zillow.properties.DataSourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = {
            "com.github.zillow.repository.entity","com.github.zillow.repository.interest",
                "com.github.zillow.repository.listing",  "com.github.zillow.repository.roles",  "com.github.zillow.repository.user",
                "com.github.zillow.repository.userPrincipal"
        },
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "tmJpa"
)
public class JpaConfig {
    private final DataSourceProperties dataSourceProperties;
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() { return new JdbcTemplate(dataSource()); }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(/*@Qualifier("dataSource")*/ DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(
                "com.github.zillow.repository.entity","com.github.zillow.repository",
                "com.github.zillow.repository.interest",
                "com.github.zillow.repository.listing",  "com.github.zillow.repository.roles",  "com.github.zillow.repository.user",
                "com.github.zillow.repository.userPrincipal"
                );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comment", "true");

        em.setJpaPropertyMap(properties);

        return em;
    }


    @Bean(name = "tmJpa")
    public PlatformTransactionManager transactionManager(/*@Qualifier("dataSource")*/ DataSource dataSource) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean(dataSource).getObject());
        return transactionManager;
    }
}

