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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.insurance.repository")
@ComponentScan(basePackages = "com.insurance")
public class AppConfig {

    // DataSource Configuration
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        String dbUrl = System.getenv().getOrDefault("SPRING_DATASOURCE_URL",
                "jdbc:postgresql://postgres:5432/insurance_db");
        String dbUsername = System.getenv().getOrDefault("SPRING_DATASOURCE_USERNAME", "postgres");
        String dbPassword = System.getenv().getOrDefault("SPRING_DATASOURCE_PASSWORD", "0074");

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        // Add connection testing and pooling properties
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
        properties.setProperty("logging.level.org.springfremework.security", "DEBUG");
        properties.setProperty("logging.level.org.springframework", "DEBUG");

        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", System.getenv().getOrDefault("SPRING_DATASOURCE_URL", "jdbc:postgresql://postgres:5432/insurance_db"));
        properties.setProperty("hibernate.connection.username", System.getenv().getOrDefault("SPRING_DATASOURCE_USERNAME", "postgres"));
        properties.setProperty("hibernate.connection.password", System.getenv().getOrDefault("SPRING_DATASOURCE_PASSWORD", "0074"));
        return properties;
    }
}
