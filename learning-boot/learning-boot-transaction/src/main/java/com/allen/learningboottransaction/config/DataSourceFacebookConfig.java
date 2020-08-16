package com.allen.learningboottransaction.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    "com.allen.learningboottransaction.repository.facebook" },
    entityManagerFactoryRef = "entityManagerFactoryFacebook",
    transactionManagerRef = "platformTransactionManagerFacebook")
public class DataSourceFacebookConfig {

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    @Primary
    @ConfigurationProperties("learning.datasource.facebook")
    public DataSource dataSourceFacebook() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryFacebook(
        EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder
            .dataSource(dataSourceFacebook())
            .packages("com.allen.learningboottransaction.pojo.DO.facebook")
            .properties(jpaProperties.getProperties())
            .persistenceUnit("facebook")
            .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager platformTransactionManagerFacebook(
        EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return new JpaTransactionManager(entityManagerFactoryFacebook(entityManagerFactoryBuilder).getObject());
    }

    @Bean
    @Primary
    public TransactionTemplate transactionTemplateFacebook(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return new TransactionTemplate(platformTransactionManagerFacebook(entityManagerFactoryBuilder));
    }

}
