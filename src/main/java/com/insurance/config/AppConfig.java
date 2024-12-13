package com.insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.insurance.repository")
@ComponentScan(basePackages = "com.insurance")
public class AppConfig {


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        String dbUrl = System.getenv().getOrDefault("SPRING_DATASOURCE_URL", "jdbc:postgresql://postgres:5432/insurance_db");
        String dbUsername = System.getenv().getOrDefault("SPRING_DATASOURCE_USERNAME", "postgres");
        String dbPassword = System.getenv().getOrDefault("SPRING_DATASOURCE_PASSWORD", "0074");
        
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource);
        emFactory.setPackagesToScan("com.insurance.model"); 

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emFactory.setJpaVendorAdapter(vendorAdapter);
        emFactory.setJpaProperties(hibernateProperties());
        return emFactory;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("logging.level.org.springframework.security", "DEBUG");
        properties.setProperty("logging.level.org.springframework", "DEBUG");
        return properties;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }
}
