//package com.github.zillow.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = {
//            "com.github.zillow.repository.entity","com.github.zillow.repository.interest",
//                "com.github.zillow.repository.listing"
//        },
//        entityManagerFactoryRef = "entityManagerFactoryBean",
//        transactionManagerRef = "tmJpa"
//)
//public class JpaConfig {
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan(
//                "com.github.zillow.repository.entity","com.github.zillow.repository"
//                );
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.use_sql_comment", "true");
//
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//
//    @Bean(name = "tmJpa")
//    public PlatformTransactionManager transactionManager(/*@Qualifier("dataSource")*/ DataSource dataSource) {
//
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactoryBean(dataSource).getObject());
//        return transactionManager;
//    }
//}
//
