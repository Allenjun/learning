package com.allen.learningboottransaction.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/17 15:13:00
 */
@Configuration
@EnableJpaRepositories(basePackages = {
    "com.allen.learningboottransaction.repository.google" },
    entityManagerFactoryRef = "entityManagerFactoryGoogle",
    transactionManagerRef = "platformTransactionManagerGoogle")
public class DataSourceGoogleConfig {

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    @ConfigurationProperties("learning.datasource.google")
    public DataSource dataSourceGoogle() {
        return new HikariDataSource();
    }

    @Bean
    public TransactionTemplate transactionTemplateGoogle(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return new TransactionTemplate(platformTransactionManagerGoogle(entityManagerFactoryBuilder));
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryGoogle(
        EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder
            .dataSource(dataSourceGoogle())
            .packages("com.allen.learningboottransaction.pojo.DO.google")
            .properties(jpaProperties.getProperties())
            .persistenceUnit("google")
            .build();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManagerGoogle(
        EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return new JpaTransactionManager(entityManagerFactoryGoogle(entityManagerFactoryBuilder).getObject());
    }

}
