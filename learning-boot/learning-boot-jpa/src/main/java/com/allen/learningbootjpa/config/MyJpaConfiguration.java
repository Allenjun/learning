package com.allen.learningbootjpa.config;

import com.allen.learningbootjpa.repository.BaseJpaRepositoryCustomImpl;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author JUN @Description TODO
 * @createTime 13:44
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.allen.learningbootjpa.repository"},
        //    repositoryFactoryBeanClass = Object.class,
        repositoryBaseClass = BaseJpaRepositoryCustomImpl.class,
        repositoryImplementationPostfix = "impl",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        enableDefaultTransactions = true)
public class MyJpaConfiguration {

    @Bean
    public EntityManager entityManager(
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return Objects.requireNonNull(entityManagerFactory.getObject()).createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            DataSource dataSource,
            JpaProperties jpaProperties) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.allen.learningbootjpa.pojo.DO")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
